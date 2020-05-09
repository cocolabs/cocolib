package io.yooksi.cocolib.gui;

import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.TextureManager;

import static io.yooksi.cocolib.gui.PlaneGeometry.*;

/**
 * This class contains helpful method and fields for working with GUI.
 */
public class GuiElement {

	/** Default width of the Minecraft main window */
	public static final int DEFAULT_WINDOW_WIDTH = 427;

	/** Default height of the Minecraft main window */
	public static final int DEFAULT_WINDOW_HEIGHT = 240;

	/** Prevent class instantiation */
	private GuiElement() {}

	/**
	 * Returns a set of coordinates for a {@code GUI} element with the given width
	 * and position on the {@code y} axis in the main window.
	 *
	 * @param width size of the element along the {@code x} axis.
	 * @param y position on the {@code y} axis (from bottom).
	 * @param window instance of Minecraft {@code MainWindow}.
	 */
	public static Coordinates getCenteredPosition(int width, int y, MainWindow window) {

		int scaledX = window.getScaledWidth() / 2 - width / 2;
		int scaledY = window.getScaledHeight() - y;

		return new Coordinates(scaledX, scaledY);
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
	public static Coordinates getScaledPosition(int x, int y, MainWindow window) {

		int scaledX = window.getScaledWidth() / 2 - (DEFAULT_WINDOW_WIDTH - x) + DEFAULT_WINDOW_WIDTH / 2;
		int scaledY = window.getScaledHeight() - (DEFAULT_WINDOW_HEIGHT - y);

		return new Coordinates(scaledX, scaledY);
	}

	public static void bindAndDrawTexture(SpriteObject sprite) {

		Minecraft instance = Minecraft.getInstance();
		TextureManager manager = instance.getTextureManager();

		sprite.updateScaledPosition(instance.getMainWindow());

		// Bind sprite map
		manager.bindTexture(sprite.getTexture());

		// Draw the texture on screen
		drawTexturedModalRect(sprite.getX(), sprite.getY(), sprite.getU(), sprite.getV(),
				sprite.getWidth(), sprite.getHeight());

		// Rebind HUD sprite map
		manager.bindTexture(AbstractGui.GUI_ICONS_LOCATION);
	}

	/**
	 * Draws the texture bound to the screen.
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
