package com.github.allinkdev.alwaysday;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public final class AlwaysDay implements ModInitializer {
    public static final GameRules.Key<GameRules.BooleanValue> RULE = GameRuleRegistry.register("alwaysDay",
            GameRules.Category.UPDATES,
            GameRuleFactory.createBooleanRule(false));

    public static boolean getModifiedBooleanValue(final GameRules instance,
                                                  final GameRules.Key<GameRules.BooleanValue> key) {
        final boolean value = instance.getBoolean(key);

        if (key.equals(GameRules.RULE_DAYLIGHT)) {
            final boolean isAlwaysDay = instance.getBoolean(AlwaysDay.RULE);

            if (isAlwaysDay) {
                return false;
            }
        }

        return value;
    }

    @Override
    public void onInitialize() {
        //
    }
}
