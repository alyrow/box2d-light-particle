package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageColour extends PhysicForce {

    private Color[][] matrix;

    public ImageColour(BufferedImage image) {
        matrix = new Color[image.getWidth()][image.getHeight()];
        java.awt.Color c;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                c = new java.awt.Color(image.getRGB(i, j));
                matrix[i][matrix[0].length - j - 1] = new Color(c.getRed() / 256f, c.getGreen() / 256f, c.getBlue() / 256f, 1f);
            }
        }
    }

    int x, y;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        x = (int) (particle.x / Gdx.graphics.getWidth() * matrix.length);
        y = (int) (particle.y / Gdx.graphics.getHeight() * matrix[0].length);
        try {
            particle.setLightColor(matrix[x][y]);


        } catch (ArrayIndexOutOfBoundsException i) {
            particle.setLightColor(matrix[MathUtils.clamp(x, 0, matrix.length - 1)][MathUtils.clamp(y, 0, matrix[0].length - 1)]);
        }
        return Vector2.Zero;
    }

//    public static ImageColour get() {
//        try {
//            return new ImageColour(ImageIO.read(new File("D:\\i.png")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
