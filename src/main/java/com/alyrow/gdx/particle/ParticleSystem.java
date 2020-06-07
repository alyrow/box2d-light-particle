package com.alyrow.gdx.particle;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.alyrow.gdx.particle.physics.PhysicManager;
import com.alyrow.gdx.particle.rules.ParticleEmissionNumber;
import com.alyrow.gdx.particle.texture.ParticleTexture;

import java.util.Date;


/**
 * @author alyrow
 * Create a particle system
 */
public class ParticleSystem {
    /**
     * Define particles type
     * @see ParticleType
     */
    public int type;


    private World world;
    private Camera camera;

    /**
     * Define Texture of particles
     * @see ParticleTexture
     */
    public ParticleTexture texture;

    /**
     * Define rules as particles life, emission duration....
     * For more details :
     * @see ParticleRules
     */
    private ParticleRules rules;

    /**
     * Array of all existing particles in the system
     */
    public Array<Particle> particles = new Array<>();

    /**
     * Time of emission
     */
    public long time;

    /**
     * Draw particles on this
     */
    private SpriteBatch batch;

    private long test_long; //For check time elapsed or number of particles inner the screen

    /**
     * Initial position of particles
     */
    public float x, y;

    /**
     * Manage physics on particles
     * @see PhysicManager
     */
    private PhysicManager physicManager;

    /**
     * Constructor that create a particle system
     * @param type Particles type (Halo, Texture, No texture)
     *             {@link ParticleType}
     * @param world If `null` collisions between particles and world's objects are deactivated
     * @param camera For display particles on the screen
     */
    public ParticleSystem(int type, World world, Camera camera) {
        this.type = type;
        this.world = world;
        this.camera = camera;
        batch = new SpriteBatch();
        time = TimeUtils.millis();

        if(type == ParticleType.HALO) texture = new ParticleTexture("halo.png");
        else if (type == ParticleType.NOTHING) texture = new ParticleTexture();
    }

    /**
     * Set texture of particles if `type` is set to `TEXTURE` or `HALO`
     * @see ParticleType
     * @param texture Set texture with ParticleTexture object
     *                {@link ParticleTexture}
     */
    public void setTexture(ParticleTexture texture) {
        if (type == ParticleType.NOTHING) {
            try {
                throw new Exception("Particle type is set to NOTHING, you cannot set texture for NOTHING.");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        this.texture = texture;
    }

    /**
     * Set the rules of particle system and particles.
     * /!\ THIS IS VERY IMPORTANT TO SET RULES
     * @param rules All rules in one object ;)
     *              {@link ParticleRules}
     */
    public void setRules(ParticleRules rules) {
        this.rules = rules;
        if (rules.number.mode == ParticleEmissionNumber.PER_SECONDS) test_long = new Date().getTime();
    }

    /**
     * For get the rules
     * @return Return the rules in one single object
     * @see ParticleRules
     */
    public ParticleRules getRules() {
        return rules;
    }

    /**
     * Well, set physics rules to particles.
     * @param physicManager The physic manager contains all physics rules in a single object
     *                      {@link PhysicManager}
     */
    public void setPhysicManager(PhysicManager physicManager) {
        this.physicManager = physicManager;
    }

    /**
     * For get the physic manager
     * @return the physic manager
     */
    public PhysicManager getPhysicManager() {
        return physicManager;
    }

    /**
     * Set the first position of particles. If you modify this, it won't affect existing particles but futures.
     * @param x Set x position of futures particles
     * @param y Set y position of futures particles
     */
    public void setParticlesPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Free memory
     */
    public void dispose() {
        batch.dispose();
        particles.forEach(Particle::dispose);
        texture.dispose();
    }

    /**
     * Oh, if you want to see your particles, you need to call this in your render method...
     */
    public void render() {
        if (!rules.duration.infinite)
            if (TimeUtils.timeSinceMillis(time) >= rules.duration.duration * 1000) return;

        if (rules.number.mode == ParticleEmissionNumber.INNER_SCREEN) {
            test_long = 0;
            for (int i = 0; i < particles.size; i++) if (particles.get(i).isInnerScreen) test_long++;
            if (test_long < rules.number.getNumber())
                particles.add(new Particle(rules.light.getLight(), batch, rules.life.getLife(), rules.life.outer, texture.getTexture(), camera, this, x, y, physicManager.getParticleForces(x, y, world, camera), world != null, type));
        } else {
            if (new Date().getTime() >= test_long + rules.number.seconds*1000) {
                for (int j = 0; j < rules.number.getNumber(); j++)
                    particles.add(new Particle(rules.light.getLight(), batch, rules.life.getLife(), rules.life.outer, texture.getTexture(), camera, this, x, y, physicManager.getParticleForces(x, y, world, camera), world != null, type));
                test_long = new Date().getTime();
            }
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i < particles.size; i++) particles.get(i).render(batch, camera, physicManager.forces);
        //particles.forEach(particle -> particle.render(batch, camera));
        batch.end();
    }


    /**
     * Well, down are all blending function on SpriteBatch.
     * @see SpriteBatch
     */

    //Blending functions :
    public void enableBlending() {
        batch.enableBlending();
    }
    public void disableBlending() {
        batch.disableBlending();
    }
    public boolean isBlendingEnabled() {
        return batch.isBlendingEnabled();
    }
    public int getBlendDstFunc() {
        return batch.getBlendDstFunc();
    }
    public int getBlendSrcFunc() {
        return batch.getBlendSrcFunc();
    }
    public int getBlendSrcFuncAlpha() {
        return batch.getBlendSrcFuncAlpha();
    }
    public int getBlendDstFuncAlpha() {
        return batch.getBlendDstFuncAlpha();
    }
    public void disableBlending(int srcFunc, int dstFunc) {
        batch.setBlendFunction(srcFunc, dstFunc);
    }
    public void setBlendFunctionSeparate(int srcFuncColor, int dstFuncColor, int srcFuncAlpha, int dstFuncAlpha) {
        batch.setBlendFunctionSeparate(srcFuncColor, dstFuncColor, srcFuncAlpha, dstFuncAlpha);
    }
}
