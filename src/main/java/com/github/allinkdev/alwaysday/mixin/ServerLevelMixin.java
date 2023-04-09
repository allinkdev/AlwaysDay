package com.github.allinkdev.alwaysday.mixin;

import com.github.allinkdev.alwaysday.AlwaysDay;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerLevel.class)
public final class ServerLevelMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    private boolean onTick_GetBoolean(final GameRules instance,
                                      final GameRules.Key<GameRules.BooleanValue> key) {
        return AlwaysDay.getModifiedBooleanValue(instance, key);
    }

    @Redirect(method = "tickTime", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    private boolean onTickTime_GetBoolean(final GameRules instance,
                                          final GameRules.Key<GameRules.BooleanValue> key) {
        return AlwaysDay.getModifiedBooleanValue(instance, key);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;advanceWeatherCycle()V"))
    private void onAdvanceWeatherCycle(final ServerLevel instance) {
        final ServerLevelAccessor accessor = (ServerLevelAccessor) instance;

        accessor.invokeAdvanceWeatherCycle();

        final GameRules gameRules = accessor.invokeGetGameRules();
        final boolean isAlwaysDay = gameRules.getBoolean(AlwaysDay.RULE);

        if (!isAlwaysDay) {
            return;
        }

        accessor.invokeSetDayTime(5000L);
    }
}
