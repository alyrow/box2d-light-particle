package com.alyrow.gdx.particle.utils;

import com.badlogic.gdx.utils.ByteArray;

public class MetaDataPacket {

    ByteArray array;

    int index = 0;

    public MetaDataPacket(byte[] bytes) {
        array = new ByteArray(bytes);
    }

    public MetaDataPacket() {
        array = new ByteArray();
    }

//    public void addInt(int i) {
//        array.addAll(ByteBuffer.allocate(Integer.BYTES).putInt(i).array());
//    }

    public void addByte(byte b) {
        array.add(b);
    }

    public void addLong(long l) {
        array.addAll(
                (byte) (l >>> 56),
                (byte) (l >>> 48),
                (byte) (l >>> 40),
                (byte) (l >>> 32),
                (byte) (l >>> 24),
                (byte) (l >>> 16),
                (byte) (l >>> 8),
                (byte) l
        );

    }

    public void addInt(int i) {
        array.addAll(
                (byte) (i >>> 24),
                (byte) (i >>> 16),
                (byte) (i >>> 8),
                (byte) i
        );
    }

    public void addFloat(float l) {
        int intBits = Float.floatToIntBits(l);
        array.addAll(
                (byte) (intBits >> 24),
                (byte) (intBits >> 16),
                (byte) (intBits >> 8),
                (byte) (intBits))
        ;
    }

    public long getLong() {
        long result = 0;
        for (int c = index; index < 8 + c; index++) {
            result <<= 8;
            result |= (array.get(index) & 0xFF);
        }
        return result;
    }

    public int getInt() {
        int result = 0;
        for (int c = index; index < 4 + c; index++) {
            result <<= 8;
            result |= (array.get(index) & 0xFF);
        }
        return result;
    }

    public float getFloat() {
        int intBits = array.get(index) << 24
                | (array.get(index + 1) & 0xFF) << 16
                | (array.get(index + 2) & 0xFF) << 8
                | (array.get(index + 3) & 0xFF);
        index += 4;
        return Float.intBitsToFloat(intBits);
    }

    public byte[] getBytes() {
        byte[] items = array.items;
//        array = null;
        index = 0;
        return items;
    }

}
