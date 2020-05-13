/*
 *  Copyright (C) 2020 Matthew Cain
 *
 *  This file is part of CocoLib.
 *
 *  CocoLib is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CocoLib. If not, see <https://www.gnu.org/licenses/>.
 */
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
		CocoLogger.info("CocoLib pre-initialized");
	}
}
