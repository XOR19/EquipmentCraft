package equipmentcraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;


public interface ItemToolPart {
	
	public boolean renderAtTool();

	public IIcon getIconFor(ItemTool itemTool);

	public void registerIconFor(ItemTool itemTool, IIconRegister iconRegister);
	
	public ToolPart getToolPart();
	
}
