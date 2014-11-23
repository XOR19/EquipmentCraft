package equipmentcraft;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

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
	
	public static <T> T[] a(T...array){
		return array;
	}
	
	public static <T> Set<T> asSet(final T...args){
		return new AbstractSet<T>() {
			
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {

					private int index;
					
					@Override
					public boolean hasNext() {
						return args.length>this.index;
					}

					@Override
					public T next() {
						return args[this.index++];
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
					
				};
			}

			@Override
			public int size() {
				return args.length;
			}
		};
	}
	
}
