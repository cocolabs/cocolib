package io.yooksi.cocolib.gui;

import net.minecraft.util.ResourceLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpriteObjectTest {

	@Test
	void shouldBuildCorrectValues() {

		ResourceLocation location = new ResourceLocation(
				"minecraft", "textures/gui/icons.png");

		final int width = 100;
		final int height = 100;

		final int x = 10;
		final int y = 72;
		final int u = 0;
		final int v = 5;
		
		final SpriteObject sprite = SpriteObject.Builder.create(
				location.getNamespace(), location.getPath())
				.withSize(width, height).withPos(x, y).withUV(u, v).build();

		ResourceLocation texture = sprite.getTexture();

		Assertions.assertEquals(location.getNamespace(), texture.getNamespace());
		Assertions.assertEquals(location.getPath(), texture.getPath());

		Assertions.assertEquals(width, sprite.getWidth());
		Assertions.assertEquals(height, sprite.getHeight());
		
		Assertions.assertEquals(x, sprite.getX());
		Assertions.assertEquals(y, sprite.getY());
		Assertions.assertEquals(u, sprite.getU());
		Assertions.assertEquals(v, sprite.getV());
	}
}
