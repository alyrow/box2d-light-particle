package com.csnakes.gdx.particle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.csnakes.gdx.particle.rules.ParticleEmissionNumber;
import com.csnakes.gdx.particle.texture.ParticleTexture;

import java.util.Date;

public class ParticleSystem {
    public int type;
    private World world;
    private Camera camera;
    private ParticleTexture texture;
    private ParticleRules rules;
    public Array<Particle> particles = new Array<>();
    public long time;
    private SpriteBatch batch;
    private long test_long; //For check time elapsed or number of particles inner the screen
    private float x, y;

    public ParticleSystem(int type, World world, Camera camera) {
        this.type = type;
        this.world = world;
        this.camera = camera;
        batch = new SpriteBatch();
        time = TimeUtils.millis();
    }

    public void setTexture(ParticleTexture texture) {
        if (type == ParticleType.HALO || type == ParticleType.NOTHING) {
            try {
                throw new Exception("Particle type is set to HALO or NOTHING, you cannot set texture for HALO or NOTHING. Maybe in a future release.");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        this.texture = texture;
    }

    public void setRules(ParticleRules rules) {
        this.rules = rules;
        if (rules.number.mode == ParticleEmissionNumber.PER_SECONDS) test_long = new Date().getTime();
    }

    public ParticleRules getRules() {
        return rules;
    }

    public void setParticlesPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public void dispose() {
        batch.dispose();
    }

    public void render() {
        if (!rules.duration.infinite)
            if (TimeUtils.timeSinceMillis(time) >= rules.duration.duration * 1000) return;

        if (rules.number.mode == ParticleEmissionNumber.INNER_SCREEN) {
            test_long = 0;
            for (int i = 0; i < particles.size; i++) if (particles.get(i).isInnerScreen) test_long++;
            if (test_long < rules.number.getNumber())
                particles.add(new Particle(rules.life.getLife(), rules.life.outer, texture.getTexture(), camera, this, x, y));
        } else {
            if (new Date().getTime() >= test_long + 1000) {
                for (int j = 0; j < rules.number.getNumber(); j++)
                    particles.add(new Particle(rules.life.getLife(), rules.life.outer, texture.getTexture(), camera, this, x, y));
                test_long = new Date().getTime();
            }
        }

        //batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i < particles.size; i++) particles.get(i).render(batch, camera);
        //particles.forEach(particle -> particle.render(batch, camera));
        batch.end();
    }
}
