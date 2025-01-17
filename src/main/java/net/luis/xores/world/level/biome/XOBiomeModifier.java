/*
 * XOres
 * Copyright (C) 2024 Luis Staudt
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package net.luis.xores.world.level.biome;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.luis.xores.XOres;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

public record XOBiomeModifier(@NotNull HolderSet<Biome> overworldBiomes, @NotNull HolderSet<PlacedFeature> overworldFeatures, @NotNull HolderSet<Biome> mountainPeakBiomes, @NotNull HolderSet<PlacedFeature> mountainPeakFeatures,
							  @NotNull HolderSet<Biome> endBiomes, @NotNull HolderSet<PlacedFeature> endFeatures) implements BiomeModifier {
	
	public static final ResourceKey<BiomeModifier> KEY = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(XOres.MOD_ID, "ore_biome_modifier"));
	
	public static final MapCodec<XOBiomeModifier> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
		return instance.group(Biome.LIST_CODEC.fieldOf("overworld_biomes").forGetter((biomeModifier) -> {
			return biomeModifier.overworldBiomes();
		}), PlacedFeature.LIST_CODEC.fieldOf("overworld_features").forGetter((biomeModifier) -> {
			return biomeModifier.overworldFeatures();
		}), Biome.LIST_CODEC.fieldOf("peak_biomes").forGetter((biomeModifier) -> {
			return biomeModifier.mountainPeakBiomes();
		}), PlacedFeature.LIST_CODEC.fieldOf("peak_features").forGetter((biomeModifier) -> {
			return biomeModifier.mountainPeakFeatures();
		}), Biome.LIST_CODEC.fieldOf("end_biomes").forGetter((biomeModifier) -> {
			return biomeModifier.endBiomes();
		}), PlacedFeature.LIST_CODEC.fieldOf("end_features").forGetter((biomeModifier) -> {
			return biomeModifier.endFeatures();
		})).apply(instance, XOBiomeModifier::new);
	});
	
	@Override
	public void modify(@NotNull Holder<Biome> biome, @NotNull Phase phase, @NotNull Builder builder) {
		if (phase == Phase.ADD) {
			BiomeGenerationSettings.PlainBuilder generationBuilder = builder.getGenerationSettings();
			if (this.overworldBiomes.contains(biome)) {
				if (this.mountainPeakBiomes.contains(biome)) {
					for (Holder<PlacedFeature> holder : this.mountainPeakFeatures) {
						generationBuilder.addFeature(Decoration.UNDERGROUND_ORES, holder);
					}
				}
				for (Holder<PlacedFeature> holder : this.overworldFeatures) {
					generationBuilder.addFeature(Decoration.UNDERGROUND_ORES, holder);
				}
			} else if (this.endBiomes.contains(biome)) {
				for (Holder<PlacedFeature> holder : this.endFeatures) {
					generationBuilder.addFeature(Decoration.UNDERGROUND_ORES, holder);
				}
			}
		}
	}
	
	@Override
	public @NotNull MapCodec<XOBiomeModifier> codec() {
		return XOBiomeModifiers.ORE_BIOME_MODIFIER.get();
	}
}
