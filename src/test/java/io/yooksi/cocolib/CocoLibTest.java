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
