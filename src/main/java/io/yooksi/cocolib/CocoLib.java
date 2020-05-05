package io.yooksi.cocolib;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;

@Mod(CocoLib.MOD_ID)
public class CocoLib {

	public static final String MOD_ID = "cocolib";

	public CocoLib() {

		// Initialize mod logger
		CocoLogger.init(LogManager.getLogger());

		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
	}

	private void setup(final FMLCommonSetupEvent event) {
		CocoLogger.info("CocoLib has been initialized");
	}
}
