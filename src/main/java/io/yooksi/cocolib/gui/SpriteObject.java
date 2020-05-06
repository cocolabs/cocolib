package io.yooksi.cocolib.gui;

import net.minecraft.client.MainWindow;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class represents a game sprite ready to be drawn on screen.
 * <p>Use {@link Builder} to build a new {@code SpriteObject}.
 */
public class SpriteObject {

	/** Set of 2D coordinates holding the position of the sprite relative to main window */
	public static class Coordinates {

		public final int x;
		public final int y;

		public Coordinates(int x, int y) {
			this.x = x; this.y = y;
		}
	}

	private int windowScaledHeight;
	private int windowScaledWidth;

	private final int height;
	private final int width;

	/** Texture location for this sprite */
	@NotNull private final ResourceLocation location;

	/** Position relative to the default size of the main window screen */
	@NotNull private final Coordinates originPos;

	/** Position of the sprite relative to the current size of the main window screen */
	@NotNull private Coordinates currentPos;

	/** Set of 2D coordinates used for sprite {@code UV} mapping */
	@NotNull private final Coordinates uv;

	private SpriteObject(ResourceLocation location, Coordinates position,
						 Coordinates uvCoordinates, int width, int height) {

		this.location = location;
		originPos = position;
		currentPos = position;
		uv = uvCoordinates;

		windowScaledHeight = GuiHelper.DEFAULT_WINDOW_HEIGHT;
		windowScaledWidth = GuiHelper.DEFAULT_WINDOW_WIDTH;

		this.height = height;
		this.width = width;
	}

	public static class Builder {

		private int height;
		private int width;
		@NotNull private final ResourceLocation texture;
		@Nullable private Coordinates coordinates;
		@Nullable private Coordinates uv;

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

		if (!doesScaledSizeMatch(window))
		{
			currentPos = GuiHelper.getScaledPosition(originPos.x, originPos.y, window);
			windowScaledWidth = window.getScaledWidth();
			windowScaledHeight = window.getScaledHeight();
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
		return window.getScaledHeight() == windowScaledHeight 
				&& window.getScaledWidth() == windowScaledWidth;
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
		return width;
	}

	public int getHeight() {
		return height;
	}
}
