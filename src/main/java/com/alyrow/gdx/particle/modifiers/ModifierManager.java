package com.alyrow.gdx.particle.modifiers;

import com.badlogic.gdx.utils.Array;

public class ModifierManager {

    public Array<Modifier> modifiers = new Array<Modifier>();

    public ModifierManager() {}

    public void addModifier(Modifier modifier) {
        modifiers.add(modifier);
    }

    public void removeModifier(Modifier modifier) {
        modifiers.removeValue(modifier, true);
    }
}
