package equipmentcraft.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

import com.google.common.collect.Multimap;

import equipmentcraft.ItemEffect;
import equipmentcraft.Utils;

public class ItemCore extends Item {
	
	private ItemParts[] parts;
	
	private IIcon[][] partIcons;
	
	private ItemEffect[] effects;
	
	private IIcon[][] effectIcons;
	
	private Set<String> toolClasses;
	
	private String name;
	
	public ItemCore(ItemParts[] parts, ItemEffect[] effects, Set<String> toolClasses){
		this.parts = parts;
		this.partIcons = new IIcon[parts.length][];
		this.effects = effects;
		this.effectIcons = new IIcon[effects.length][];
		this.toolClasses = Collections.unmodifiableSet(toolClasses);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer palyer) {
		if(stack.getItemUseAction()!=EnumAction.none){
			palyer.setItemInUse(stack, stack.getMaxItemUseDuration());
		}
		return stack;
	}

	/**
	 * DigSpeed
	 */
	@Override
	public float func_150893_a(ItemStack stack, Block block) {
		NBTTagCompound nbtTagCompound = Utils.getTagCompound(stack);
		if(nbtTagCompound==null)
			return 1.0F;
		return nbtTagCompound.getFloat("BreakSpeed");
	}
	
	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int pass) {
		NBTTagCompound nbtTagCompound = Utils.getTagCompound(stack);
		if(nbtTagCompound==null)
			return getDefaultColor();
		
		if(pass<this.parts.length){
			NBTTagList partsList = nbtTagCompound.getTagList("Parts", NBT.TAG_COMPOUND);
			return this.parts[pass].getColorFromItemStack(this, this.partIcons[pass], partsList.getCompoundTagAt(pass));
		}
		pass -= this.parts.length;
		int[] effects = nbtTagCompound.getIntArray("Effects");
		int effect = effects.length>pass?effects[pass]:-1;
		if(effect==-1)
			return getDefaultColor();
		NBTTagList effectList = nbtTagCompound.getTagList("EffectDatas", NBT.TAG_COMPOUND);
		return this.effects[pass].getColorFromItemStack(this, this.effectIcons[pass], effectList.getCompoundTagAt(effect));
	}
	
	public int getDefaultColor(){
		return super.getColorFromItemStack(null, 0);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack) {
		return EnumAction.none;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 0;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		NBTTagCompound nbtTagCompound = Utils.getTagCompound(stack);
		if(nbtTagCompound==null)
			return EnumRarity.common;
		int rarity = nbtTagCompound.getInteger("Rarity");
		return EnumRarity.values()[rarity];
	}
	
	@Override
	public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
		if(pass<this.parts.length){
			int maxUse = getMaxUseIndex();
			return this.parts[pass].getIconFor(this, this.partIcons[pass], new NBTTagCompound(), getUseIndex(-1), maxUse);
		}
		return null;
	}
	
	@Override
	public void registerIcons(IIconRegister register) {
		int maxUse = getMaxUseIndex();
		for(int i=0; i<this.parts.length; i++){
			this.partIcons[i] = this.parts[i].registerIconsFor(this, register, maxUse);
		}
		for(int i=0; i<this.effects.length; i++){
			this.effectIcons[i] = this.effects[i].registerIconsFor(this, register, maxUse);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Multimap getAttributeModifiers(ItemStack stack) {
		Multimap map = super.getAttributeModifiers(stack);
		NBTTagCompound nbtTagCompound = Utils.getTagCompound(stack);
		if(nbtTagCompound!=null && nbtTagCompound.hasKey("DamageVsEntity")){
			double damage = nbtTagCompound.getDouble("DamageVsEntity");
			map.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Item modifier", damage, 0));
		}
		return map;
	}
	
	public int getMaxUseIndex(){
		return 1;
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	public int getUseIndex(int useRemaining){
		return 0;
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass, EntityPlayer player, ItemStack usingItem, int useRemaining){
		NBTTagCompound nbtTagCompound = Utils.getTagCompound(stack);
		if(nbtTagCompound==null)
			return getIconFromDamageForRenderPass(stack.getItemDamage(), pass);
		int index = getUseIndex(useRemaining);
		int maxUse = getMaxUseIndex();
		if(pass<this.parts.length){
			NBTTagList partsList = nbtTagCompound.getTagList("Parts", NBT.TAG_COMPOUND);
			return this.parts[pass].getIconFor(this, this.partIcons[pass], partsList.getCompoundTagAt(pass), index, maxUse);
		}
		pass -= this.parts.length;
		int[] effects = nbtTagCompound.getIntArray("Effects");
		int effect = effects.length>pass?effects[pass]:-1;
		if(effect==-1)
			return null;
		NBTTagList effectList = nbtTagCompound.getTagList("EffectDatas", NBT.TAG_COMPOUND);
		return this.effects[pass].getIconFor(this, this.effectIcons[pass], effectList.getCompoundTagAt(effect), index, maxUse);
    }
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return getIcon(stack, pass, null, null, -1);
	}
	
	@Override
	public boolean canHarvestBlock(Block block, ItemStack itemStack) {
		return func_150897_b(block);
	}
	
	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return this.toolClasses;
	}
	
	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass) {
		NBTTagCompound nbtTagCompound = Utils.getTagCompound(stack);
		if(nbtTagCompound==null)
			return -1;
		return nbtTagCompound.getInteger("harvestLevel@"+toolClass)-1;
	}
	
	@Override
	public boolean requiresMultipleRenderPasses() {
		return getRenderPasses()>1;
	}
	
	@Override
	public int getRenderPasses(int metadata) {
		return getRenderPasses();
	}
	
	public int getRenderPasses(){
		return this.parts.length+this.effects.length;
	}

	public ItemStack build(ItemStack[] parts) {
		ItemStack is = new ItemStack(this);
		NBTTagCompound nbtTagCompound = Utils.createTagCompound(is);
		NBTTagList partsList = new NBTTagList();
		for(int i=0; i<this.parts.length; i++){
			NBTTagCompound partTag = this.parts[i].save(this, parts[i]);
			partsList.appendTag(partTag);
		}
		nbtTagCompound.setTag("Parts", partsList);
		nbtTagCompound.setFloat("BreakSpeed", 1.0f);
		return is;
	}

	public String getName() {
		return this.name;
	}
	
	@Override
	public Item setUnlocalizedName(String name) {
		this.name = name;
		return super.setUnlocalizedName(name);
	}

	public ItemParts[] getParts() {
		return this.parts;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
		List[] partsList = new ArrayList[this.parts.length];
		for(int i=0; i<this.parts.length; i++){
			this.parts[i].getSubItems(this.parts[i], creativeTabs, partsList[i] = new ArrayList());
		}
		ItemStack[] partsBuild = new ItemStack[this.parts.length];
		int[] counter = new int[this.parts.length];
		boolean again = true;
		while(again){
			for(int i=0; i<this.parts.length; i++){
				partsBuild[i] = (ItemStack) partsList[i].get(counter[i]);
			}
			list.add(build(partsBuild));
			for(int i=0; i<this.parts.length; i++){
				counter[i]++;
				int max = partsList[i].size();
				again = counter[i]<max;
				if(again){
					break;
				}
				counter[i]-=max;
			}
		}
	}
	
}
