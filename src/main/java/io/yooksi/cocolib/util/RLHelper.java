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
