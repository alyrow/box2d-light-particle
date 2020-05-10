package com.csnakes.gdx.particle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.csnakes.gdx.particle.physics.PhysicForce;
import com.csnakes.gdx.particle.physics.PhysicParticle;

public class Particle {
    private final boolean worldPhysic;
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

    public Particle(SpriteBatch batch, float life, boolean outer, Texture texture, Camera camera, ParticleSystem system, float x, float y, PhysicParticle physicParticle, boolean worldPhysic) {
        this.life = life * 1000; //s to ms
        this.outer = outer;
        this.texture = texture;
        this.system = system;
        this.x = x;
        this.y = y;
        this.physicParticle = physicParticle;
        this.worldPhysic = worldPhysic;

        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        physicParticle.setSize(sprite.getWidth(), sprite.getHeight());

        isInnerScreen = _calculateIfInnerScreen(camera);
        timeEllapsed = TimeUtils.millis();
    }

    public void render(SpriteBatch batch, Camera camera, Array<PhysicForce> forces) {
        if (life <= 0) return;
        float delta = Gdx.graphics.getDeltaTime() * 1000;
        isInnerScreen = _calculateIfInnerScreen(camera);
        if (outer && !isInnerScreen) life = life - delta;
        else if (!outer) life = life - delta;
        Gdx.app.log("inner", String.valueOf(isInnerScreen));

        //Put all physics here
        Array<Vector2> vectors = new Array<>();
        forces.forEach(force -> vectors.add(force.getForce(physicParticle)));
        float vx = 0, vy = 0;
        for (int i = 0; i < vectors.size; i++) {
            vx += vectors.get(i).x;
            vy += vectors.get(i).y;
        }
        if (!worldPhysic) {
            physicParticle.x += vx / (delta + 1);
            physicParticle.y += vy / (delta + 1);
        } else {
            physicParticle.body.setLinearVelocity(vx, vy);
            physicParticle.x = physicParticle.body.getPosition().x - physicParticle.width/2;
            physicParticle.y = physicParticle.body.getPosition().y - physicParticle.height/2;
        }
        x = physicParticle.x;
        y = physicParticle.y;
        Gdx.app.log("position", x+", "+y);
        sprite.setPosition(physicParticle.x, physicParticle.y);


        if (life < 500) {
            sprite.setAlpha(life/500);
        }
        if (life <= 0) {
            //texture.dispose();
            system.particles.removeValue(this, true);
            if (worldPhysic) {
                physicParticle.world.destroyBody(physicParticle.body);
            }
        } else sprite.draw(batch);
    }

    private boolean _calculateIfInnerScreen(Camera camera) {
        float w = camera.viewportWidth;//batch.getProjectionMatrix().getScaleX();
        float h = camera.viewportHeight;//batch.getProjectionMatrix().getScaleY();
        float x = 0;//camera.position.x;//batch.getProjectionMatrix().getTranslation(new Vector3()).x;
        float y = 0;//camera.position.y;//batch.getProjectionMatrix().getTranslation(new Vector3()).y;
        Gdx.app.log("w, h, x, y", w +", "+h+", "+", "+x+", "+y);

        return this.x >= x && this.x < x + w && this.y >= y && this.y < y + h;
    }
}
