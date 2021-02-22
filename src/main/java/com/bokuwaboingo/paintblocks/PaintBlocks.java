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
	public static final PaintItemGroup P_GROUP = new PaintItemGroup();
	
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
			super("paint_blocks");
		}

		@Override
		public ItemStack createIcon()
		{
			return ItemInit.RAINBOW_BLOCK.get().getDefaultInstance();
		}
    }
    
    public static class PaintItemGroup extends ItemGroup
    {
		public PaintItemGroup()
		{
			super("paint");
		}

		@Override
		public ItemStack createIcon()
		{
			return ItemInit.RAINBOW_PAINT.get().getDefaultInstance();
		}
    }
}
