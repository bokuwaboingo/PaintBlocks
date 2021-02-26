package com.bokuwaboingo.paintblocks.init;

import java.util.Locale;

import com.bokuwaboingo.paintblocks.PaintBlocks;
import com.bokuwaboingo.paintblocks.structures.*;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
	
	@SubscribeEvent
	public static void registerStructurePieces(RegistryEvent.Register<Structure<?>> event)
	{
		Registry.register(Registry.STRUCTURE_PIECE, "PAINT_STORAGE".toLowerCase(Locale.ROOT), PAINT_STORAGE_PIECE);
	}
}
