package com.bokuwaboingo.paintblocks.items;

import com.bokuwaboingo.paintblocks.PaintBlocks;
import com.bokuwaboingo.paintblocks.blocks.RainbowBlock;

import net.minecraft.item.*;

public class RainbowBlockItem extends BlockItem
{
	public RainbowBlockItem(RainbowBlock blockIn)
	{
		super(blockIn, new Item.Properties().group(PaintBlocks.PB_GROUP));
	}
}
