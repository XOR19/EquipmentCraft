package equipmentcraft.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import equipmentcraft.Material;
import equipmentcraft.Settings;
import equipmentcraft.Utils;


public class ItemParts extends Item {

	private Material[] materials;

	private IIcon[] icons;
	
	private boolean useOwnIcons;
	
	private String name;
	
	public ItemParts(Material[] materials, boolean useOwnIcons){
		this.materials = materials;
		this.icons = new IIcon[materials.length+1];
		this.useOwnIcons = useOwnIcons;
	}
	
	public int getColorFromItemStack(ItemCore itemCore, IIcon[] icons, NBTTagCompound part) {
		if(Settings.onlyUseDefaultIcons){
			int material = part.getInteger("Material");
			if(material!=0){
				return this.materials[material-1].getColor();
			}
		}
		return itemCore.getDefaultColor();
	}

	public boolean useOwnIcons(ItemCore itemCore){
		return this.useOwnIcons;
	}
	
	public IIcon[] registerIconsFor(ItemCore itemCore, IIconRegister register, int maxUse) {
		if(useOwnIcons(itemCore))
			return null;
		IIcon[] icons = new IIcon[(Settings.onlyUseDefaultIcons?1:(this.materials.length+1))*maxUse];
		String prefix = "equipmentcraft:"+itemCore.getName()+"/"+getName()+"/";
		icons[0] = register.registerIcon(prefix+"default");
		for(int i=1; i<maxUse; i++){
			icons[i] = register.registerIcon(prefix+"default_use_"+i);
		}
		if(!Settings.onlyUseDefaultIcons){
			for(int i=0; i<this.materials.length; i++){
				icons[(i+1)*maxUse] = register.registerIcon(prefix+this.materials[i].getName());
				for(int j=1; j<maxUse; j++){
					icons[(i+1)*maxUse+j] = register.registerIcon(prefix+this.materials[i].getName()+"_use_"+i);
				}
			}
		}
		return icons;
	}

	public IIcon getIconFor(ItemCore itemCore, IIcon[] icons, NBTTagCompound part, int useIndex, int maxUse) {
		int material = part.getInteger("Material");
		if(useOwnIcons(itemCore) || icons==null){
			icons = this.icons;
			maxUse = 1;
			useIndex = 0;
		}
		if(Settings.onlyUseDefaultIcons){
			return icons[useIndex];
		}
		int index = material*maxUse+useIndex;
		if(icons.length<=index){
			return icons[useIndex];
		}
		IIcon icon = icons[material*maxUse+useIndex];
		if(icon==null || Utils.isMissing(icon, TextureMap.locationItemsTexture)){
			return icons[useIndex];
		}
		return icon;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public Item setUnlocalizedName(String name) {
		this.name = name;
		return super.setUnlocalizedName(name);
	}

	public NBTTagCompound save(ItemCore itemCore, ItemStack itemStack) {
		NBTTagCompound nbtTagCompound = Utils.getTagCompound(itemStack);
		if(nbtTagCompound==null){
			return new NBTTagCompound();
		}
		return nbtTagCompound;
	}

	@Override
	public void registerIcons(IIconRegister register) {
		String prefix = "equipmentcraft:"+getName()+"/";
		this.icons[0] = register.registerIcon(prefix+"default");
		if(!Settings.onlyUseDefaultIcons){
			for(int i=0; i<this.materials.length; i++){
				this.icons[i+1] = register.registerIcon(prefix+this.materials[i].getName());
			}
		}
	}
	
	@Override
	public int getColorFromItemStack(ItemStack stack, int pass) {
		if(Settings.onlyUseDefaultIcons){
			NBTTagCompound nbtTagCompound = Utils.getTagCompound(stack);
			if(nbtTagCompound!=null){
				int material = nbtTagCompound.getInteger("Material");
				if(material!=0){
					return this.materials[material-1].getColor();
				}
			}
		}
		return super.getColorFromItemStack(stack, pass);
	}

	@Override
	public IIcon getIconFromDamage(int damage) {
		return this.icons[0];
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return getIconIndex(stack);
	}
	
	@Override
	public IIcon getIconIndex(ItemStack stack){
		NBTTagCompound nbtTagCompound = Utils.getTagCompound(stack);
		if(nbtTagCompound==null || Settings.onlyUseDefaultIcons)
			return this.icons[0];
		int material = nbtTagCompound.getInteger("Material");
		IIcon icon = this.icons[material];
		if(icon==null || Utils.isMissing(icon, TextureMap.locationItemsTexture)){
			return this.icons[0];
		}
		return icon;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
		for(int i=0; i<this.materials.length; i++){
			ItemStack is = new ItemStack(item);
			NBTTagCompound nbtTagCompound = Utils.createTagCompound(is);
			nbtTagCompound.setInteger("Material", i+1);
			list.add(is);
		}
	}
	
}
