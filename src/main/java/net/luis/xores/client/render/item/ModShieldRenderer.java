package net.luis.xores.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.luis.xores.common.item.modded.ModShieldItem;
import net.luis.xores.init.ModItems;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;

/**
 * extension of {@link BlockEntityWithoutLevelRenderer},<br>
 * used to render all mod {@link ShieldItem}s
 * 
 * @author Luis-st
 * 
 * @see {@link ModShieldItem}
 * @see {@link ModItems}
 */

public class ModShieldRenderer extends BlockEntityWithoutLevelRenderer {

	/**
	 * the texture of the shield as a {@link ResourceLocation}
	 */
	protected final ResourceLocation shieldTexture;
	
	/**
	 * the model for the {@link ShieldItem}
	 */
	protected final ShieldModel shieldModel;
	
	/**
	 * constructor for the {@link ModShieldRender}
	 */
	public ModShieldRenderer(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet modelSet, ResourceLocation shieldTexture) {
		super(renderDispatcher, modelSet);
		this.shieldTexture = shieldTexture;
		this.shieldModel = new ShieldModel(modelSet.bakeLayer(ModelLayers.SHIELD));
	}
	
	/**
	 * render the {@link ModShieldRenderer#shieldModel} with the {@link ModShieldRenderer#shieldTexture}
	 */
	@Override
	public void renderByItem(ItemStack stack, TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay) {
		poseStack.pushPose();
		poseStack.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(bufferSource, this.shieldModel.renderType(this.shieldTexture), true, stack.hasFoil());
        this.shieldModel.handle().render(poseStack, vertexconsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.shieldModel.plate().render(poseStack, vertexconsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
	}

}