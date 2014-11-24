package equipmentcraft;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import equipmentcraft.items.ItemCore;
import equipmentcraft.items.ItemHoe;
import equipmentcraft.items.ItemParts;

@Mod(modid="EquipmentCraft", name="EquipmentCraft", version="1.0.0")
public class EquipmentCraft {
	
	public static Material wood;
	public static Material stone;
	public static Material iron;
	public static Material gold;
	public static Material diamond;
	public static Material emerald;
	
	public static ItemParts pickaxe_head;
	public static ItemParts axe_head;
	public static ItemParts shovel_head;
	public static ItemParts hoe_head;
	public static ItemParts handle;
	public static ItemParts connect;
	public static ItemCore pickaxe;
	public static ItemCore axe;
	public static ItemCore shovel;
	public static ItemCore hoe;
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		wood = new Material("wood", Item.getItemFromBlock(Blocks.planks), 0x6B511F, 0, 2.0f);
		stone = new Material("stone", Item.getItemFromBlock(Blocks.cobblestone), 0x7F7F7F, 1, 4.0f);
		iron = new Material("iron", Items.iron_ingot, 0xC1C1C1, 2, 6.0f);
		gold = new Material("gold", Items.gold_ingot, 0xBCBF4D, 0, 12.0f);
		diamond = new Material("diamond", Items.diamond, 0x27B29A, 3, 8.0f);
		emerald = new Material("emerald", Items.emerald, 0x00B038, 3, 12.0f);
		
		/*
		 * Maybe to add:
		 * Bone, Paper, Brick, Nether Brick, Flint, String Materials
		 */
		
		Material[] materials = {wood, stone, iron, gold, diamond, emerald};
		
		Block[] pickaxeEffective = {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail};
		Block[] axeEffective = {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin};
		Block[] shovelEffective = {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium};
		
		GameRegistry.registerItem((pickaxe_head = new ItemParts(materials, true)).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("pickaxe_head"), "pickaxe_head");
		GameRegistry.registerItem((axe_head = new ItemParts(materials, true)).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("axe_head"), "axe_head");
		GameRegistry.registerItem((shovel_head = new ItemParts(materials, true)).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("shovel_head"), "shovel_head");
		GameRegistry.registerItem((hoe_head = new ItemParts(materials, true)).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("hoe_head"), "hoe_head");
		GameRegistry.registerItem((handle = new ItemParts(materials, false)).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("handle").setFull3D(), "handle");
		GameRegistry.registerItem((connect = new ItemParts(materials, false)).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("connect"), "connect");
		GameRegistry.registerItem((pickaxe = new ItemCore(Utils.a(pickaxe_head, handle, connect), new ItemEffect[0], Utils.a("pickaxe"), pickaxeEffective)).setUnlocalizedName("pickaxe"), "pickaxe");
		GameRegistry.registerItem((axe = new ItemCore(Utils.a(axe_head, handle, connect), new ItemEffect[0], Utils.a("axe"), axeEffective)).setUnlocalizedName("axe"), "axe");
		GameRegistry.registerItem((shovel = new ItemCore(Utils.a(shovel_head, handle, connect), new ItemEffect[0], Utils.a("shovel"), shovelEffective)).setUnlocalizedName("shovel"), "shovel");
		GameRegistry.registerItem((hoe = new ItemHoe(Utils.a(hoe_head, handle, connect), new ItemEffect[0])).setUnlocalizedName("hoe"), "hoe");
		GameRegistry.addRecipe(ToolBuild.create(pickaxe, 1, 0, 2, 1));
		GameRegistry.addRecipe(ToolBuild.create(axe, 1, 0, 2, 1));
		GameRegistry.addRecipe(ToolBuild.create(shovel, 1, 0, 2, 1));
		GameRegistry.addRecipe(ToolBuild.create(hoe, 1, 0, 2, 1));
	}
	
}
