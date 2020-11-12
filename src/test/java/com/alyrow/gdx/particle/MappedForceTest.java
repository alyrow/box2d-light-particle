package com.alyrow.gdx.particle;

import box2dLight.RayHandler;
import com.alyrow.gdx.particle.physics.BlackLine;
import com.alyrow.gdx.particle.physics.PhysicManager;
import com.alyrow.gdx.particle.physics.powerups.MappedForce;
import com.alyrow.gdx.particle.rules.ParticleEmissionDuration;
import com.alyrow.gdx.particle.rules.ParticleEmissionLightRandom;
import com.alyrow.gdx.particle.rules.ParticleEmissionNumber;
import com.alyrow.gdx.particle.rules.ParticleLife;
import com.alyrow.gdx.particle.utils.Line;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class MappedForceTest extends Game {

    private World world;
    private RayHandler rayHandler;
    private Box2DDebugRenderer debugRenderer;
    private ParticleSystem system;
    private OrthographicCamera camera;
    private ParticleEmissionLightRandom emissionLight;
    PhysicManager physicManager;

    @Override
    public void create() {
        Box2D.init();
        world = new World(new Vector2(10, 0), true);
        rayHandler = new RayHandler(world);
        rayHandler.setBlurNum(3);
        rayHandler.setAmbientLight(new Color(0.1f, 0.1f, 0.1f, 0f));
        rayHandler.setShadows(true);
        debugRenderer = new Box2DDebugRenderer();

        ParticleRules rules = new ParticleRules();
        ParticleEmissionNumber ps = new ParticleEmissionNumber(ParticleEmissionNumber.INNER_SCREEN, 200);
        ps.setDelay(1f);
        rules.setNumber(ps); //One particle emitted per seconds
        rules.setLife(new ParticleLife(5, true)); //Particles life : 5s. Life will decrease when particles are outside the screen.
        rules.setDuration(new ParticleEmissionDuration(true)); //Infinite duration for the emission
        emissionLight = new ParticleEmissionLightRandom(rayHandler, 16, /*new Color(0.37647f, 1, 1, 1)*/Color.BLACK, 10.5f, 50);
        rules.setLight(emissionLight); //Add random light distance between 35 and 45.

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f, 1.0f);
        camera.update();
        system = new ParticleSystem(ParticleType.HALO, null, camera);
        system.setRules(rules);
        system.setParticlesPosition(-2, 0);
//        system.disableBlending();


        int x = (int) (Gdx.graphics.getWidth() / 2f), y = (int) (Gdx.graphics.getHeight() / 2f);

        physicManager = new PhysicManager();
        MappedForce mappedForce = new MappedForce(
//                new Fan(x, y, 20f)
//                new Whirlpool(x, y, 20, 20) {{setDestructionRadius(0);}}
//                new BrownianForce(25,25, 156548, 1)
                new BlackLine(Line.fromTwoPoints(0, 0, 1, 1), 40)  {{setDestructionRadius(0);}}
                , true, null);
//        mappedForce.addForce(new PointAttract(x, y, 10f, -1));

        physicManager.addForce(mappedForce);

        system.setPhysicManager(physicManager);
    }

    boolean g = true;

    @Override
    public void render() {
        camera.update();
        world.step(1f / 60f, 6, 2);
        system.setParticlesPosition(Math.round(Math.random() * Gdx.graphics.getWidth()), Math.round(Math.random() * Gdx.graphics.getHeight())); //Random position
        emissionLight.color = new Color(0, 0, 1, 1);
        system.render();

        rayHandler.setCombinedMatrix(camera.combined);
        rayHandler.updateAndRender();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Mapped force tester");
        configuration.setWindowedMode(1024, 768);
        new Lwjgl3Application(new MappedForceTest(), configuration);
    }
}