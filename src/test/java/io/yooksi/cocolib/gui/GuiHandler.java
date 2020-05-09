package io.yooksi.cocolib.gui;

import io.yooksi.cocolib.CocoLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GuiHandler {

	private static final ResourceLocation MAPPED_TEST =
			new ResourceLocation(CocoLib.MOD_ID, "textures/gui/mapped_test.png");

	public final SpriteObject XP_BAR_ELEMENT = SpriteObject.Builder.create(
			AbstractGui.GUI_ICONS_LOCATION).withPos(Alignment.BOTTOM_CENTER, 0, 24)
			.withSize(182, 5).withUV(0, 79).build();

	public final SpriteObject TOP_LEFT_ELEMENT = SpriteObject.Builder.create(MAPPED_TEST)
			.withPos(Alignment.TOP_LEFT, 15, 15).withSize(123, 48).build();

	public final SpriteObject TOP_RIGHT_ELEMENT = SpriteObject.Builder.create(MAPPED_TEST)
			.withPos(Alignment.TOP_RIGHT).withUV(0, 48).withSize(123, 48).build();

	public final SpriteObject BOTTOM_LEFT_ELEMENT = SpriteObject.Builder.create(MAPPED_TEST)
			.withPos(Alignment.BOTTOM_LEFT, 5, 5)
			.withUV(37, 96).withSize(37, 46).build();

	public final SpriteObject BOTTOM_RIGHT_ELEMENT = SpriteObject.Builder.create(MAPPED_TEST)
			.withPos(Alignment.BOTTOM_RIGHT, 5, 5)
			.withUV(0, 96).withSize(37, 46).build();

	public final SpriteObject CROSSHAIRS = SpriteObject.Builder.create(MAPPED_TEST)
			.withPos(Alignment.CENTER).withUV(181, 37).withSize(19, 20).build();

	@SubscribeEvent
	public void onPreRenderOverlay(RenderGameOverlayEvent.Pre event) {

		if (Minecraft.getInstance().playerController != null) {

			// This element should perfectly draw over vanilla xp bar
			GuiElement.bindAndDrawTexture(XP_BAR_ELEMENT);

			// This element should draw in top left corner of the screen
			GuiElement.bindAndDrawTexture(TOP_LEFT_ELEMENT);

			// This element should draw in top right corner of the screen
			GuiElement.bindAndDrawTexture(TOP_RIGHT_ELEMENT);

			// This element should draw in the middle of the screen
			GuiElement.bindAndDrawTexture(CROSSHAIRS);

			// This element should draw in the bottom left corner of the screen
			GuiElement.bindAndDrawTexture(BOTTOM_LEFT_ELEMENT);

			// This element should draw in the bottom right corner of the screen
			GuiElement.bindAndDrawTexture(BOTTOM_RIGHT_ELEMENT);
		}
	}
}