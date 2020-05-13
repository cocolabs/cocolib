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
package io.yooksi.cocolib.util;

import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Contract;

/**
 * Tiny utility class to help find {@code ResourceLocation}.
 */
public final class RLHelper {

	private RLHelper() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @param path path to the resource within <i>minecraft</i> namespace.
	 * @return {@code ResourceLocation} pointing to Minecraft resource.
	 */
	@Contract("_ -> new")
	public static ResourceLocation getMCResourceLocation(String path) {
		return new ResourceLocation("minecraft", path);
	}

	/**
	 * @param id mod identifier to use as namespace.
	 * @param path path to the resource within given namespace.
	 * @return {@code ResourceLocation} pointing to Mod resource with provided path.
	 */
	@Contract("_, _ -> new")
	public static ResourceLocation getTextureLocation(String id, String path) {
		return new ResourceLocation(id, "textures/" + path);
	}
}
