package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.utils.Json;
import org.junit.Test;

public class JsonSerTest {

    @Test
    public void test() {

        Json json = new Json();

        String jsd = json.prettyPrint(new Fan(12.25f, 24.55f, 20.45f));

        System.out.println(jsd);

        Fan fan = json.fromJson(Fan.class,jsd);

        System.out.println(fan);

    }

}