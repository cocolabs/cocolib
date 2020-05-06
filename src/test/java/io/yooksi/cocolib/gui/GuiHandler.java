package io.yooksi.cocolib.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GuiHandler {

	public final SpriteObject XP_BAR_TEST = SpriteObject.Builder.create(
			AbstractGui.GUI_ICONS_LOCATION).withPos(122, 211)
			.withSize(182, 5).withUV(0, 79).build();

	@SubscribeEvent
	public void onPreRenderOverlay(RenderGameOverlayEvent.Pre event) {

		if (Minecraft.getInstance().playerController != null) {
			GuiHelper.bindAndDrawTexture(XP_BAR_TEST);
		}
	}
}