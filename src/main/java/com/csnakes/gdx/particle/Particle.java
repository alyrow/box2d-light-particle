package com.csnakes.gdx.particle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.csnakes.gdx.particle.physics.PhysicForce;
import com.csnakes.gdx.particle.physics.PhysicParticle;

public class Particle {
    public float life; //in seconds
    public boolean outer; //If false --> Survive `life` seconds then die
    public Texture texture;
    public Sprite sprite;
    public Boolean isInnerScreen;
    private ParticleSystem system;
    private float x;
    private float y;
    private long timeEllapsed;
    public PhysicParticle physicParticle;

    public Particle(float life, boolean outer, Texture texture, Camera camera, ParticleSystem system, float x, float y, PhysicParticle physicParticle) {
        this.life = life * 1000; //s to ms
        this.outer = outer;
        this.texture = texture;
        this.system = system;
        this.x = x;
        this.y = y;
        this.physicParticle = physicParticle;

        sprite = new Sprite(texture);
        sprite.setPosition(x, y);

        isInnerScreen = _calculateIfInnerScreen(camera);
        timeEllapsed = TimeUtils.millis();
    }

    public void render(SpriteBatch batch, Camera camera, Array<PhysicForce> forces) {
        if (life <= 0) return;
        float delta = Gdx.graphics.getDeltaTime() * 1000;
        isInnerScreen = _calculateIfInnerScreen(camera);
        if (outer && !isInnerScreen) life = life - delta;
        else if (!outer) life = life - delta;
        //Gdx.app.log("Ellapsed", String.valueOf(delta));

        //Put all physics here
        Array<Vector2> vectors = new Array<>();
        forces.forEach(force -> vectors.add(force.getForce(physicParticle)));
        float vx = 0, vy = 0;
        for (int i = 0; i < vectors.size; i++) {
            vx += vectors.get(i).x;
            vy += vectors.get(i).y;
        }
        physicParticle.x += vx/(delta+1);
        physicParticle.y += vy/(delta+1);
        x = physicParticle.x;
        y = physicParticle.y;
        sprite.setPosition(physicParticle.x, physicParticle.y);


        if (life <= 0) {
            //texture.dispose();
            system.particles.removeValue(this, true);
        } else sprite.draw(batch);
    }

    private boolean _calculateIfInnerScreen(Camera camera) {
        float w = camera.viewportWidth;
        float h = camera.viewportHeight;
        float x = camera.position.x;
        float y = camera.position.y;

        return this.x >= x && this.x < x + w && this.y >= y && this.y < y + h;
    }
}
