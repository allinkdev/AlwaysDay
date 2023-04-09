package com.github.allinkdev.alwaysday.mixin;

import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ServerLevel.class)
public interface ServerLevelAccessor extends LevelAccessor {
    @Invoker
    void invokeAdvanceWeatherCycle();
    @Invoker
    void invokeSetDayTime(long time);
}
