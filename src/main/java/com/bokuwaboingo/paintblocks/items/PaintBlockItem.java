package com.bokuwaboingo.paintblocks.items;

import com.bokuwaboingo.paintblocks.PaintBlocks;
import com.bokuwaboingo.paintblocks.blocks.PaintBlock;

import net.minecraft.item.*;

public class PaintBlockItem extends BlockItem
{
	public PaintBlockItem(PaintBlock blockIn)
	{
		super(blockIn, new Item.Properties().group(PaintBlocks.PB_GROUP));
	}	
}
