package com.alyrow.gdx.particle.utils;

import com.badlogic.gdx.math.Vector2;

public class InformationHolder {

    public MetaDataPacket packet;
    public Vector2[][] map;

    public InformationHolder() {
        packet = new MetaDataPacket();
    }

    public InformationHolder(byte[] bytes, Vector2[][] map) {
        packet = new MetaDataPacket(bytes);
        this.map = map;
    }

    public InformationHolder(MetaDataPacket packet, Vector2[][] map) {
        this.packet = packet;
        this.map = map;
    }
}
