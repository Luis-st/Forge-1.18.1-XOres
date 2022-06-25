package net.luis.xores.event.registry;

import net.luis.xores.XOres;
import net.luis.xores.client.render.layer.XOresElytraLayer;
import net.luis.xores.world.item.ElytraChestplateItem;
import net.luis.xores.world.item.XOresItems;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
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
	 * add the {@link XOresElytraLayer} for all {@link ElytraChestplateItem}s to<br>
	 * the {@link PlayerRenderer} and the {@link ArmorStandRenderer}
	 */
	@SubscribeEvent
	public static void addLayers(EntityRenderersEvent.AddLayers event) {
		for (String skin : event.getSkins()) {
			PlayerRenderer playerRenderer = event.getSkin(skin);
			playerRenderer.addLayer(new XOresElytraLayer<>(playerRenderer, event.getEntityModels(), XOresItems.DIAMOND_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/diamond_elytra.png")));
			playerRenderer.addLayer(new XOresElytraLayer<>(playerRenderer, event.getEntityModels(), XOresItems.NETHERITE_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/netherite_elytra.png")));
			playerRenderer.addLayer(new XOresElytraLayer<>(playerRenderer, event.getEntityModels(), XOresItems.ENDERITE_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/enderite_elytra.png")));
			playerRenderer.addLayer(new XOresElytraLayer<>(playerRenderer, event.getEntityModels(), XOresItems.NIGHT_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/night_elytra.png")));
		}
		ArmorStandRenderer armorStandRenderer = event.getRenderer(EntityType.ARMOR_STAND);
		armorStandRenderer.addLayer(new XOresElytraLayer<>(armorStandRenderer, event.getEntityModels(), XOresItems.DIAMOND_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/diamond_elytra.png")));
		armorStandRenderer.addLayer(new XOresElytraLayer<>(armorStandRenderer, event.getEntityModels(), XOresItems.NETHERITE_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/netherite_elytra.png")));
		armorStandRenderer.addLayer(new XOresElytraLayer<>(armorStandRenderer, event.getEntityModels(), XOresItems.ENDERITE_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/enderite_elytra.png")));
		armorStandRenderer.addLayer(new XOresElytraLayer<>(armorStandRenderer, event.getEntityModels(), XOresItems.NIGHT_ELYTRA_CHESTPLATE.get(), new ResourceLocation(XOres.MOD_ID, "textures/entity/night_elytra.png")));
	}
	
}
