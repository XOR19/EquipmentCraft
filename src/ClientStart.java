import java.util.ArrayList;
import java.util.List;

import net.minecraft.launchwrapper.Launch;

/**
 * 
 * Run this in case that Minecraft is another project
 * 
 * @author XOR
 *
 */
public class ClientStart {

	/**
	 * 
	 * Start the client
	 * 
	 * @param args only allowed --username yourName
	 */
	public static void main(String args[]){
		System.setProperty("fml.ignoreInvalidMinecraftCertificates", "true");
		String userName = System.getProperty("user.name", "ToolCraft"+(System.currentTimeMillis()%1000));
		String assetsDir="";
		List<String> coreMods = new ArrayList<String>();
		if(args!=null){
			for(int i=0; i<args.length-1; i++){
				if(args[i].equals("--username")){
					userName = args[i+1];
				}else if(args[i].equals("--assetsDir")){
					assetsDir = args[i+1];
				}else if(args[i].equals("--coreMod")){
					coreMods.add(args[i+1]);
				}
			}
		}
		if(!coreMods.isEmpty())
			addCoreMods(coreMods.toArray(new String[coreMods.size()]));
		Launch.main(new String[]{"--version", "1.6", "--tweakClass", "cpw.mods.fml.common.launcher.FMLTweaker", "--userProperties", "{}", "--accessToken", "0", "--username", userName, assetsDir!=""?"--assetsDir":"", assetsDir});
	}
	
	private static void addCoreMods(String...names){
		String s = System.getProperty("fml.coreMods.load", "");
		for(String name:names){
			if(name.isEmpty())
				continue;
			if(!s.isEmpty()){
				s += ",";
			}
			s += name;
		}
		System.setProperty("fml.coreMods.load", s);
	}
	
}
