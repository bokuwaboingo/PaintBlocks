package com.bokuwaboingo.paintblocks.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class RainbowBlock extends Block
{
	public RainbowBlock()
	{
		super(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.8f).sound(SoundType.STONE));
	}
}
