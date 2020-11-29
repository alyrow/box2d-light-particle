package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.modifiers.Modifier;
import com.alyrow.gdx.particle.modifiers.RandomColors;
import com.alyrow.gdx.particle.tester.Tester;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.function.Supplier;

public class PathFollowTest {

    public static void main(String[] args) {

        new Tester()
                .setLightOn(false)
                .setPc(1000)
                .forModifier((Supplier<Modifier>) RandomColors::new)
                .forForce(() -> {
                    int x = Gdx.graphics.getWidth() / 2;
                    int y = Gdx.graphics.getHeight() / 2;
                    return new PathFollow(new float[]{

                            // Square
//                            x - 200, y - 200,
//                            x - 200, y + 200,
//                            x + 200, y + 200,
//                            x + 200, y - 200

                            // Game Pad
                            x - 250, y - 200,
                            x - 200, y + 50,
                            x - 150, y + 100,
                            x + 150, y + 100,
                            x + 200, y + 50,
                            x + 250, y - 200,
                            x + 100, y - 200,
                            x, y - 150,
                            x - 100, y - 200

                    }, new Vector2(x, y), 5, 10);
                })
                .Test();

    }

}