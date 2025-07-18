package com.caelin.endercattle.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;

public class EndChickenSpawnEggItem extends SpawnEggItem {

    private static final float CONSUME_DURATION = 1.6F; // Duration for the consumption (1.6 seconds)
    private static final int COOLDOWN_TICKS = 20; // Cooldown duration in ticks (slightly higher to ensure use)

    // Class-level variables to track sound playing time and cooldown
    private long lastUseTime = -1L; // Track the last time the item was used
    private long lastSoundPlayedTime = -1L; // Track the last time the eating sound was played
    private static final int SOUND_INTERVAL_TICKS = 4; // Eating sound interval in ticks

    public EndChickenSpawnEggItem(EntityType<? extends Mob> defaultType, Properties properties) {
        super(defaultType, properties);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.EAT; // The animation for consuming (eating)
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return (int) (CONSUME_DURATION * 20); // Convert 1.6 seconds to ticks
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        long currentTime = level.getGameTime();

        if (player.isShiftKeyDown()) {
            // Start consuming the item
            player.startUsingItem(hand);
            lastUseTime = currentTime; // Update the last use time to prevent immediate reuse
            return InteractionResult.CONSUME; // Proceed with the consumption
        }

        // Fallback to default spawn egg behavior
        return super.use(level, player, hand);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        if (level.isClientSide && entity instanceof Player player) {
            long currentTime = level.getGameTime();

            // Play sound at intervals during consumption
            if ((currentTime - lastSoundPlayedTime) >= SOUND_INTERVAL_TICKS) {
                SimpleSoundInstance soundInstance = new SimpleSoundInstance(
                        SoundEvents.GENERIC_EAT.value(),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F,
                        level.getRandom(),
                        player.getX(),
                        player.getY(),
                        player.getZ()
                );
                Minecraft.getInstance().getSoundManager().play(soundInstance);
                lastSoundPlayedTime = currentTime;
            }

            // Ensure the item is fully consumed after the duration
            if (remainingUseDuration <= 0) {
                finishUsingItem(stack, level, entity); // Trigger finish after the consumption time
                lastUseTime = currentTime; // Reset the cooldown time only after the consumption finishes
            }
        }
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        // Ensure we're on the server-side
        if (!level.isClientSide && entity instanceof Player player) {
            // Shrink the stack after consumption
            stack.shrink(1);

            // Apply the burp sound after the consumption is complete
            level.playSound(null,
                    player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_BURP,
                    player.getSoundSource(),
                    1.0F, 1.0F);

            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2D);
            player.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(150.0D);
            player.getAttribute(Attributes.WATER_MOVEMENT_EFFICIENCY).setBaseValue(0.4D);
            player.getAttribute(Attributes.SNEAKING_SPEED).setBaseValue(0.3D);
            player.getAttribute(Attributes.STEP_HEIGHT).setBaseValue(3.0);
        }


        return stack; // Return the modified stack
    }
}