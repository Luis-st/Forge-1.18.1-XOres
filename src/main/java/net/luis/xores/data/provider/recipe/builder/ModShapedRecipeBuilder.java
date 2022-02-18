package net.luis.xores.data.provider.recipe.builder;

import net.luis.xores.common.material.Material;
import net.luis.xores.data.provider.recipe.ModRecipeProvider;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.Tag;
import net.minecraft.tags.Tag.Named;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;

/**
 * extension of {@link ShapedRecipeBuilder},<br>
 * used to create easier {@link ShapedRecipe}s for mod {@link Item}s
 * in {@link ModRecipeProvider}
 * 
 * @author Luis-st
 * 
 * @see {@link ShapedRecipeBuilder}
 * @see {@link ShapedRecipe}
 * @see {@link Item}
 * @see {@link ModRecipeProvider}
 */

public class ModShapedRecipeBuilder extends ShapedRecipeBuilder {

	/**
	 * constructor for the {@link ModShapedRecipeBuilder},<br>
	 * private since you should use the factory methods
	 * 
	 * @see {@link ModShapedRecipeBuilder#shaped(ItemLike)}
	 * @see {@link ModShapedRecipeBuilder#shaped(ItemLike, int)}
	 */
	private ModShapedRecipeBuilder(ItemLike result, int count) {
		super(result, count);
	}
	
	/**
	 * @return a {@link ModShapedRecipeBuilder} for the given {@link ItemLike}
	 * 
	 * @see {@link ModShapedRecipeBuilder#shaped(ItemLike, int)}
	 */
	public static ModShapedRecipeBuilder shaped(ItemLike result) {
		return shaped(result, 1);
	}

	/**
	 * @return a {@link ModShapedRecipeBuilder} for the given {@link ItemLike} and the count
	 */
	public static ModShapedRecipeBuilder shaped(ItemLike result, int count) {
		return new ModShapedRecipeBuilder(result, count);
	}
	
	/**
	 * sets the group for the {@link ShapedRecipe}
	 * @param material
	 * @return the {@link ModShapedRecipeBuilder} itself
	 */
	public ModShapedRecipeBuilder group(Material material) {
		return this.group(this.getGroup(material));
	}
	
	/**
	 * sets the group for the {@link ShapedRecipe}
	 * @param group
	 * @return the {@link ModShapedRecipeBuilder} itself
	 */
	@Override
	public ModShapedRecipeBuilder group(String group) {
		return (ModShapedRecipeBuilder) super.group(group);
	}
	
	/**
	 * defines an {@link ItemLike} as a {@link Character} key for the {@link ShapedRecipe},
	 * the key is used in the pattern
	 * @param character which is used as key
	 * @param itemLike which should be defined
	 * @return the {@link ModShapedRecipeBuilder} itself
	 */
	@Override
	public ModShapedRecipeBuilder define(Character character, ItemLike itemLike) {
		return (ModShapedRecipeBuilder) super.define(character, itemLike);
	}
	
	/**
	 * defines a {@link Tag} as a {@link Character} key for the {@link ShapedRecipe},
	 * the key is used in the pattern
	 * @param character which is used as key
	 * @param tag which should be defined
	 * @return the {@link ModShapedRecipeBuilder} itself
	 */
	@Override
	public ModShapedRecipeBuilder define(Character character, Tag<Item> tag) {
		return (ModShapedRecipeBuilder) super.define(character, tag);
	}
	
	/**
	 * defines a {@link Material} as a {@link Character} key for the {@link ShapedRecipe},
	 * the key is used in the pattern
	 * @param character which is used as key
	 * @param material which should be defined
	 * @return the {@link ModShapedRecipeBuilder} itself
	 */
	public ModShapedRecipeBuilder define(Character character, Material material) {
		return this.define(character, material.asIngredient());
	}
	
	/**
	 * defines an {@link Ingredient} as a {@link Character} key for the {@link ShapedRecipe},
	 * the key is used in the pattern
	 * @param character which is used as key
	 * @param ingredient which should be defined
	 * @return the {@link ModShapedRecipeBuilder} itself
	 */
	@Override
	public ModShapedRecipeBuilder define(Character character, Ingredient ingredient) {
		return (ModShapedRecipeBuilder) super.define(character, ingredient);
	}
	
	/**
	 * sets all three lines of the pattern
	 * @param firstLine as a {@link String}
	 * @param secondLine as a {@link String}
	 * @param thirdLine as a {@link String}
	 * @return the {@link ModShapedRecipeBuilder} itself
	 */
	public ModShapedRecipeBuilder pattern(String firstLine, String secondLine, String thirdLine) {
		return this.pattern(firstLine).pattern(secondLine).pattern(thirdLine);
	}
	
	/**
	 * sets one line of the pattern
	 * @param pattern as a {@link String}
	 * @return the {@link ModShapedRecipeBuilder} itself
	 */
	@Override
	public ModShapedRecipeBuilder pattern(String pattern) {
		return (ModShapedRecipeBuilder) super.pattern(pattern);
	}
	
	/**
	 * sets the unlocked by condition of the {@link ShapedRecipe} to the given {@link Material}
	 * @param material which is used as condition
	 * @return the {@link ModShapedRecipeBuilder} itself
	 */
	public ModShapedRecipeBuilder unlockedBy(Material material) {
		return this.unlockedBy("has_", material);
	}
	
	/**
	 * sets the unlocked by condition of the {@link ShapedRecipe} to the given {@link Material}
	 * @param prefix for the unlocked by condition, default is 'has_' + {@link ModShapedRecipeBuilder#getId(Material)}
	 * @param material which is used as condition
	 * @return the {@link ModShapedRecipeBuilder} itself
	 */
	public ModShapedRecipeBuilder unlockedBy(String prefix, Material material) {
		return (ModShapedRecipeBuilder) this.unlockedBy(prefix + this.getId(material), this.has(material));
	}
	
	/**
	 * sets the unlocked by condition of the {@link ShapedRecipe} to the given {@link CriterionTriggerInstance}
	 * @param name of the condition
	 * @param triggerInstance which is used as condition
	 * @return the {@link ModShapedRecipeBuilder} itself
	 */
	@Override
	public ModShapedRecipeBuilder unlockedBy(String name, CriterionTriggerInstance triggerInstance) {
		return (ModShapedRecipeBuilder) super.unlockedBy(name, triggerInstance);
	}
	
	/**
	 * @param item for which a id should be get
	 * @return the id for the given {@link Item} as a {@link String}
	 */
	protected final String getId(Item item) {
		return item.getRegistryName().getPath();
	}
	
	/**
	 * @param material for which a id should be get
	 * @return the id for the given {@link Material} as a {@link String}
	 */
	protected final String getId(Material material) {
		if (material.isItem()) {
			return this.getId(material.itemOrThrow());
		}
		Named<Item> tag = material.tagOrThrow();
		if (!tag.getName().getPath().contains("/")) {
			return tag.getName().getPath();
		}
		String[] pathParts = tag.getName().getPath().split("/");
		return pathParts[pathParts.length - 1];
	}
	
	/**
	 * @param material for which a group should be get
	 * @return the group name for the given {@link Material} as a {@link String}
	 */
	protected final String getGroup(Material material) {
		if (material.isItem()) {
			String path = material.itemOrThrow().getRegistryName().getPath();
			if (!path.contains("_")) {
				return path;
			}
			String[] pathParts = path.split("_");
			if (pathParts[0].equals("polished") || pathParts[0].equals("rose")) {
				return "rose_quartz";
			}
			return pathParts[0];
		}
		return this.getId(material);
	}
	
	/**
	 * @param material for which a {@link TriggerInstance} should be get
	 * @return a {@link TriggerInstance} for the given {@link Material}
	 */
	protected final TriggerInstance has(Material material) {
		if (material.isItem()) {
			return this.inventoryTrigger(ItemPredicate.Builder.item().of(material.itemOrThrow()).build());
		} else if (material.isTag()) {
			return this.inventoryTrigger(ItemPredicate.Builder.item().of(material.tagOrThrow()).build());
		}
		throw new IllegalStateException("Fail to get TriggerInstance for Material since it's not Item and Tag");
	}

	/**
	 * @param predicates for which a {@link TriggerInstance} should be get
	 * @return a {@link TriggerInstance} for the given {@link ItemPredicate}s
	 */
	protected final InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... predicates) {
		return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, predicates);
	}
	
}