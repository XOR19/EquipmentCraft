package equipmentcraft;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


public class Utils {
	
	public static NBTTagCompound getTagCompound(ItemStack itemStack){
		NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
		return nbtTagCompound==null?null:nbtTagCompound.getCompoundTag("ToolCraft");
	}
	
}
