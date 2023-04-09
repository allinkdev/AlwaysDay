package com.github.allinkdev.alwaysday.mixin;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Level.class)
public interface LevelAccessor {
    @Invoker
    GameRules invokeGetGameRules();
}
