package com.stevekung.skyblockcatia.utils.skyblock;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.stevekung.skyblockcatia.utils.DataUtils;
import com.stevekung.stevekungslib.utils.NumberUtils;
import com.stevekung.stevekungslib.utils.TextComponentUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringUtil;

public class SBStats
{
    public static SBStats STATS;

    private final List<String> blacklist;
    @SerializedName("current_locations")
    private final Map<String, String> currentLocations;
    @SerializedName("sea_creatures")
    private final List<String> seaCreatures;
    private final Map<String, String> renamed;

    public SBStats(List<String> blacklist, Map<String, String> currentLocations, List<String> seaCreatures, Map<String, String> renamed)
    {
        this.blacklist = blacklist;
        this.currentLocations = currentLocations;
        this.seaCreatures = seaCreatures;
        this.renamed = renamed;
    }

    public static void getStats() throws IOException
    {
        STATS = TextComponentUtils.GSON.fromJson(DataUtils.getData("stats.json"), SBStats.class);
    }

    public List<String> getBlacklist()
    {
        return this.blacklist;
    }

    public Map<String, String> getCurrentLocations()
    {
        return this.currentLocations;
    }

    public List<String> getSeaCreatures()
    {
        return this.seaCreatures;
    }

    public Map<String, String> getRenamed()
    {
        return this.renamed;
    }

    public static class Display
    {
        private Component component;
        private String name;
        private final double value;
        private String valueString;

        public Display(Component component, double value)
        {
            this.component = component;
            this.value = value;
        }

        public Display(String name, double value)
        {
            this.name = name;
            this.value = value;
        }

        public Display(String name, String valueString)
        {
            this.name = name;
            this.value = 0;
            this.valueString = valueString;
        }

        public Component getName()
        {
            if (this.component != null)
            {
                return this.component;
            }
            return TextComponentUtils.component(StringUtil.isNullOrEmpty(this.name) ? "" : this.name);
        }

        public double getValue()
        {
            return this.value;
        }

        public String getValueByString()
        {
            if (this.name == null || this.name.startsWith(ChatFormatting.YELLOW.toString()))
            {
                return "";
            }
            else if (!StringUtil.isNullOrEmpty(this.valueString))
            {
                return this.valueString;
            }
            return NumberUtils.NUMBER_FORMAT_WITH_DECIMAL.format(this.value);
        }
    }
}