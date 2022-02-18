package net.luis.xores.event.registry;

import net.luis.xores.XOres;
import net.luis.xores.client.render.layer.ModElytraLayer;
import net.luis.xores.common.item.ElytraChestplateItem;
import net.luis.xores.init.ModItems;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.EntityRenderersEvent.AddLayers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;;

/**
 * {@link AddLayers} event handler
 * 
 * @author Luis-st
 */

@EventBusSubscriber(modid = XOres.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class OnEntityRenderersEvent {
	
	/**
	 * add the {@link ModElytraLayer} for all {@link ElytraChestplateItem}s to the {@link PlayerRenderer}
	 */
	@SubscribeEvent
	public static void addLayers(EntityRenderersEvent.AddLayers event) {
		for (String skin : event.getSkins()) {
			PlayerRenderer playerRenderer = event.getSkin(skin);
			playerRenderer.addLayer(new ModElytraLayer<>(playerRenderer, event.getEntityModels(), ModItems.DIAMOND_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/diamond_elytra.png")));
			playerRenderer.addLayer(new ModElytraLayer<>(playerRenderer, event.getEntityModels(), ModItems.NETHERITE_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/netherite_elytra.png")));
			playerRenderer.addLayer(new ModElytraLayer<>(playerRenderer, event.getEntityModels(), ModItems.ENDERITE_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/enderite_elytra.png")));
			playerRenderer.addLayer(new ModElytraLayer<>(playerRenderer, event.getEntityModels(), ModItems.NIGHT_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/night_elytra.png")));
		}
	}
	
}
