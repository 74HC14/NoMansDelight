package com.magafin.client.render;

import com.magafin.common.block.entity.FermentationBarrelBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import org.joml.Matrix4f;

public class FermentationBarrelRenderer implements BlockEntityRenderer<FermentationBarrelBlockEntity> {
    public FermentationBarrelRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(FermentationBarrelBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        // Проверка: есть ли что рендерить
        if (blockEntity.inputStack.isEmpty() || blockEntity.currentTexture == null || blockEntity.currentTexture.isEmpty()) {
            return;
        }

        // Высота слоя жидкости в зависимости от количества предметов (max 4)
        int count = blockEntity.inputStack.getCount();
        float height = 0.25f + (Math.min(count, 4) * 0.15f);

        // Получаем спрайт напрямую из атласа блоков
        ResourceLocation textureLocation = ResourceLocation.parse(blockEntity.currentTexture);
        TextureAtlas atlas = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);
        TextureAtlasSprite sprite = atlas.getSprite(textureLocation);

        // Если спрайт не найден, Minecraft вернет missing_no (черно-розовый),
        // но это лучше, чем серый квадрат, так как сразу видна ошибка пути.

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.translucent());
        Matrix4f pose = poseStack.last().pose();

        // Границы (чуть меньше полного блока, чтобы не "врезаться" в стенки)
        float min = 0f;
        float max = 1f;

        // Отрисовка плоскости
        // Используем вспомогательный метод для чистоты кода
        addVertex(consumer, pose, min, height, max, sprite.getU0(), sprite.getV1(), packedLight, packedOverlay);
        addVertex(consumer, pose, max, height, max, sprite.getU1(), sprite.getV1(), packedLight, packedOverlay);
        addVertex(consumer, pose, max, height, min, sprite.getU1(), sprite.getV0(), packedLight, packedOverlay);
        addVertex(consumer, pose, min, height, min, sprite.getU0(), sprite.getV0(), packedLight, packedOverlay);
    }

    private void addVertex(VertexConsumer consumer, Matrix4f pose, float x, float y, float z, float u, float v, int light, int overlay) {
        consumer.addVertex(pose, x, y, z)
                .setColor(-1)
                .setUv(u, v)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(0, 1, 0);
    }
}