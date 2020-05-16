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
package io.yooksi.cocolib.gui;

import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.TextureManager;
import org.jetbrains.annotations.Contract;

import static io.yooksi.cocolib.gui.PlaneGeometry.Dimensions;

/**
 * This class contains helpful method and fields for working with GUI.
 */
public class GuiElement {

	/**
	 * Current <i>scaled</i> size of the Minecraft main window.
	 * <p>Updated from {@link #doesScaledSizeMatch()}
	 */
	private final Dimensions SCALED_WINDOW_SIZE = new Dimensions(427, 240);

	public static void bindAndDrawTexture(SpriteObject sprite) {

		Minecraft instance = Minecraft.getInstance();
		TextureManager manager = instance.getTextureManager();

		sprite.updateScaledPosition(false);

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

	/**
	 * Check if {@code MainWindow} size has changed since last time
	 * this method was called and update the dimension values if needed
	 *
	 * @return {@code true} if {@link #SCALED_WINDOW_SIZE} matches the {@code MainWindow} size.
	 */
	boolean doesScaledSizeMatch() {

		MainWindow window = Minecraft.getInstance().getMainWindow();

		if (!SCALED_WINDOW_SIZE.isEqual(window.getScaledWidth(), window.getScaledHeight()))
		{
			SCALED_WINDOW_SIZE.update(window.getScaledWidth(), window.getScaledHeight());
			return false;
		}
		else return true;
	}

	/**
	 * @return {@code Dimensions} that match the {@link #SCALED_WINDOW_SIZE}.
	 */
	@Contract(pure = true)
	public Dimensions getScaledWindowSize() {
		return new Dimensions(SCALED_WINDOW_SIZE.getWidth(), SCALED_WINDOW_SIZE.getHeight());
	}
}
