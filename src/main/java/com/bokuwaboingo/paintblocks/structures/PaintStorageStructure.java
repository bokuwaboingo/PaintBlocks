package com.bokuwaboingo.paintblocks.structures;

import com.bokuwaboingo.paintblocks.PaintBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class PaintStorageStructure extends Structure<NoFeatureConfig>
{
	public PaintStorageStructure(Codec<NoFeatureConfig> codec)
	{
		super(codec);
	}

	@Override
	public IStartFactory<NoFeatureConfig> getStartFactory()
	{
		return PaintStorageStructure.Start::new;
	}
	
	@Override
	public String getStructureName() {
		return PaintBlocks.MOD_ID + ":paint_storage";
	}
	
	@Override
	public GenerationStage.Decoration getDecorationStage() {
		return GenerationStage.Decoration.SURFACE_STRUCTURES;
	}
	
	public static class Start extends StructureStart<NoFeatureConfig>
	{

		public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference, long seed)
		{
			super(structure, chunkX, chunkZ, boundingBox, reference, seed);
		}

		@Override
		public void func_230364_a_(DynamicRegistries manager, ChunkGenerator generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig config)
		{
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			
			int x = (chunkX << 4) + 7;
			int z = (chunkZ << 4) + 7;
			int y = generator.getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG) - 1;
			BlockPos pos = new BlockPos(x, y, z);
			
			PaintStoragePieces.start(templateManager, pos, rotation, this.components, this.rand);
			
			this.recalculateStructureSize();
		}
	}
}