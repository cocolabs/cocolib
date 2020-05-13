package io.yooksi.cocolib;

import io.yooksi.cocolib.gui.GuiHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(CocoLibTest.MOD_ID)
public class CocoLibTest {

	public static final String MOD_ID = "cocolib";

	public CocoLibTest() {
		MinecraftForge.EVENT_BUS.register(new GuiHandler());
	}
}
