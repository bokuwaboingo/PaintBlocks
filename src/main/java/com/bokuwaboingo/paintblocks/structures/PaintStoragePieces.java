package com.bokuwaboingo.paintblocks.structures;

import java.util.*;

import com.bokuwaboingo.paintblocks.PaintBlocks;
import com.bokuwaboingo.paintblocks.init.StructureInit;
import com.google.common.collect.ImmutableMap;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.*;

public class PaintStoragePieces
{
	private static final ResourceLocation PAINT_STORAGE = new ResourceLocation(PaintBlocks.MOD_ID, "paint_storage");
	
	private static final Map<ResourceLocation, BlockPos> OFFSET = ImmutableMap.of(PAINT_STORAGE, new BlockPos(0, 0, 0));
	
	public static void start(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, Random rand)
	{
		BlockPos rotationOffset = new BlockPos(0, 0, 0).rotate(rotation);
		BlockPos blockPos = rotationOffset.add(pos.getX(), pos.getY(), pos.getZ());
		pieces.add(new PaintStoragePieces.Piece(manager, PAINT_STORAGE, blockPos, rotation));
	}
	
	public static class Piece extends TemplateStructurePiece
	{
		private ResourceLocation resourceLocation;
		private Rotation rotation;

		public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotationIn)
		{
			super(StructureInit.PAINT_STORAGE_PIECE, 0);
			this.resourceLocation = resourceLocationIn;
			BlockPos blockpos = PaintStoragePieces.OFFSET.get(resourceLocation);
			this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
			this.rotation = rotationIn;
			this.setupPiece(templateManagerIn);
		}

		public Piece(TemplateManager templateManagerIn, CompoundNBT tagCompound)
		{
			super(StructureInit.PAINT_STORAGE_PIECE, tagCompound);
			this.resourceLocation = new ResourceLocation(tagCompound.getString("Template"));
			this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
			this.setupPiece(templateManagerIn);
		}

		private void setupPiece(TemplateManager templateManager)
		{
			Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
			this.setup(template, this.templatePosition, placementsettings);
		}

		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putString("Template", this.resourceLocation.toString());
			tagCompound.putString("Rot", this.rotation.name());
		}

		@Override
		protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb)
		{}
	}
}
