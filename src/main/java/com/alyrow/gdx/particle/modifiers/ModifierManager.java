package com.alyrow.gdx.particle.modifiers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class ModifierManager implements Json.Serializable {

    public Array<Modifier> modifiers = new Array<Modifier>();

    public ModifierManager() {}

    public void addModifier(Modifier...modifiers) {
        this.modifiers.addAll(modifiers);
    }

    public void removeModifier(Modifier modifier) {
        modifiers.removeValue(modifier, true);
    }

    @Override
    public void write(Json json) {
        json.writeValue("modifiers", modifiers, Modifier.class);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {

    }
}
