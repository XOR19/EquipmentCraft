package equipmentcraft.items;

import equipmentcraft.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


public class ItemData {
	
	/**
	 * What kind of blocks can be mined
	 * 0=wood tools
	 * 1=stone tools
	 * 2=iron tools
	 * 3=diamond tools
	 */
	public int harvestLevel;
	/**
	 * How long the tool can be used
	 */
	public int durability;
	/**
	 * How fast the tool mines
	 */
	public int efficiency;
	/**
	 * How mutch more drops you can get
	 */
	public int looting;
	/**
	 * Silk touch
	 */
	public boolean silk;
	/**
	 * But things when destroied
	 */
	public int fireAspect;
	/**
	 * Bow infinity
	 */
	public boolean infinity;
	/**
	 * Entity knockback
	 */
	public int knockback;
	/**
	 * Damage vs. entity
	 */
	public int damage;
	/**
	 * Speed under water
	 */
	public int waterSpeed;
	
	public static ItemData getItemData(ItemStack itemStack){
		NBTTagCompound nbtTagCompound = Utils.getTagCompound(itemStack);
		if(nbtTagCompound==null){
			return null;
		}
		ItemData itemData = new ItemData();
		itemData.harvestLevel = nbtTagCompound.getInteger("harvestLevel")-1;
		itemData.durability = nbtTagCompound.getInteger("durability");
		itemData.efficiency = nbtTagCompound.getInteger("efficiency");
		itemData.looting = nbtTagCompound.getInteger("looting");
		itemData.silk = nbtTagCompound.getBoolean("silk");
		itemData.fireAspect = nbtTagCompound.getInteger("fireAspect");
		itemData.infinity = nbtTagCompound.getBoolean("infinity");
		itemData.knockback = nbtTagCompound.getInteger("knockback");
		itemData.damage = nbtTagCompound.getInteger("damage");
		itemData.waterSpeed = nbtTagCompound.getInteger("waterSpeed");
		return itemData;
	}
	
	public static void setItemData(NBTTagCompound nbtTagCompound, ItemData itemData){
		if(itemData.harvestLevel==-1){
			nbtTagCompound.removeTag("harvestLevel");
		}else{
			nbtTagCompound.setInteger("harvestLevel", itemData.harvestLevel+1);
		}
		if(itemData.durability==0){
			nbtTagCompound.removeTag("durability");
		}else{
			nbtTagCompound.setInteger("durability", itemData.durability);
		}
		if(itemData.efficiency==0){
			nbtTagCompound.removeTag("efficiency");
		}else{
			nbtTagCompound.setInteger("efficiency", itemData.efficiency);
		}
		if(itemData.looting==0){
			nbtTagCompound.removeTag("looting");
		}else{
			nbtTagCompound.setInteger("looting", itemData.looting);
		}
		if(itemData.silk){
			nbtTagCompound.setBoolean("silk", true);
		}else{
			nbtTagCompound.removeTag("silk");
		}
		if(itemData.fireAspect==0){
			nbtTagCompound.removeTag("fireAspect");
		}else{
			nbtTagCompound.setInteger("fireAspect", itemData.fireAspect);
		}
		if(itemData.infinity){
			nbtTagCompound.setBoolean("infinity", true);
		}else{
			nbtTagCompound.removeTag("infinity");
		}
		if(itemData.knockback==0){
			nbtTagCompound.removeTag("knockback");
		}else{
			nbtTagCompound.setInteger("knockback", itemData.knockback);
		}
		if(itemData.damage==0){
			nbtTagCompound.removeTag("damage");
		}else{
			nbtTagCompound.setInteger("damage", itemData.damage);
		}
		if(itemData.waterSpeed==0){
			nbtTagCompound.removeTag("waterSpeed");
		}else{
			nbtTagCompound.setInteger("waterSpeed", itemData.waterSpeed);
		}
	}

	public void combine(ItemData itemDataFor) {
		// TODO Auto-generated method stub
		
	}
	
}
