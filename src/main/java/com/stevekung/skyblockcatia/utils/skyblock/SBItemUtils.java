package com.stevekung.skyblockcatia.utils.skyblock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.stevekung.skyblockcatia.event.handler.SkyBlockEventHandler;
import com.stevekung.skyblockcatia.utils.skyblock.api.InventoryType;
import com.stevekung.stevekungslib.utils.ItemUtils;
import com.stevekung.stevekungslib.utils.TextComponentUtils;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.datafix.fixes.*;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.Constants;

public class SBItemUtils
{
    public static final ImmutableList<String> BLACKLIST = ImmutableList.of("SNOW_BLASTER", "SNOW_CANNON");
    public static final ImmutableList<String> CLICKABLE = ImmutableList.of("WEIRD_TUBA", "BAT_WAND");

    public static List<ItemStack> decodeItem(JsonObject currentProfile, InventoryType type)
    {
        if (currentProfile.has(type.getApiName()))
        {
            List<ItemStack> itemStack = Lists.newArrayList();
            byte[] decode = Base64.getDecoder().decode(currentProfile.get(type.getApiName()).getAsJsonObject().get("data").getAsString().replace("\\u003d", "="));

            try
            {
                CompoundNBT compound = CompressedStreamTools.readCompressed(new ByteArrayInputStream(decode));
                ListNBT list = compound.getList("i", Constants.NBT.TAG_COMPOUND);

                for (int i = 0; i < list.size(); ++i)
                {
                    itemStack.add(SBItemUtils.flatteningItemStack(list.getCompound(i)));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            if (type == InventoryType.INVENTORY)
            {
                Collections.rotate(itemStack, -9);
            }
            return itemStack;
        }
        else
        {
            List<ItemStack> itemStack = Lists.newArrayList();
            ItemStack barrier = new ItemStack(Blocks.BARRIER);
            barrier.setDisplayName(TextComponentUtils.formatted(type.getName() + " is not available!", TextFormatting.RED).setStyle(Style.EMPTY.setItalic(false)));

            for (int i = 0; i < 36; ++i)
            {
                itemStack.add(barrier);
            }
            return itemStack;
        }
    }

    public static ItemStack flatteningItemStack(CompoundNBT apiCompound)
    {
        CompoundNBT newNbt = new CompoundNBT();
        String itemId = "minecraft:air";
        short oldItemId = apiCompound.getShort("id");
        short damage = apiCompound.getShort("Damage");
        CompoundNBT sbTag = apiCompound.getCompound("tag");
        String newItemReg = ItemStackDataFlattening.updateItem(ItemIntIDToString.getItem(oldItemId), damage);

        if (newItemReg != null)
        {
            itemId = EntityRenaming1510.ITEM_RENAME_MAP.getOrDefault(newItemReg, newItemReg);
        }
        else
        {
            try
            {
                String itemId2 = SBItemUtils.getKey(BlockStateFlatternEntities.MAP, (int)oldItemId);
                String newItemReg2 = ItemStackDataFlattening.updateItem(itemId2, damage);

                if (newItemReg2 != null)
                {
                    itemId = EntityRenaming1510.ITEM_RENAME_MAP.getOrDefault(newItemReg2, newItemReg2);
                }
                else
                {
                    itemId = itemId2;
                }
            }
            catch (NoSuchElementException e)
            {
                itemId = ItemIntIDToString.getItem(oldItemId);
            }

            if (itemId.equals("minecraft:potion"))
            {
                String potionId = PotionItems.POTION_IDS[damage & 127];

                if ((damage & 16384) == 16384)
                {
                    itemId = "minecraft:splash_potion";
                }
                sbTag.putString("Potion", potionId == null ? "minecraft:water" : potionId);
            }
            if (itemId.equals("minecraft:spawn_egg") && damage == 0)
            {
                itemId = "minecraft:polar_bear_spawn_egg";
            }
        }

        if (sbTag != null)
        {
            ListNBT loreList = new ListNBT();
            StringNBT name = StringNBT.valueOf(TextComponentUtils.toJson(sbTag.getCompound("display").getString("Name")));
            ListNBT sbLore = sbTag.getCompound("display").getList("Lore", Constants.NBT.TAG_STRING);

            for (int loreI = 0; loreI < sbLore.size(); loreI++)
            {
                loreList.add(StringNBT.valueOf(TextComponentUtils.toJson(sbLore.getString(loreI))));
            }

            if (sbTag.contains("SkullOwner"))
            {
                CompoundNBT skullOwner = sbTag.getCompound("SkullOwner");
                skullOwner.putIntArray("Id", ItemUtils.uuidToIntArray(skullOwner.getString("Id")));
                sbTag.put("SkullOwner", skullOwner);
            }

            if (sbTag.contains("ench"))
            {
                ListNBT enchantmentList = sbTag.getList("ench", Constants.NBT.TAG_COMPOUND);

                if (enchantmentList.size() == 0) // dummy enchantment
                {
                    enchantmentList.add(new CompoundNBT());
                }
                else
                {
                    for (int enchI = 0; enchI < enchantmentList.size(); enchI++)
                    {
                        int enchant2 = enchantmentList.getCompound(enchI).getInt("id");
                        enchantmentList.getCompound(enchI).putString("id", ItemStackEnchantmentFix.field_208047_a.getOrDefault(enchant2, "null"));
                    }
                }
                sbTag.put("Enchantments", enchantmentList);
            }

            CompoundNBT sbDisplay = sbTag.getCompound("display");
            sbDisplay.remove("Name");
            sbDisplay.remove("Lore");
            sbDisplay.put("Name", name);
            sbDisplay.put("Lore", loreList);

            sbTag.remove("ench");
            sbTag.put("display", sbDisplay);

            newNbt.putString("id", itemId);
            newNbt.putByte("Count", apiCompound.getByte("Count"));
            newNbt.put("tag", sbTag);
        }
        return ItemStack.read(newNbt);
    }

    public static ActionResult<ItemStack> getBlockedItem(ItemStack itemStack, PlayerEntity player, ActionResult<ItemStack> defaultValue)
    {
        if (SkyBlockEventHandler.isSkyBlock && !itemStack.isEmpty() && itemStack.hasTag())
        {
            CompoundNBT extraAttrib = itemStack.getTag().getCompound("ExtraAttributes");

            if (!BLACKLIST.stream().anyMatch(id -> extraAttrib.getString("id").equals(id)))
            {
                player.swingArm(Hand.MAIN_HAND);
            }
        }
        return defaultValue;
    }

    public static ActionResultType getBlockedItemResult(ItemStack itemStack, PlayerEntity player, ActionResultType defaultValue)
    {
        if (SkyBlockEventHandler.isSkyBlock && !itemStack.isEmpty() && itemStack.hasTag())
        {
            CompoundNBT extraAttrib = itemStack.getTag().getCompound("ExtraAttributes");

            if (!BLACKLIST.stream().anyMatch(id -> extraAttrib.getString("id").equals(id)))
            {
                player.swingArm(Hand.MAIN_HAND);
                return ActionResultType.SUCCESS;
            }
        }
        return defaultValue;
    }

    private static <K, V> K getKey(Map<K, V> map, V value)
    {
        return map.keySet().stream().filter(key -> value.equals(map.get(key))).findFirst().get();
    }
}