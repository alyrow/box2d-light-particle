package com.csnakes.gdx.particle;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.csnakes.gdx.particle.texture.ParticleTexture;

public class ParticleSystem {
    public int type;
    private World world;
    private Camera camera;
    private ParticleTexture texture;
    private ParticleRules rules;

    public ParticleSystem(int type, World world, Camera camera) {
        this.type = type;
        this.world = world;
        this.camera = camera;
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
    }

    public ParticleRules getRules() {
        return rules;
    }
}
