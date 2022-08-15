package net.luis.xores.data.provider.loottable;

import java.util.stream.Collectors;

import net.luis.xores.world.level.block.XOBlocks;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

/**
 * 
 * @author Luis-st
 *
 */

public class XOBlockLoot extends BlockLoot {
	
	XOBlockLoot() {
		
	}
	
	@Override
	protected void addTables() {
		for (Block block : XOBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList())) {
			this.dropSelf(block);
		}
	}
	
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return XOBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
	}

}