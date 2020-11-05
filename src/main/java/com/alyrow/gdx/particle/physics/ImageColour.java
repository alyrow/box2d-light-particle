package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class ImageColour extends PhysicForce {

    private Color[][] matrix;

    public ImageColour(Color[][] matrix) {
        this.matrix = matrix;
    }

    public ImageColour(Pixmap image) {
        matrix = new Color[image.getWidth()][image.getHeight()];

        for (int i = 0; i < image.getWidth(); i++)
            for (int j = 0; j < image.getHeight(); j++)
                matrix[i][matrix[0].length - j - 1] = new Color(image.getPixel(i, j));
    }

    public ImageColour(FileHandle file) {
        this(new Pixmap(file));
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

}
