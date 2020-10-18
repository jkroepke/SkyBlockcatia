package com.stevekung.skyblockcatia.gui.toasts;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.stevekung.stevekungslib.utils.ColorUtils;
import com.stevekung.stevekungslib.utils.TextComponentUtils;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.ToastGui;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.SkullTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class VisitIslandToast implements IToast
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("skyblockcatia:textures/gui/visit_island_toasts.png");
    private final ItemStack itemStack;
    private final String name;

    public VisitIslandToast(String name)
    {
        this.itemStack = VisitIslandToast.getPlayerHead(name);
        this.name = name;
    }

    @SuppressWarnings("deprecation")
    @Override
    public IToast.Visibility func_230444_a_(MatrixStack matrixStack, ToastGui toastGui, long delta)
    {
        toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        AbstractGui.blit(matrixStack, 0, 0, 0, 0, 160, 32, 160, 32);
        toastGui.getMinecraft().fontRenderer.func_243246_a(matrixStack, TextComponentUtils.formatted(this.name, TextFormatting.BOLD), 30, 7, ColorUtils.toDecimal(255, 255, 85));
        toastGui.getMinecraft().fontRenderer.drawString(matrixStack, "is visiting Your Island!", 30, 18, ColorUtils.toDecimal(255, 255, 255));
        toastGui.getMinecraft().getItemRenderer().renderItemAndEffectIntoGUI(this.itemStack, 8, 8);
        return delta >= 5000L ? IToast.Visibility.HIDE : IToast.Visibility.SHOW;
    }

    private static ItemStack getPlayerHead(String name)
    {
        ItemStack itemStack = new ItemStack(Items.PLAYER_HEAD);
        CompoundNBT compound = new CompoundNBT();
        GameProfile profile = SkullTileEntity.updateGameProfile(new GameProfile(null, name));
        compound.remove("SkullOwner");
        compound.put("SkullOwner", NBTUtil.writeGameProfile(new CompoundNBT(), profile));
        itemStack.setTag(compound);
        return itemStack;
    }
}