package io.yooksi.cocolib.gui;

import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class represents a game sprite ready to be drawn on screen.
 * <p>Use {@link Builder} to build a new {@code SpriteObject}.
 */
public class SpriteObject {

	/**
	 * This class holds a set of 2D coordinates used for {@code UV} mapping
	 */
	public static class UVCoordinates {

		public final int u;
		public final int v;

		public UVCoordinates(int u, int v) {
			this.u = u; this.v = v;
		}
	}

	/**
	 * This class holds a set of 2D coordinates in the Minecraft main window
	 * with <i>default</i> size intended for positioning sprites.
	 */
	public static class Coordinates {

		/** Width of the default Minecraft main window*/
		public static final int MAX_X = GuiHelper.DEFAULT_WINDOW_WIDTH;

		/** Height of the default Minecraft main window */
		public static final int MAX_Y = GuiHelper.DEFAULT_WINDOW_HEIGHT;

		public final int x;
		public final int y;

		public Coordinates(int x, int y) {

			this.x = Math.min(x, MAX_X);
			this.y = Math.min(y, MAX_Y);
		}
	}

	private int windowScaledHeight;
	private int windowScaledWidth;

	private final int height;
	private final int width;

	/** Texture location for this sprite */
	@NotNull private final ResourceLocation texture;

	/** Set of 2D coordinates holding the position of the sprite relative to main window */
	@NotNull private Coordinates coordinates;

	/** Set of 2D coordinates used for sprite {@code UV} mapping */
	@NotNull private final UVCoordinates uv;

	private SpriteObject(ResourceLocation location, Coordinates position,
						 UVCoordinates uvCoordinates, int width, int height) {

		this.texture = location;
		this.coordinates = position;
		this.uv = uvCoordinates;

		this.windowScaledHeight = GuiHelper.DEFAULT_WINDOW_HEIGHT;
		this.windowScaledWidth = GuiHelper.DEFAULT_WINDOW_WIDTH;

		this.height = height;
		this.width = width;
	}

	public static class Builder {

		private int height;
		private int width;
		@NotNull private final ResourceLocation texture;
		@Nullable private Coordinates coordinates;
		@Nullable private UVCoordinates uv;

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

			uv = new UVCoordinates(u, v);
			return this;
		}

		@Contract(value = "-> new", pure = true)
		public SpriteObject build() {
			return new SpriteObject(texture, coordinates == null ? new Coordinates(0, 0) :
					coordinates, uv == null ? new UVCoordinates(0, 0) : uv, width, height);
		}
	}

	/**
	 * Update sprite coordinates to scale with the current window size.
	 * @param window instance of Minecraft {@code MainWindow}.
	 */
	public void updateScaledPosition(MainWindow window) {

		if (!doesScaledSizeMatch(window))
		{
			coordinates = GuiHelper.getScaledPosition(getX(), getY(), window);
			windowScaledWidth = window.getScaledWidth();
			windowScaledHeight = window.getScaledHeight();
		}
	}

	public ResourceLocation getTexture() {
		return texture;
	}

	/**
	 * @param window instance of {@code MainWindow} to compare size with
	 * @return {@code true} if the stored scaled window width and height match the given window
	 */
	public boolean doesScaledSizeMatch(MainWindow window) {
		return window.getScaledHeight() == windowScaledHeight 
				&& window.getScaledWidth() == windowScaledWidth;
	}

	/**
	 * @see #getX()
	 * @see #getY()
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * @return the sprite position in the main window along {@code x} axis
	 */
	public int getX() {
		return coordinates.x;
	}

	/**
	 * @return the sprite position in the main window along {@code y} axis
	 */
	public int getY() {
		return coordinates.y;
	}

	/**
	 * @return the sprite {@code UV} mapping coordinate along {@code x} axis
	 */
	public int getU() {
		return uv.u;
	}

	/**
	 * @return the sprite {@code UV} mapping coordinate along {@code y} axis
	 */
	public int getV() {
		return uv.v;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
