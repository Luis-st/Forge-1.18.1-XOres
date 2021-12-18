package net.luis.xores;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.luis.xores.init.ModBlockItems;
import net.luis.xores.init.ModBlocks;
import net.luis.xores.init.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(XOres.MOD_ID)
public class XOres {
	
	public static final String MOD_ID = "xores";
	public static final Logger LOGGER = LogManager.getLogger(XOres.class);
	
	public XOres() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModBlocks.BLOCKS.register(modEventBus);
		ModBlockItems.ITEMS.register(modEventBus);
		ModItems.ITEMS.register(modEventBus);
	}
	
}
