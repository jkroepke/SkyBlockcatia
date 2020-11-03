package com.stevekung.skyblockcatia.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.renderer.GlStateManager;

@Mixin(GuiNewChat.class)
public abstract class GuiNewChatMixin extends Gui
{
    @Inject(method = "drawChat(I)V", at = @At("HEAD"))
    private void renderDepthPre(int updateCounter, CallbackInfo info)
    {
        GlStateManager.disableDepth();
    }

    @Inject(method = "drawChat(I)V", at = @At("RETURN"))
    private void renderDepthPost(int updateCounter, CallbackInfo info)
    {
        GlStateManager.enableDepth();
    }
}