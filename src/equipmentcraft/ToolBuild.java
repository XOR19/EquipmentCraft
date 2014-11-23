package equipmentcraft;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.world.World;
import equipmentcraft.items.ItemCore;
import equipmentcraft.items.ItemParts;

public class ToolBuild extends ShapedRecipes {
	
	private ItemCore itemCore;
	
	private int[] indices;
	
	private int partCount;
	
	public static ToolBuild create(ItemCore core, int width, int...indices){
		ItemParts[] parts = core.getParts();
		if(width<1)
			throw new IllegalArgumentException("bad width");
		if(indices.length%width!=0)
			throw new IllegalArgumentException("bad indices length");
		int height = indices.length/width;
		if(height<1)
			throw new IllegalArgumentException("bad height");
		ItemStack[] items = new ItemStack[indices.length];
		boolean[] used = new boolean[parts.length];
		for(int i=0; i<indices.length; i++){
			int index = indices[i];
			if(index!=-1){
				if(used[index])
					throw new IllegalArgumentException("duplicated part uses "+index);
				items[i] = new ItemStack(parts[index]);
				used[index] = true;
			}
		}
		for(int i=0; i<used.length; i++){
			if(!used[i])
				throw new IllegalArgumentException("part not used "+i);
		}
		return new ToolBuild(width, height, items, parts.length, indices, core);
	}
	
	public ToolBuild(int width, int height, ItemStack[] items, int partCount, int[] indices, ItemCore itemCore) {
		super(width, height, items, new ItemStack(itemCore));
		this.itemCore = itemCore;
		this.indices = indices;
		this.partCount = partCount;
	}
	
	@Override
	public boolean matches(InventoryCrafting crafting, World world) {
		return getCraftingResult(crafting) != null;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting crafting) {
		ItemStack[] parts = getParts(crafting);
		if (parts == null) return null;
		return this.itemCore.build(parts);
	}
	
	public ItemStack[] getParts(InventoryCrafting crafting) {
		ItemStack[] parts = new ItemStack[this.partCount];
		for (int i = 0; i <= 3 - this.recipeWidth; i++) {
			for (int j = 0; j <= 3 - this.recipeHeight; j++) {
				if (this.checkMatch(crafting, i, j, false, parts)) {
					return parts;
				}
			}
		}
		return null;
	}
	
	@SuppressWarnings("null")
	private boolean checkMatch(InventoryCrafting crafting, int sx, int sy, boolean mirror, ItemStack[] parts) {
		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				int xx = x - sx;
				int yy = y - sy;
				ItemStack should = null;
				int index = -1;
				if (xx >= 0 && yy >= 0 && xx < this.recipeWidth && yy < this.recipeHeight) {
					if (mirror) {
						index = this.recipeWidth - xx - 1 + yy * this.recipeWidth;
					} else {
						index = xx + yy * this.recipeWidth;
					}
					should = this.recipeItems[index];
					index = this.indices[index];
				}
				
				ItemStack is = crafting.getStackInRowAndColumn(x, y);
				
				if ((is != null || should != null) && (is == null && should != null || is != null && should == null || should.getItem() != is.getItem())) {
					return false;
				}
				
				if(index!=-1){
					parts[index] = is;
				}
			}
		}
		
		return true;
	}
	
}
