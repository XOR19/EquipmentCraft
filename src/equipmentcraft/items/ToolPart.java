package equipmentcraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;


public class ToolPart extends Item implements ItemToolPart{
	
	private boolean renderAtTool;
	
	private MaterialFilter materialFilter;
	
	@Override
	public boolean renderAtTool() {
		return this.renderAtTool;
	}
	
	@Override
	public IIcon getIconFor(ItemTool itemTool) {
		return this.itemIcon;
	}

	@Override
	public void registerIconFor(ItemTool itemTool, IIconRegister iconRegister) {/**/}

	@Override
	public ToolPart getToolPart() {
		return this;
	}
	
}
