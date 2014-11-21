package equipmentcraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import equipmentcraft.Utils;


public class ToolPart extends Item implements ItemToolPart{
	
	private boolean renderAtTool;
	
	private MaterialFilter materialFilter;
	
	public ToolPart(boolean renderAtTool, MaterialFilter materialFilter){
		this.renderAtTool = renderAtTool;
		this.materialFilter = materialFilter;
	}

	@Override
	public int getColorFromItemStack(ItemStack itemStack, int pass) {
		Material material = getMaterial(itemStack);
		if(material==null)
			return getColorFromItemStack(itemStack, pass);
		return material.getColor(itemStack);
	}
	
	@Override
	public boolean renderAtTool() {
		return this.renderAtTool;
	}
	
	@Override
	public IIcon getIconFor(ItemTool itemTool) {
		return this.itemIcon;
	}

	public boolean isMaterialAccepted(Material material){
		return this.materialFilter.accepted(material);
	}
	
	@Override
	public void registerIconFor(ItemTool itemTool, IIconRegister iconRegister) {/**/}

	@Override
	public ToolPart getToolPart() {
		return this;
	}
	
	public static Material getMaterial(ItemStack itemStack){
		NBTTagCompound nbtTagCompound = Utils.getTagCompound(itemStack);
		if(nbtTagCompound!=null){
			return Material.getByName(nbtTagCompound.getString("Material"));
		}
		return null;
	}
	
}
