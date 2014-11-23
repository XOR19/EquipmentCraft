package equipmentcraft;

import equipmentcraft.items.ItemCore;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;


public class ItemEffect {

	private String name;
	
	public ItemEffect(String name){
		this.name = name;
	}
	
	public IIcon[] registerIconsFor(ItemCore itemCore, IIconRegister register, int maxUse) {
		IIcon[] icons = new IIcon[maxUse];
		String prefix = "EquipmentCraft:"+itemCore.getName()+"/"+getName()+"/";
		icons[0] = register.registerIcon(prefix+"effect");
		for(int i=1; i<maxUse; i++){
			icons[i] = register.registerIcon(prefix+"effect_use_"+i);
		}
		return icons;
	}

	public IIcon getIconFor(ItemCore itemCore, IIcon[] icons, NBTTagCompound effect, int index, int maxUse) {
		return icons[index];
	}

	public int getColorFromItemStack(ItemCore itemCore, IIcon[] icons, NBTTagCompound effect) {
		return itemCore.getDefaultColor();
	}
	
	public String getName() {
		return this.name;
	}
	
}
