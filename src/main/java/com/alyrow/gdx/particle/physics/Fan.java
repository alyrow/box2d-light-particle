package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.utils.ImageSerializable;
import com.alyrow.gdx.particle.utils.InformationHolder;
import com.alyrow.gdx.particle.utils.MetaDataPacket;
import com.badlogic.gdx.math.Vector2;

public class Fan extends PhysicForce implements ImageSerializable {

    // Black hole specific
    private float speed;
    private Vector2 center;

    public Fan() {
    }

    public Fan(float x, float y, float speed) {
        center = new Vector2(x, y);
        this.speed = speed / 100;
    }

    private Vector2 cache = new Vector2(0, 0);

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        return cache.set(particle.y - center.y, center.x - particle.x).scl(speed);
    }

    @Override
    public MetaDataPacket getByteData() {
        MetaDataPacket packet = new MetaDataPacket();
        packet.addFloat(center.x);
        packet.addFloat(center.y);
        packet.addFloat(speed);
        return packet;
    }

    @Override
    public void setByteData(MetaDataPacket packet) {
        center = new Vector2();
        center.x = packet.getFloat();
        center.y = packet.getFloat();
        speed = packet.getFloat();
    }
}