package com.alyrow.gdx.particle.utils;

import org.junit.Test;

public class MetaDataPacketTest {

    @Test
    public void simpleTest() {

        MetaDataPacket pack = new MetaDataPacket();

        pack.addLong(12);
        pack.addInt(12);
        pack.addFloat(12f);
        pack.addLong(Long.MAX_VALUE / 2);
        pack.addInt(Integer.MAX_VALUE / 2);
        pack.addFloat(Float.MAX_VALUE / 2);

        byte[] bytes = pack.getBytes();

        MetaDataPacket new_pack = new MetaDataPacket(bytes);

        System.out.println(new_pack.getLong());
        System.out.println(new_pack.getInt());
        System.out.println(new_pack.getFloat());
        System.out.println(new_pack.getLong());
        System.out.println(new_pack.getInt());
        System.out.println(new_pack.getFloat());
    }

}