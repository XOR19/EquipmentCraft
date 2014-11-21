package equipmentcraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.Constants.NBT;


public class ItemTool extends Item {

	protected final ItemToolPart[] toRender;
	
	protected final ItemToolPart[] parts;
	
	protected ItemTool(ItemToolPart[] parts){
		this.parts = parts;
		int num = 0;
		for(ItemToolPart part:parts){
			if(part.renderAtTool()){
				num++;
			}
		}
		this.toRender = new ItemToolPart[num];
		num = 0;
		for(ItemToolPart part:parts){
			if(part.renderAtTool()){
				this.toRender[num++] = part;
			}
		}
	}
	
	@Override
	public int getColorFromItemStack(ItemStack itemStack, int pass) {
		Material material = getPartMaterial(itemStack, pass);
		if(material==null)
			return getColorFromItemStack(itemStack, pass);
		return material.getColor(itemStack);
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
		return this.toRender[pass].getIconFor(this);
	}

	@Override
	public int getRenderPasses(int metadata) {
		return this.toRender.length;
	}
	
	@Override
	public void registerIcons(IIconRegister iconRegister) {
		for(ItemToolPart part:this.toRender){
			part.registerIconFor(this, iconRegister);
		}
	}

	public ItemToolPart[] getParts(){
		return this.parts;
	}
	
	public static Material getPartMaterial(ItemStack itemStack, int part){
		NBTTagCompound nbtTagCompound = getTagCompound(itemStack);
		if(nbtTagCompound!=null){
			NBTTagList list = nbtTagCompound.getTagList("Materials", NBT.TAG_STRING);
			if(list!=null){
				return Material.getByName(list.getStringTagAt(part));
			}
		}
		return null;
	}
	
	private static NBTTagCompound getTagCompound(ItemStack itemStack){
		NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
		return nbtTagCompound==null?null:nbtTagCompound.getCompoundTag("ToolCraft");
	}
	
}
