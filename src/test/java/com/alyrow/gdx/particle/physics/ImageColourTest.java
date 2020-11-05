package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.modifiers.RandomPositionRectangle;
import com.alyrow.gdx.particle.tester.Tester;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageColourTest {

    public static void main(String[] args) throws IOException {

        BufferedImage i = ImageIO.read(new File("D:\\i.png"));
        Color[][] c = new Color[i.getWidth()][i.getHeight()];
        for(int j = 0; j < i.getWidth(); j++) {
            for(int k = 0; k < i.getHeight(); k++) {
                java.awt.Color f = new java.awt.Color(i.getRGB(j, k));
                c[j][k] = new Color(f.getRed(), f.getGreen(), f.getBlue(), 255);
            }
        }
        new Tester()
                .forModifier(() -> new RandomPositionRectangle(Gdx.graphics.getWidth(), Gdx.graphics.getWidth()))
                .forForce(() -> new ImageColour(c/*Gdx.files.absolute("D:\\i.png")*/))
                .setPc(1000).Test();

    }

}