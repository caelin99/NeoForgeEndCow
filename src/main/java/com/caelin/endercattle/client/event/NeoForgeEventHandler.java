//package com.caelin.endercattle.client.event;
//
//import com.caelin.endercattle.EnderCattle;
//import com.caelin.endercattle.common.ModEntities;
//import com.mojang.logging.LogUtils;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EntitySpawnReason;
//import net.minecraft.world.entity.ai.attributes.Attributes;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
//import net.neoforged.neoforge.event.entity.player.PlayerEvent;
//import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
//import net.neoforged.neoforge.event.tick.PlayerTickEvent;
//import org.slf4j.Logger;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.caelin.endercattle.EnderCattle.END_CHICKEN_SPAWN_EGG;
//
//@EventBusSubscriber(modid = EnderCattle.MODID, bus = EventBusSubscriber.Bus.GAME)
//public class NeoForgeEventHandler {
//
//    static Logger LOGGER = LogUtils.getLogger();
//
//    @SubscribeEvent
//    public static void playerEatSpawnEgg(PlayerInteractEvent.RightClickEmpty event) {
//
//        if (event.getEntity() instanceof Player) {
//            Player player = event.getEntity();
//            boolean isSneaking = player.isShiftKeyDown();
//            boolean isPlayerHoldingSpawnEgg = player.isHolding(stack ->
//                    stack.getItem() == END_CHICKEN_SPAWN_EGG.get() &&
//                            stack.getCount() > 0);
//
//            if (isPlayerHoldingSpawnEgg && isSneaking) {
//                LOGGER.info("Player is holding End Chicken Spawn Egg while SNEAKY");
//
//                player.startUsingItem(InteractionHand.MAIN_HAND);
//
//                ItemStack spawnEggStack = player.getMainHandItem();
//                spawnEggStack.shrink(1);
//
//                player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35D);
//                player.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(150.0D);
//                player.getAttribute(Attributes.WATER_MOVEMENT_EFFICIENCY).setBaseValue(0.5D);
//                player.getAttribute(Attributes.SNEAKING_SPEED).setBaseValue(0.3D);
//                player.getAttribute(Attributes.STEP_HEIGHT).setBaseValue(4.5D);
//            }
//        }
//    }
//
//    private static final Map<Player, Long> keyPressStartTime = new HashMap<>();
//
//    @SubscribeEvent
//    public static void playerHoldKeyForSpawnEgg(PlayerTickEvent.Post event) {
//        if (event.getEntity() instanceof Player player) {
//
//
//            // Check if the player is holding the key and the player is holding a spawn egg
//            if (player.isShiftKeyDown() && player.isHolding(stack -> stack.getItem() == END_CHICKEN_SPAWN_EGG.get())) {
//
//                // Store the time when the key press starts
//                long currentTime = System.currentTimeMillis();
//
//                // Store the starting time if not already
//                if (!keyPressStartTime.containsKey(player)) {
//                    keyPressStartTime.put(player, currentTime);
//                }
//
//                // Check if 2 seconds have passed
//                if (currentTime - keyPressStartTime.get(player) >= 2000) {
//                    // Trigger the action (eat the spawn egg)
//                    triggerSpawnEggUse(player);
//
//                    // Reset the timer
//                    keyPressStartTime.remove(player);
//                }
//            } else {
//                // If the key is released, reset the timer
//                keyPressStartTime.remove(player);
//            }
//        }
//    }
//
//    private static void triggerSpawnEggUse(Player player) {
//        LOGGER.info("Player held the key for 2 seconds, using the spawn egg...");
//
//        // Decrease the spawn egg stack
//        ItemStack spawnEggStack = player.getMainHandItem();
//        spawnEggStack.shrink(1); // Simulate consuming the spawn egg
//
//        // Spawn the entity (the End Chicken in this case)
//        if (!player.level().isClientSide) {
//            Entity entity = ModEntities.END_CHICKEN.get().create(player.level(), EntitySpawnReason.SPAWN_ITEM_USE);
//            if (entity != null) {
//                entity.moveTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
//                player.level().addFreshEntity(entity);
//            }
//        }
//
//        // Optionally, play a sound or trigger an effect
//        player.playSound(SoundEvents.PLAYER_BURP, 1.0F, 1.0F);
//    }
//}
