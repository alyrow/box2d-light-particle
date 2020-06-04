package com.alyrow.gdx.particle.pack;

import box2dLight.RayHandler;
import com.alyrow.gdx.particle.ParticleRules;
import com.alyrow.gdx.particle.ParticleSystem;
import com.alyrow.gdx.particle.physics.*;
import com.alyrow.gdx.particle.rules.*;
import com.alyrow.gdx.particle.texture.AnimatedTexture;
import com.alyrow.gdx.particle.texture.ParticleTexture;
import com.alyrow.gdx.particle.texture.RandomParticleTexture;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.function.BiConsumer;

public class Packer {
    JSONObject object;
    ParticleSystem system;

    private World world;
    private Camera camera;
    private RayHandler rayHandler;


    public Packer(String path) {}

    public Packer(ParticleSystem system) {}


    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setRayHandler(RayHandler rayHandler) {
        this.rayHandler = rayHandler;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void load(String path) throws IOException, ParseException {
        ZipFile zipFile = new ZipFile(path);
        FileHeader fileHeader = zipFile.getFileHeader("system.json");
        InputStream inputStream = zipFile.getInputStream(fileHeader);
        JSONParser jsonParser = new JSONParser();
        Reader reader = new InputStreamReader(inputStream);
        object = (JSONObject) jsonParser.parse(reader);

        system = new ParticleSystem((Integer) object.get("particle_type"), world, camera);

        JSONObject texture = (JSONObject) (object.get("texture"));
        String[][] paths = (String[][]) texture.get("texture");
        if ((Boolean) texture.get("random")) {
            RandomParticleTexture particleTexture = new RandomParticleTexture();
            if ((Boolean) texture.get("animated")) {
                for (String[] pt: paths) {
                    Texture[] textures = new Texture[pt.length];
                    for (int i = 0; i<pt.length; i++) {
                        textures[i] = new Texture(pt[i]);
                    }
                    particleTexture.addTexture(new AnimatedTexture(textures, (Float) texture.get("fps")));
                }
            } else {
                for (String[] pt: paths) {
                    particleTexture.addTexture(pt[0]);
                }
            }
            system.setTexture(particleTexture);
        } else {
            ParticleTexture particleTexture;
            if ((Boolean) texture.get("animated")) {
                String[] pt = paths[0];
                    Texture[] textures = new Texture[pt.length];
                    for (int i = 0; i<pt.length; i++) {
                        textures[i] = new Texture(pt[i]);
                    }
                particleTexture = new ParticleTexture(new AnimatedTexture(textures, (Float) texture.get("fps")));

            } else {
                String[] pt = paths[0];
                    particleTexture = new ParticleTexture(pt[0]);

            }
            system.setTexture(particleTexture);
        }

        JSONObject rules = (JSONObject) (object.get("rules"));
        ParticleRules particleRules = new ParticleRules();

        JSONObject particleEmissionDuration = (JSONObject) rules.get("ParticleEmissionDuration");
        if((Boolean) particleEmissionDuration.get("infinite")) particleRules.setDuration(new ParticleEmissionDuration(true));
        else particleRules.setDuration(new ParticleEmissionDuration((Float) particleEmissionDuration.get("duration")));

        if (rayHandler != null) {
            JSONObject particleEmissionLight = (JSONObject) rules.get("ParticleEmissionLight");
            Float[] color = (Float[]) particleEmissionLight.get("color");
            Float[] distance = (Float[]) particleEmissionLight.get("distance");
            if ((Boolean) particleEmissionLight.get("random")) {
                particleRules.setLight(new ParticleEmissionLightRandom(rayHandler, (Integer) particleEmissionLight.get("rays"), new Color(color[0], color[1], color[2], color[3]), distance[0], distance[1]));
            } else particleRules.setLight(new ParticleEmissionLight(rayHandler, (Integer) particleEmissionLight.get("rays"), new Color(color[0], color[1], color[2], color[3]), distance[0]));
        } else particleRules.setLight(new ParticleEmissionLight());

        JSONObject particleEmissionNumber = (JSONObject) rules.get("ParticleEmissionNumber");
        Integer[] number = (Integer[]) particleEmissionNumber.get("number");
        if ((Boolean) particleEmissionNumber.get("random")) {
            ParticleEmissionNumberRandom nrzer = new ParticleEmissionNumberRandom((Integer) particleEmissionNumber.get("mode"), number[0], number[1]);
            nrzer.setDelay((Integer) particleEmissionNumber.get("delay"));
            particleRules.setNumber(nrzer);
        } else {
            ParticleEmissionNumber nrzer = new ParticleEmissionNumber((Integer) particleEmissionNumber.get("mode"), number[0]);
            nrzer.setDelay((Integer) particleEmissionNumber.get("delay"));
            particleRules.setNumber(nrzer);
        }

        JSONObject particleLife = (JSONObject) rules.get("ParticleLife");
        Integer[] life = (Integer[]) particleLife.get("life");
        if((Boolean)particleLife.get("random")) {
            particleRules.setLife(new ParticleLifeRandom(life[0], life[1], (Boolean)particleLife.get("outer")));
        } else particleRules.setLife(new ParticleLife(life[0], (Boolean)particleLife.get("outer")));

        system.setRules(particleRules);

        PhysicManager physicManager = new PhysicManager();
        JSONObject physics = (JSONObject) (object.get("physics"));
        JSONObject force = (JSONObject) (physics.get("force"));
        force.forEach(new BiConsumer() {
            @Override
            public void accept(Object o, Object o2) {
                String name = (String) o;
                double[] values = (double[]) o2;

                switch (name) {
                    case "BrownianForce":
                        physicManager.addForce(new BrownianForce((float) values[0], (float) values[1], (long) values[2], values[3]));
                        break;
                    case "LinearForce":
                        physicManager.addForce(new LinearForce((float) values[0], (float) values[1]));
                        break;
                    case "RandomLinearForce":
                        physicManager.addForce(new RandomLinearForce((float) values[0], (float) values[1], (float) values[2], (float) values[3]));
                        break;
                    case "RadialForce":
                        physicManager.addForce(new RadialForce((float) values[0]));
                        break;
                    case "RandomRadialForce":
                        physicManager.addForce(new RandomRadialForce((float) values[0], (float) values[1]));
                        break;
                    default:

                        break;
                }

            }        });

        system.setPhysicManager(physicManager);

        JSONObject position = (JSONObject) (object.get("position"));
        system.setParticlesPosition((float) position.get("x"), (float) position.get("y"));

        reader.close();
    }
}
