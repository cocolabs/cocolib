package io.yooksi.cocolib.gui;

import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.TextureManager;

/**
 * This class contains helpful method and fields for working with GUI.
 */
public class GuiHelper {

	/** Default width of the Minecraft main window */
	public static final int DEFAULT_WINDOW_WIDTH = 427;

	/** Default height of the Minecraft main window */
	public static final int DEFAULT_WINDOW_HEIGHT = 240;

	/** Prevent class instantiation */
	private GuiHelper() {}

	/**
	 * Returns a set of coordinates for a {@code GUI} element with the given width
	 * and position on the {@code y} axis in the main window.
	 *
	 * @param width size of the element along the {@code x} axis.
	 * @param y position on the {@code y} axis (from bottom).
	 * @param window instance of Minecraft {@code MainWindow}.
	 */
	public static SpriteObject.Coordinates getCenteredPosition(int width, int y, MainWindow window) {

		int coordX = window.getScaledWidth() / 2 - width / 2;
		int coordY = window.getScaledHeight() - y;

		return new SpriteObject.Coordinates(coordX, coordY);
	}

	/**
	 * Returns a set of coordinates for a {@code GUI} element with the given
	 * position in the default window size coordinate grid. This way of resolving
	 * position should work for any set of given coordinates whether you want to
	 * center an element or have a free form position.
	 *
	 * @param x initial position of element along {@code x} axis.
	 * @param y initial position of element along {@code y} axis.
	 * @param window instance of Minecraft {@code MainWindow}.
	 */
	public static SpriteObject.Coordinates getScaledPosition(int x, int y, MainWindow window) {

		int coordX = window.getScaledWidth() / 2 - (DEFAULT_WINDOW_WIDTH - x) + DEFAULT_WINDOW_WIDTH / 2;
		int coordY = window.getScaledHeight() - (DEFAULT_WINDOW_HEIGHT - y);

		return new SpriteObject.Coordinates(coordX, coordY);
	}

	public static void bindAndDrawTexture(SpriteObject sprite) {

		TextureManager manager = Minecraft.getInstance().getTextureManager();

		// Bind sprite map
		manager.bindTexture(sprite.getTexture());

		// Draw the texture on screen
		drawTexturedModalRect(sprite.getX(), sprite.getY(), sprite.getU(), sprite.getV(),
				sprite.getWidth(), sprite.getHeight());

		// Rebind HUD sprite map
		manager.bindTexture(AbstractGui.GUI_ICONS_LOCATION);
	}

	/**
	 * Draws the binded texture to the screen.
	 *
	 * @param x position to draw the texture along the {@code x} axis.
	 * @param y position to draw the texture along the {@code y} axis.
	 * @param u texture {@code UV} mapping coordinate along {@code x} axis.
	 * @param v texture {@code UV} mapping coordinate along {@code y} axis.
	 * @param width width of the texture to draw.
	 * @param height height of the texture to draw.
	 */
	public static void drawTexturedModalRect(int x, int y, int u, int v, int width, int height) {
		AbstractGui.blit(x, y, u, v, width, height, 256, 256);
	}
}
