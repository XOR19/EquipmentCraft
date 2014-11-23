package equipmentcraft;

import java.util.HashMap;

import net.minecraft.item.Item;

public class Material {
	
	private static final HashMap<String, Material> materials = new HashMap<String, Material>();
	
	private static final HashMap<Item, Material> materialsItems = new HashMap<Item, Material>();
	
	private String name;
	
	private int color;
	
	private Item item;
	
	private int harvestLevel;
	
	private float breakSpeed;
	
	public Material(String name, Item item, int color, int harvestLevel, float breakSpeed) {
		if(materials.containsKey(name))
			throw new IllegalArgumentException("Duplicated Material Name \""+name+"\"");
		this.name = name;
		this.item = item;
		this.color = color;
		this.harvestLevel = harvestLevel;
		this.breakSpeed = breakSpeed;
		materials.put(name, this);
		materialsItems.put(item, this);
	}
	
	public int getColor() {
		return this.color;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Item getItem(){
		return this.item;
	}
	
	public int getHarvestLevel(){
		return this.harvestLevel;
	}
	
	public static Material getByName(String name) {
		return materials.get(name);
	}
	
	public static Material getByItem(Item item) {
		return materialsItems.get(item);
	}

	public float getBreakSpeed() {
		return this.breakSpeed;
	}
	
}
