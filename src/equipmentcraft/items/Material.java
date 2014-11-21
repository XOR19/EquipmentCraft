package equipmentcraft.items;

import java.util.HashMap;

import net.minecraft.item.ItemStack;


public class Material {
	
	private static final HashMap<String, Material> materials = new HashMap<String, Material>();
	
	private String name;
	
	private int color;
	
	
	
	public int getColor(ItemStack itemStack) {
		return color;
	}

	public static Material getByName(String name) {
		return materials.get(name);
	}
	
}
