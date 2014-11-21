package equipmentcraft.items;

import java.util.HashMap;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class Material {
	
	private static final HashMap<String, Material> materials = new HashMap<String, Material>();
	
	private String name;
	
	private int color;
	
	private ToolMaterial toolMaterial;
	
	private ArmorMaterial armorMaterial;
	
	public Material(String name, ToolMaterial toolMaterial, ArmorMaterial armorMaterial, int color) {
		if(materials.containsKey(name))
			throw new IllegalArgumentException("Duplicated Material Name \""+name+"\"");
		this.name = name;
		this.toolMaterial = toolMaterial;
		this.armorMaterial = armorMaterial;
		this.color = color;
		materials.put(name, this);
	}
	
	public int getColor(ItemStack itemStack) {
		return this.color;
	}
	
	public ToolMaterial getToolMaterial(){
		return this.toolMaterial;
	}
	
	public ArmorMaterial getArmorMaterial(){
		return this.armorMaterial;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static Material getByName(String name) {
		return materials.get(name);
	}
	
}
