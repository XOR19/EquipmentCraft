package equipmentcraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;


public class Utils {
	
	public static NBTTagCompound getTagCompound(ItemStack itemStack){
		NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
		return nbtTagCompound==null?null:nbtTagCompound.getCompoundTag("ToolCraft");
	}
	
	public static NBTTagCompound createTagCompound(ItemStack itemStack){
		NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
		if(nbtTagCompound==null){
			nbtTagCompound = new NBTTagCompound();
			itemStack.setTagCompound(nbtTagCompound);
		}
		NBTTagCompound nbtTagCompound2;
		if(nbtTagCompound.hasKey("ToolCraft")){
			nbtTagCompound2 = nbtTagCompound.getCompoundTag("ToolCraft");
		}else{
			nbtTagCompound2 = new NBTTagCompound();
			nbtTagCompound.setTag("ToolCraft", nbtTagCompound2);
		}
		return nbtTagCompound2;
	}
	
	public static boolean isMissing(IIcon icon, ResourceLocation resourceLocation){
		TextureMap map = (TextureMap)mc().getTextureManager().getTexture(resourceLocation);
		IIcon missing = map.getAtlasSprite(null);
		return missing.getMinU()==icon.getMinU() &&  missing.getMaxU()==icon.getMaxU() && missing.getMinV()==icon.getMinV() &&  missing.getMaxV()==icon.getMaxV();
	}
	
	public static Minecraft mc(){
		return Minecraft.getMinecraft();
	}
	
}
