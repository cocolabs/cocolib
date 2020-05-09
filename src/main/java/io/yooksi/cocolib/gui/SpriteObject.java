package io.yooksi.cocolib.gui;

import io.yooksi.cocolib.CocoLogger;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Contract;

import static io.yooksi.cocolib.gui.PlaneGeometry.*;

/**
 * This class represents a game sprite ready to be drawn on screen.
 * <p>Use {@link Builder} to build a new {@code SpriteObject}.
 */
public class SpriteObject extends GuiElement {

	/** Texture location for this sprite */
	private final ResourceLocation location;

	/** Alignment of the sprite relative to main window screen. */
	public final Alignment alignment;

	/**
	 * Values to offset from the edge of the main window screen.
	 * <p>The offset direction depends on sprite {@link #alignment}.
	 */
	public final Dimensions offset;

	/** Position relative to the size of the main window screen */
	private Coordinates position;

	/** Size of the sprite object */
	private final Dimensions size;

	/** Set of 2D coordinates used for sprite {@code UV} mapping */
	private final Coordinates uv;

	private SpriteObject(ResourceLocation location, Alignment alignment, int offsetX,
						 int offsetY, int u, int v, int width, int height) {

		this.location = location;
		this.alignment = alignment;
		this.offset = new ConstantDimensions(offsetX, offsetY);

		if (width <= 1 || height <= 1) {
			CocoLogger.warn("Invalid sprite size [x: %d, y: %d", width, height);
		}
		size = new Dimensions(width, height);
		position = alignment.getPosition(getScaledWindowSize(), size, offset);
		uv = new Coordinates(u, v);
	}

	public static class Builder {

		private int width, height, offsetX, offsetY, u, v;
		private final ResourceLocation texture;
		private Alignment alignment = Alignment.TOP_LEFT;

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

		@Contract("_, _, _ -> this")
		public Builder withPos(Alignment alignment, int offsetX, int offsetY) {

			this.alignment = alignment;
			this.offsetX = offsetX;
			this.offsetY = offsetY;
			return this;
		}

		@Contract("_, -> this")
		public Builder withPos(Alignment alignment) {

			this.alignment = alignment;
			return this;
		}

		@Contract("_, _ -> this")
		public Builder withUV(int u, int v) {

			this.u = u;
			this.v = v;
			return this;
		}

		@Contract(value = "-> new", pure = true)
		public SpriteObject build() {
			return new SpriteObject(texture, alignment, offsetX, offsetY, u, v, width, height);
		}
	}

	/**
	 * Update sprite coordinates to scale with the current window size.
	 */
	public void updateScaledPosition() {

		// Calculate scaled position only if window size has changed
		if (!doesScaledSizeMatch()) {
			position = alignment.getPosition(getScaledWindowSize(), size, offset);
		}
	}

	public ResourceLocation getTexture() {
		return location;
	}

	/**
	 * @see #getX()
	 * @see #getY()
	 */
	public Coordinates getPosition() {
		return position;
	}

	/**
	 * @return the sprite position in the main window along {@code x} axis
	 */
	public int getX() {
		return position.x;
	}

	/**
	 * @return the sprite position in the main window along {@code y} axis
	 */
	public int getY() {
		return position.y;
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
		return size.getWidth();
	}

	public int getHeight() {
		return size.getHeight();
	}
}
