package equipmentcraft;

import java.util.HashSet;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import equipmentcraft.items.ItemCore;
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
	public static ItemParts handle;
	public static ItemParts connect;
	public static ItemCore pickaxe;
	public static ItemCore axe;
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		wood = new Material("wood", Item.getItemFromBlock(Blocks.planks), 0x6B511F, 0);
		stone = new Material("stone", Item.getItemFromBlock(Blocks.cobblestone), 0x7F7F7F, 1);
		iron = new Material("iron", Items.iron_ingot, 0xC1C1C1, 2);
		gold = new Material("gold", Items.gold_ingot, 0xBCBF4D, 0);
		diamond = new Material("diamond", Items.diamond, 0x27B29A, 3);
		emerald = new Material("emerald", Items.emerald, 0x00B038, 3);
		
		Material[] materials = {wood, stone, iron, gold, diamond, emerald};
		
		GameRegistry.registerItem((pickaxe_head = new ItemParts(materials, true)).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("pickaxe_head"), "pickaxe_head");
		GameRegistry.registerItem((axe_head = new ItemParts(materials, true)).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("axe_head"), "axe_head");
		GameRegistry.registerItem((handle = new ItemParts(materials, false)).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("handle"), "handle");
		GameRegistry.registerItem((connect = new ItemParts(materials, false)).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("connect"), "connect");
		GameRegistry.registerItem((pickaxe = new ItemCore(new ItemParts[]{pickaxe_head, handle, connect}, new ItemEffect[0], new HashSet<String>())).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("pickaxe"), "pickaxe");
		GameRegistry.registerItem((axe = new ItemCore(new ItemParts[]{axe_head, handle, connect}, new ItemEffect[0], new HashSet<String>())).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("axe"), "axe");
		GameRegistry.addRecipe(ToolBuild.create(pickaxe, 1, 0, 2, 1));
		GameRegistry.addRecipe(ToolBuild.create(axe, 1, 0, 2, 1));
	}
	
}
