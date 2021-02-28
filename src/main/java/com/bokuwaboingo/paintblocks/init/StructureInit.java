package com.bokuwaboingo.paintblocks.init;

import com.bokuwaboingo.paintblocks.PaintBlocks;
import com.bokuwaboingo.paintblocks.structures.*;
import com.google.common.collect.*;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.*;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.settings.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.*;

@Mod.EventBusSubscriber(modid = PaintBlocks.MOD_ID, bus = Bus.MOD)
public class StructureInit
{
	public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, PaintBlocks.MOD_ID);
	
	public static IStructurePieceType PAINT_STORAGE_PIECE = PaintStoragePieces.Piece::new;
	
	public static final RegistryObject<PaintStorageStructure> PAINT_STORAGE = STRUCTURES.register("paint_storage", () -> new PaintStorageStructure(NoFeatureConfig.field_236558_a_));
	
	public static void registerStructurePieces()
	{
		Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(PaintBlocks.MOD_ID, "paint_storage"), PAINT_STORAGE_PIECE);
	}
	
	public static void registerPaintStorage()
	{
		Structure.NAME_STRUCTURE_BIMAP.put("paintblocks:paint_storage", PAINT_STORAGE.get());

        Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder()
                                    .addAll(Structure.field_236384_t_)
                                    .add(PAINT_STORAGE.get())
                                    .build();

        DimensionStructuresSettings.field_236191_b_ = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                                                      .putAll(DimensionStructuresSettings.field_236191_b_)
                                                      .put(PAINT_STORAGE.get(), new StructureSeparationSettings(10, 5, 1234567890))
                                                      .build();
	}
	
	public static void registerConfiguration()
	{
		Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(PaintBlocks.MOD_ID, "configured_paint_storage"), PAINT_STORAGE.get().withConfiguration(NoFeatureConfig.field_236559_b_));
		
		FlatGenerationSettings.STRUCTURES.put(PAINT_STORAGE.get(), PAINT_STORAGE.get().withConfiguration(NoFeatureConfig.field_236559_b_));
	}
}
