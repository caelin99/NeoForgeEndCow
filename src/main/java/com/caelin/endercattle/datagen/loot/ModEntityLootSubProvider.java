package com.caelin.endercattle.datagen.loot;

import com.caelin.endercattle.EnderCattle;
import com.caelin.endercattle.common.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.fml.common.Mod;

import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static com.caelin.endercattle.EnderCattle.END_CHICKEN_SPAWN_EGG;
import static com.caelin.endercattle.EnderCattle.END_COW_SPAWN_EGG;

public class ModEntityLootSubProvider extends EntityLootSubProvider  {

        public ModEntityLootSubProvider(HolderLookup.Provider lookupProvider) {
            super(FeatureFlags.DEFAULT_FLAGS, lookupProvider);
        }

    @Override
    public Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntities.ENTITY_TYPES.getEntries()
                .stream()
                .map(e -> (EntityType<?>) e.value());
    }

    @Override
    public void generate() {
            this.add(ModEntities.END_COW.get(), LootTable.lootTable()
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1,2))
                            .add(EmptyLootItem.emptyItem().setWeight(30))
                            .add(LootItem.lootTableItem(Items.BEEF).setWeight(70))
                    )
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(END_COW_SPAWN_EGG.asItem()).setWeight(5))
                            .add(EmptyLootItem.emptyItem().setWeight(95))
                    ));


            this.add(ModEntities.END_CHICKEN.get(), LootTable.lootTable()
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(2,3))
                            .add(EmptyLootItem.emptyItem().setWeight(30))
                            .add(LootItem.lootTableItem(Items.FEATHER).setWeight(60))
                            .add(LootItem.lootTableItem(Items.EGG).setWeight(60))
                    )
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(END_CHICKEN_SPAWN_EGG.asItem()).setWeight(5))
                            .add(EmptyLootItem.emptyItem().setWeight(95))
                    ));
    }
}
