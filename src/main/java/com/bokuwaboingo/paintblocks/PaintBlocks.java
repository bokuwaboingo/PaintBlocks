package com.bokuwaboingo.paintblocks;

import java.util.*;

import com.bokuwaboingo.paintblocks.init.*;

import net.minecraft.item.*;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("paintblocks")
public class PaintBlocks
{
	public static final String MOD_ID = "paintblocks";
	public static final PaintBlocksItemGroup PB_GROUP = new PaintBlocksItemGroup();
	
    public PaintBlocks()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addGenericListener(Structure.class, this::onRegisterStructures);
        
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        StructureInit.STRUCTURES.register(bus);

        MinecraftForge.EVENT_BUS.addListener(this::biomeModification);
        MinecraftForge.EVENT_BUS.addListener(this::addDimensionalSpacing);
    }
    
    public void onRegisterStructures(final RegistryEvent.Register<Structure<?>> event) {
        StructureInit.registerPaintStorage();
        StructureInit.registerConfiguration();
    }
    
    public void biomeModification(final BiomeLoadingEvent event)
	{
    	if (event.getCategory() == Biome.Category.PLAINS) event.getGeneration().getStructures().add(() -> StructureInit.PAINT_STORAGE.get().withConfiguration(NoFeatureConfig.field_236559_b_));
	}
    
    @SuppressWarnings("resource")
	public void addDimensionalSpacing(final WorldEvent.Load event)
    {
        if (event.getWorld() instanceof ServerWorld)
        {
            ServerWorld serverWorld = (ServerWorld)event.getWorld();

            if (serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getDimensionKey().equals(World.OVERWORLD)) return;

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
            tempMap.put(StructureInit.PAINT_STORAGE.get(), DimensionStructuresSettings.field_236191_b_.get(StructureInit.PAINT_STORAGE.get()));
            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
        }
   }
    
    public static class PaintBlocksItemGroup extends ItemGroup
    {
		public PaintBlocksItemGroup()
		{
			super("paint_blocks");
		}

		@Override
		public ItemStack createIcon()
		{
			return ItemInit.RAINBOW_BLOCK.get().getDefaultInstance();
		}
    }
}