package com.csnakes.gdx.particle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

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

    public Particle(float life, boolean outer, Texture texture, Camera camera, ParticleSystem system, float x, float y) {
        this.life = life * 1000; //s to ms
        this.outer = outer;
        this.texture = texture;
        this.system = system;
        this.x = x;
        this.y = y;

        sprite = new Sprite(texture);
        sprite.setPosition(x, y);

        isInnerScreen = _calculateIfInnerScreen(camera);
        timeEllapsed = TimeUtils.millis();
    }

    public void render(SpriteBatch batch, Camera camera) {
        if (life <= 0) return;
        float delta = Gdx.graphics.getDeltaTime() * 1000;
        isInnerScreen = _calculateIfInnerScreen(camera);
        if (outer && !isInnerScreen) life = life - delta;
        else if (!outer) life = life - delta;
        //Gdx.app.log("Ellapsed", String.valueOf(delta));

        //Put all physics here


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
