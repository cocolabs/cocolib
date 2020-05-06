package io.yooksi.cocolib;

import io.yooksi.cocolib.gui.GuiHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;

@Mod(CocoLibTest.MOD_ID)
public class CocoLibTest {

	public static final String MOD_ID = "cocolib";

	public CocoLibTest() {

		MinecraftForge.EVENT_BUS.register(new GuiHandler());
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
	}

	private void setup(final FMLCommonSetupEvent event) {
	}
}
