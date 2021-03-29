package com.stevekung.skyblockcatia.gui.toasts;

import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.stevekung.skyblockcatia.utils.skyblock.SBRenderUtils;
import com.stevekung.stevekungslib.utils.ColorUtils;
import com.stevekung.stevekungslib.utils.TextComponentUtils;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.ToastGui;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class GiftToast implements IToast
{
    private final Random rand = new Random();
    private final ResourceLocation texture;
    private final ToastUtils.ItemDrop itemDrop;
    private final long maxDrawTime;

    public GiftToast(ItemStack itemStack, ToastUtils.DropType rarity, boolean santaGift)
    {
        this.itemDrop = new ToastUtils.ItemDrop(itemStack, rarity);
        this.maxDrawTime = this.itemDrop.getType().getTime();
        this.texture = new ResourceLocation("skyblockcatia:textures/gui/gift_toasts_" + (santaGift ? 1 : Integer.valueOf(1 + this.rand.nextInt(2))) + ".png");
    }

    @SuppressWarnings("deprecation")
    @Override
    public IToast.Visibility func_230444_a_(MatrixStack matrixStack, ToastGui toastGui, long delta)
    {
        ToastUtils.ItemDrop drop = this.itemDrop;
        ItemStack itemStack = drop.getItemStack();
        ITextComponent itemName = itemStack.getDisplayName();

        if (itemStack.getItem() == Items.ENCHANTED_BOOK)
        {
            itemName = itemStack.getTooltip(null, ITooltipFlag.TooltipFlags.NORMAL).get(1);
        }

        toastGui.getMinecraft().getTextureManager().bindTexture(this.texture);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        AbstractGui.blit(matrixStack, 0, 0, 0, 0, 160, 32, 160, 32);
        toastGui.getMinecraft().fontRenderer.drawText(matrixStack, TextComponentUtils.formatted(drop.getType().getName(), TextFormatting.BOLD), 30, 7, ColorUtils.rgbToDecimal(drop.getType().getColor()));
        SBRenderUtils.drawLongItemName(toastGui, matrixStack, delta, 0L, this.maxDrawTime, itemName, false);
        RenderSystem.translatef(0.0F, 0.0F, -32.0F);
        toastGui.getMinecraft().getItemRenderer().renderItemAndEffectIntoGUI(itemStack, 8, 8);
        toastGui.getMinecraft().getItemRenderer().renderItemOverlayIntoGUI(toastGui.getMinecraft().fontRenderer, itemStack, 8, 8, null);
        RenderSystem.disableLighting();
        return delta >= this.maxDrawTime ? IToast.Visibility.HIDE : IToast.Visibility.SHOW;
    }
}