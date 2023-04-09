package com.github.allinkdev.alwaysday.mixin;

import com.github.allinkdev.alwaysday.AlwaysDay;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftServer.class)
public final class MinecraftServerMixin {
    @Redirect(method = "synchronizeTime", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    private boolean onSynchronizeTime(final GameRules instance,
                                      final GameRules.Key<GameRules.BooleanValue> key) {
        return AlwaysDay.getModifiedBooleanValue(instance, key);
    }
}
