package io.yooksi.cocolib.gui;

import net.minecraft.client.MainWindow;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import static io.yooksi.cocolib.gui.PlaneGeometry.*;

/**
 * This class represents a game sprite ready to be drawn on screen.
 * <p>Use {@link Builder} to build a new {@code SpriteObject}.
 */
public class SpriteObject {

	/** Size of the sprite object */
	private final Dimensions size;

	private final Dimensions scaledWindowSize;

	/** Texture location for this sprite */
	private final ResourceLocation location;

	/** Position relative to the default size of the main window screen */
	private final Coordinates originPos;

	/** Position of the sprite relative to the current size of the main window screen */
	private Coordinates currentPos;

	/** Set of 2D coordinates used for sprite {@code UV} mapping */
	private final Coordinates uv;

	private SpriteObject(ResourceLocation location, Coordinates position,
						 Coordinates uvCoordinates, int width, int height) {

		this.location = location;
		originPos = position;
		currentPos = position;
		uv = uvCoordinates;

		size = new Dimensions(width, height);
		scaledWindowSize = new Dimensions(
				GuiHelper.DEFAULT_WINDOW_WIDTH,
				GuiHelper.DEFAULT_WINDOW_HEIGHT
		);
	}

	public static class Builder {

		private int width, height;
		private final ResourceLocation texture;
		@Nullable private Coordinates uv, coordinates;

		private Builder(ResourceLocation texture) {
			this.texture = texture;
		}

		@Contract(value = "_, _-> new", pure = true)
		public static Builder create(String namespace, String path) {
			return new Builder(new ResourceLocation(namespace, path));
		}
		@Contract(value = "_-> new", pure = true)
		public static Builder create(ResourceLocation location) {
			return new Builder(location);
		}

		@Contract("_, _ -> this")
		public Builder withSize(int width, int height) {

			this.width = width;
			this.height = height;
			return this;
		}

		@Contract("_, _ -> this")
		public Builder withPos(int x, int y) {

			coordinates = new Coordinates(x, y);
			return this;
		}

		/**
		 * Always call this method in the chain after {@link #withSize(int, int)}.
		 */
		@Contract("_, _ -> this")
		public Builder withUV(int u, int v) {

			uv = new Coordinates(u, v);
			return this;
		}

		@Contract(value = "-> new", pure = true)
		public SpriteObject build() {
			return new SpriteObject(texture, coordinates == null ? new Coordinates(0, 0) :
					coordinates, uv == null ? new Coordinates(0, 0) : uv, width, height);
		}
	}

	/**
	 * Update sprite coordinates to scale with the current window size.
	 * @param window instance of Minecraft {@code MainWindow}.
	 */
	public void updateScaledPosition(MainWindow window) {

		// Calculate scaled position only if window size has changed
		if (!doesScaledSizeMatch(window))
		{
			currentPos = GuiHelper.getScaledPosition(originPos.x, originPos.y, window);

			scaledWindowSize.width = window.getScaledWidth();
			scaledWindowSize.height = window.getScaledHeight();
		}
	}

	public ResourceLocation getTexture() {
		return location;
	}

	/**
	 * @param window instance of {@code MainWindow} to compare size with
	 * @return {@code true} if the stored scaled window width and height match the given window
	 */
	public boolean doesScaledSizeMatch(MainWindow window) {
		return window.getScaledHeight() == size.height && window.getScaledWidth() == size.width;
	}

	public Coordinates getOriginalPosition() {
		return originPos;
	}

	/**
	 * @see #getX()
	 * @see #getY()
	 */
	public Coordinates getCurrentPos() {
		return currentPos;
	}

	/**
	 * @return the sprite position in the main window along {@code x} axis
	 */
	public int getX() {
		return currentPos.x;
	}

	/**
	 * @return the sprite position in the main window along {@code y} axis
	 */
	public int getY() {
		return currentPos.y;
	}

	/**
	 * @return the sprite {@code UV} mapping coordinate along {@code x} axis
	 */
	public int getU() {
		return uv.x;
	}

	/**
	 * @return the sprite {@code UV} mapping coordinate along {@code y} axis
	 */
	public int getV() {
		return uv.y;
	}

	public int getWidth() {
		return size.width;
	}

	public int getHeight() {
		return size.height;
	}
}
