package com.alyrow.gdx.particle;

import box2dLight.PointLight;
import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.alyrow.gdx.particle.texture.AnimatedTexture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * @author alyrow
 * This class represents a particle
 * Well, you will never use it because particles systems do it for you :)
 * @see ParticleSystem
 */
public class Particle {
    public static float ratio = 1;

    private final boolean worldPhysic;
    private AnimatedTexture anTex;
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
    public PointLight light;
    private int type;

    public Particle(PointLight light, SpriteBatch batch, float life, boolean outer, Texture texture, Camera camera, ParticleSystem system, float x, float y, PhysicParticle physicParticle, boolean worldPhysic, int type) {
        this.life = life * 1000; //s to ms
        this.outer = outer;
        this.texture = texture;
        this.system = system;
        this.x = x;
        this.y = y;
        this.physicParticle = physicParticle;
        this.physicParticle.setThisNoSenseSetterForParticleThisIsALittleReferenceToTheOrganisationOfThisLibDOnTTakeNotePlease(this);
        this.worldPhysic = worldPhysic;
        this.type = type;

        if (texture instanceof AnimatedTexture) {
            //Gdx.app.log("AnimatedTexture", "true");
            anTex = (AnimatedTexture) texture;
            sprite = new Sprite(anTex.textures[0]);
        } else sprite = new Sprite(texture);
        if (type == ParticleType.HALO)
            sprite.setColor(light.getColor());
        sprite.setPosition(x, y);
        sprite.setSize(sprite.getWidth() * ratio, sprite.getHeight() * ratio);
        physicParticle.setSize(sprite.getWidth(), sprite.getHeight());

        this.light = light;
        if (light.getDistance() < physicParticle.width / 4) {
            light.setDistance(physicParticle.width / 4);
            Gdx.app.error("Particle", "Light distance was to small : updated to " + physicParticle.width / 4);
        }
        light.setPosition(x, y);

        isInnerScreen = _calculateIfInnerScreen(camera);
        timeEllapsed = TimeUtils.millis();
    }

    public void render(SpriteBatch batch, Camera camera, Array<PhysicForce> forces) {
        if (life <= 0) return;

        if (anTex != null) {
            anTex.render(Gdx.graphics.getDeltaTime());
            sprite.setTexture(anTex.textures[anTex.frame]);
        }

        float delta = Gdx.graphics.getDeltaTime() * 1000;
        isInnerScreen = _calculateIfInnerScreen(camera);
        if (outer && !isInnerScreen) life = life - delta;
        else if (!outer) life = life - delta;
        //Gdx.app.log("inner", String.valueOf(isInnerScreen));

        //Put all physics here
//        Array<Vector2> vectors = new Array<>();
//        forces.forEach(force -> vectors.add(force.getForce(physicParticle)));
//        float vx = 0, vy = 0;
//        for (int i = 0; i < vectors.size; i++) {
//            vx += vectors.get(i).x;
//            vy += vectors.get(i).y;
//        }
        Vector2 netForce = new Vector2(0, 0);
        forces.forEach(force -> netForce.add(force.getForce(physicParticle)));
        netForce.scl(1 / physicParticle.mass); // now it's not force, it's acceleration

        if (!worldPhysic) {
            physicParticle.x += netForce.x / (delta + 1);
            physicParticle.y += netForce.y / (delta + 1);
        } else {
            physicParticle.body.setLinearVelocity(netForce.x, netForce.y);
            physicParticle.x = physicParticle.body.getPosition().x - physicParticle.width / 2;
            physicParticle.y = physicParticle.body.getPosition().y - physicParticle.height / 2;
        }
        x = physicParticle.x;
        y = physicParticle.y;
        //Gdx.app.log("position", x+", "+y);
        sprite.setPosition(physicParticle.x, physicParticle.y);
        light.setPosition(physicParticle.x + physicParticle.width / 2, physicParticle.y + physicParticle.height / 2);

        if(type == ParticleType.HALO && !sprite.getColor().equals(light.getColor())) {
            sprite.setColor(light.getColor());
        }


        if (life < 500) {
            sprite.setAlpha(life / 500);
        }
        if (life <= 0) {
            dispose();
        } else sprite.draw(batch);
    }

    public boolean _calculateIfInnerScreen(Camera camera) {
        float w = camera.viewportWidth;//batch.getProjectionMatrix().getScaleX();
        float h = camera.viewportHeight;//batch.getProjectionMatrix().getScaleY();
        float x = 0;//camera.position.x;//batch.getProjectionMatrix().getTranslation(new Vector3()).x;
        float y = 0;//camera.position.y;//batch.getProjectionMatrix().getTranslation(new Vector3()).y;
        //Gdx.app.log("w, h, x, y", w +", "+h+", "+", "+x+", "+y);

        return this.x >= x && this.x < x + w && this.y >= y && this.y < y + h;
    }

    public void dispose() {
        system.particles.removeValue(this, true);
        if (worldPhysic) {
            physicParticle.world.destroyBody(physicParticle.body);
        }
        //light.dispose();
        light.remove(true);
    }
}
