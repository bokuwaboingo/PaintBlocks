package com.bokuwaboingo.paintblocks;

import com.bokuwaboingo.paintblocks.init.*;

import net.minecraft.item.*;
import net.minecraftforge.common.MinecraftForge;
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
        
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public static class PaintBlocksItemGroup extends ItemGroup
    {
		public PaintBlocksItemGroup()
		{
			super(MOD_ID);
		}

		@Override
		public ItemStack createIcon()
		{
			return ItemInit.RAINBOW_BLOCK.get().getDefaultInstance();
		}
    }
}
