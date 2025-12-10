package github.aqumpusaxy.mana_jade.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.phys.Vec2;
import org.joml.Matrix4f;
import snownee.jade.api.ui.Element;

public class ManaElement extends Element {
    private static final int TEXTURE_WIDTH = 16;
    private static final int TEXTURE_HEIGHT = 16;
    private static final Vec2 DEFAULT_SIZE = new Vec2(16, 16);
    private static final ResourceLocation MANA_TEXTURE_LOCATION = ResourceLocation
            .fromNamespaceAndPath("botania", "block/mana_water");

    public ManaElement() {
    }

    @Override
    public Vec2 getSize() {
        return DEFAULT_SIZE;
    }

    @Override
    public void render(GuiGraphics guiGraphics, float x, float y, float maxX, float maxY) {
        Vec2 size = getCachedSize();

        RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
        Matrix4f matrix = guiGraphics.pose().last().pose();

        int xTileCount = (int) (maxX / TEXTURE_WIDTH);
        float xRemainder = maxX - xTileCount * TEXTURE_WIDTH;
        int yTileCount = (int) (maxY / TEXTURE_HEIGHT);
        float yRemainder = maxY - yTileCount * TEXTURE_HEIGHT;

        for (int xTile = 0; xTile <= xTileCount; xTile++) {
            for (int yTile = 0; yTile <= yTileCount; yTile++) {
                float width = xTile == xTileCount ? xRemainder : TEXTURE_WIDTH;
                float height = yTile == yTileCount ? yRemainder : TEXTURE_HEIGHT;
                float tileX = x + xTile * TEXTURE_WIDTH;
                float tileY = y + yTile * TEXTURE_HEIGHT;

                if (width > 0 && height > 0) {
                    float maskTop = TEXTURE_HEIGHT - height;
                    float maskRight = TEXTURE_WIDTH - width;

                    TextureAtlasSprite manaTextureSprite = Minecraft.getInstance()
                            .getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(MANA_TEXTURE_LOCATION);
                    float uMin = manaTextureSprite.getU0();
                    float uMax = manaTextureSprite.getU1();
                    float vMin = manaTextureSprite.getV0();
                    float vMax = manaTextureSprite.getV1();

                    uMax = uMax - (maskRight / 16F * (uMax - uMin));
                    vMax = vMax - (maskTop / 16F * (vMax - vMin));

                    BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferBuilder.vertex(matrix, tileX, tileY + height, 0).uv(uMin, vMax).endVertex();
                    bufferBuilder.vertex(matrix, tileX + width, tileY + height, 0).uv(uMax, vMax).endVertex();
                    bufferBuilder.vertex(matrix, tileX + width, tileY, 0).uv(uMax, vMin).endVertex();
                    bufferBuilder.vertex(matrix, tileX, tileY, 0).uv(uMin, vMin).endVertex();
                    BufferUploader.drawWithShader(bufferBuilder.end());
                }
            }
        }
    }
}