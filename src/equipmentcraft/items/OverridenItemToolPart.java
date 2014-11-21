package equipmentcraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;


public class OverridenItemToolPart implements ItemToolPart {
	
	private ToolPart toolPart;
	
	private IIcon icon;
	
	private String iconName;
	
	public OverridenItemToolPart(ToolPart toolPart, String iconName){
		this.toolPart = toolPart;
		this.iconName = iconName;
	}
	
	@Override
	public boolean renderAtTool() {
		return this.iconName!=null;
	}
	
	@Override
	public IIcon getIconFor(ItemTool itemTool) {
		return this.icon;
	}

	@Override
	public void registerIconFor(ItemTool itemTool, IIconRegister iconRegister) {
		if(this.iconName!=null){
			this.icon = iconRegister.registerIcon(this.iconName);
		}
	}

	@Override
	public ToolPart getToolPart() {
		return this.toolPart;
	}
	
}
