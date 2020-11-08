package com.stevekung.skyblockcatia.utils;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Comparator;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.stevekung.skyblockcatia.gui.api.ExpProgress;
import com.stevekung.skyblockcatia.gui.api.GuiSkyBlockData.SkillType;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class SkyBlockPets
{
    private static final Gson GSON = new Gson();
    public static PetSkin[] PET_SKIN;

    public static void getPetSkins() throws Exception
    {
        BufferedReader in = DataGetter.get("api/pet_skins.json");
        PET_SKIN = GSON.fromJson(in, PetSkin[].class);
    }

    public static class PetSkin
    {
        private final String skin;
        @SerializedName("displayName")
        private final String name;
        private final String uuid;
        private final String texture;

        public PetSkin(String skin, String name, String uuid, String texture)
        {
            this.skin = skin;
            this.name = name;
            this.uuid = uuid;
            this.texture = texture;
        }

        public String getSkin()
        {
            return this.skin;
        }

        public String getName()
        {
            return this.name;
        }

        public String getUUID()
        {
            return this.uuid;
        }

        public String getTexture()
        {
            return this.texture;
        }
    }

    public enum Tier
    {
        COMMON(ExpProgress.PET_COMMON, EnumChatFormatting.WHITE),
        UNCOMMON(ExpProgress.PET_UNCOMMON, EnumChatFormatting.GREEN),
        RARE(ExpProgress.PET_RARE, EnumChatFormatting.BLUE),
        EPIC(ExpProgress.PET_EPIC, EnumChatFormatting.DARK_PURPLE),
        LEGENDARY(ExpProgress.PET_LEGENDARY, EnumChatFormatting.GOLD),
        MYTHIC(ExpProgress.PET_LEGENDARY, EnumChatFormatting.LIGHT_PURPLE);

        private static final Tier[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(Tier::ordinal)).toArray(size -> new Tier[size]);
        private final ExpProgress[] progression;
        private final EnumChatFormatting color;

        static
        {
            for (Tier rarity : values())
            {
                VALUES[rarity.ordinal()] = rarity;
            }
        }

        private Tier(ExpProgress[] progression, EnumChatFormatting color)
        {
            this.progression = progression;
            this.color = color;
        }

        public ExpProgress[] getProgression()
        {
            return this.progression;
        }

        public EnumChatFormatting getTierColor()
        {
            return this.color;
        }

        public Tier getNextRarity()
        {
            return VALUES[(this.ordinal() + 1) % VALUES.length];
        }
    }

    public enum Type
    {
        BABY_YETI(SkillType.FISHING, "7895e21a-8f3b-3e30-bea6-06108f64d5dc", "ab126814fc3fa846dad934c349628a7a1de5b415021a03ef4211d62514d5"),
        BAT(SkillType.MINING, "1911c3bb-c0af-3474-98e2-486478c5b9ea", "382fc3f71b41769376a9e92fe3adbaac3772b999b219c9d6b4680ba9983e527"),
        BEE(SkillType.FARMING, "af894c68-45d0-3ae2-952c-b3cf925199ad", "7e941987e825a24ea7baafab9819344b6c247c75c54a691987cd296bc163c263"),
        BLACK_CAT(SkillType.COMBAT, "5992f40a-6406-48a3-867b-232e414232f3", "e4b45cbaa19fe3d68c856cd3846c03b5f59de81a480eec921ab4fa3cd81317"),
        BLAZE(SkillType.COMBAT, "118fe834-28aa-3b0d-afe6-f0c52d01afe8", "b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0"),
        BLUE_WHALE(SkillType.FISHING, "47c8ba46-82ac-3c09-b511-5502860eb012", "dab779bbccc849f88273d844e8ca2f3a67a1699cb216c0a11b44326ce2cc20"),
        CHICKEN(SkillType.FARMING, "635fdfb8-3c52-433e-87dc-70a9406c5ff0", "7f37d524c3eed171ce149887ea1dee4ed399904727d521865688ece3bac75e"),
        DOLPHIN(SkillType.FISHING, "48f53ffe-a3f0-3280-aac0-11cc0d6121f4", "cefe7d803a45aa2af1993df2544a28df849a762663719bfefc58bf389ab7f5"),
        ELEPHANT(SkillType.FARMING, "9a58e25a-cf47-447d-b13c-3ea36eccfa31", "7071a76f669db5ed6d32b48bb2dba55d5317d7f45225cb3267ec435cfa514"),
        ENDER_DRAGON(SkillType.COMBAT, "3f9632a1-0ce2-311a-97e7-b144dfcb74f3", "aec3ff563290b13ff3bcc36898af7eaa988b6cc18dc254147f58374afe9b21b9"),
        ENDERMAN(SkillType.COMBAT, "fb3c5e13-61e9-4584-99db-9f9ef9fb834d", "6eab75eaa5c9f2c43a0d23cfdce35f4df632e9815001850377385f7b2f039ce1"),
        ENDERMITE(SkillType.MINING, "3302cdfe-6879-4659-ab0b-587b2cdb98e6", "5a1a0831aa03afb4212adcbb24e5dfaa7f476a1173fce259ef75a85855"),
        FLYING_FISH(SkillType.FISHING, "fd4a969b-c84c-4b59-979d-55eca6ec5f0e", "40cd71fbbbbb66c7baf7881f415c64fa84f6504958a57ccdb8589252647ea"),
        GHOUL(SkillType.COMBAT, "3fbb2c84-3693-4dcd-bc49-3b54ca6fa8cc", "87934565bf522f6f4726cdfe127137be11d37c310db34d8c70253392b5ff5b"),
        GIRAFFE(SkillType.FORAGING, "11216f12-2843-31c8-bf8a-b8535e6c6dce", "176b4e390f2ecdb8a78dc611789ca0af1e7e09229319c3a7aa8209b63b9"),
        GOLEM(SkillType.COMBAT, "623fa763-a8d1-36c6-8dcf-09f100723d04", "89091d79ea0f59ef7ef94d7bba6e5f17f2f7d4572c44f90f76c4819a714"),
        GRIFFIN(SkillType.COMBAT, "11e506b9-cb3d-43e6-89d2-9e1575944498", "4c27e3cb52a64968e60c861ef1ab84e0a0cb5f07be103ac78da67761731f00c8"),
        GUARDIAN(SkillType.COMBAT, "26508276-c01a-32a9-9201-7dae1724954e", "221025434045bda7025b3e514b316a4b770c6faa4ba9adb4be3809526db77f9d"),
        HORSE(SkillType.COMBAT, "6d310633-c175-4b47-92ab-778287bb7a5e", "36fcd3ec3bc84bafb4123ea479471f9d2f42d8fb9c5f11cf5f4e0d93226"),
        HOUND(SkillType.COMBAT, "802a167c-cbcd-3a1f-becd-5b1a25a4cf15", "b7c8bef6beb77e29af8627ecdc38d86aa2fea7ccd163dc73c00f9f258f9a1457"),
        JELLYFISH(SkillType.ALCHEMY, "a7be2bb4-70a1-32e4-a981-8f26c5864371", "913f086ccb56323f238ba3489ff2a1a34c0fdceeafc483acff0e5488cfd6c2f1"),
        JERRY(SkillType.COMBAT, "0a9e8efb-9191-4c81-80f5-e27ca5433156", "822d8e751c8f2fd4c8942c44bdb2f5ca4d8ae8e575ed3eb34c18a86e93b"),
        LION(SkillType.FORAGING, "7e3ed445-3545-3c76-993b-8f292ea576c6", "38ff473bd52b4db2c06f1ac87fe1367bce7574fac330ffac7956229f82efba1"),
        MAGMA_CUBE(SkillType.COMBAT, "35f02923-7bec-3869-9ef5-b42a4794cac8", "38957d5023c937c4c41aa2412d43410bda23cf79a9f6ab36b76fef2d7c429"),
        MEGALODON(SkillType.FISHING, "82fc79b9-fded-3c05-b8dc-00e562803862", "a94ae433b301c7fb7c68cba625b0bd36b0b14190f20e34a7c8ee0d9de06d53b9"),
        MONKEY(SkillType.FORAGING, "e410c089-bb3a-40a3-add6-188d6187ac87", "13cf8db84807c471d7c6922302261ac1b5a179f96d1191156ecf3e1b1d3ca"),
        OCELOT(SkillType.FORAGING, "664dd492-3fcd-443b-9e61-4c7ebd9e4e10", "5657cd5c2989ff97570fec4ddcdc6926a68a3393250c1be1f0b114a1db1"),
        PARROT(SkillType.ALCHEMY, "db4d678a-731a-49cc-8dae-2cee4a5b80c9", "5df4b3401a4d06ad66ac8b5c4d189618ae617f9c143071c8ac39a563cf4e4208"),
        PHOENIX(SkillType.COMBAT, "4173bc61-9e2f-3c84-8d31-4517e64062ab", "23aaf7b1a778949696cb99d4f04ad1aa518ceee256c72e5ed65bfa5c2d88d9e"),
        PIG(SkillType.FARMING, "e1e1c2e4-1ed2-473d-bde2-3ec718535399", "621668ef7cb79dd9c22ce3d1f3f4cb6e2559893b6df4a469514e667c16aa4"),
        PIGMAN(SkillType.COMBAT, "e3410337-d22b-4427-beab-d9ceae561d2c", "63d9cb6513f2072e5d4e426d70a5557bc398554c880d4e7b7ec8ef4945eb02f2"),
        RABBIT(SkillType.FARMING, "389b150b-1aed-4bd8-af89-129043e007d1", "117bffc1972acd7f3b4a8f43b5b6c7534695b8fd62677e0306b2831574b"),
        ROCK(SkillType.MINING, "1887aa6a-240a-4927-b868-7d3631f03577", "cb2b5d48e57577563aca31735519cb622219bc058b1f34648b67b8e71bc0fa"),
        SHEEP(SkillType.ALCHEMY, "37bacd66-7fe6-39e3-81cf-82911daf648b", "64e22a46047d272e89a1cfa13e9734b7e12827e235c2012c1a95962874da0"),
        SILVERFISH(SkillType.MINING, "79e570d8-f66e-375c-9e70-97224ccd5692", "da91dab8391af5fda54acd2c0b18fbd819b865e1a8f1d623813fa761e924540"),
        SKELETON(SkillType.COMBAT, "baee4f79-051d-4b7e-9323-58494878ef5a", "fca445749251bdd898fb83f667844e38a1dff79a1529f79a42447a0599310ea4"),
        SKELETON_HORSE(SkillType.COMBAT, "8dfd0bbb-7ce2-444e-ad9a-0eb9518eaffd", "47effce35132c86ff72bcae77dfbb1d22587e94df3cbc2570ed17cf8973a"),
        SNOWMAN(SkillType.COMBAT, "b2b19dcd-dc67-31df-a790-e6cf07ae12ac", "11136616d8c4a87a54ce78a97b551610c2b2c8f6d410bc38b858f974b113b208"),
        SPIDER(SkillType.COMBAT, "7c63f3cf-a963-311a-aeca-3a075b417806", "cd541541daaff50896cd258bdbdd4cf80c3ba816735726078bfe393927e57f1"),
        SPIRIT(SkillType.COMBAT, "38699d98-8bfa-4492-acfb-7191c7c3c3bb", "8d9ccc670677d0cebaad4058d6aaf9acfab09abea5d86379a059902f2fe22655"),
        SQUID(SkillType.FISHING, "7b5da593-80d3-39f4-8220-2cef27c5b9d9", "01433be242366af126da434b8735df1eb5b3cb2cede39145974e9c483607bac"),
        TARANTULA(SkillType.COMBAT, "3e5474d4-4365-3ea7-b4bc-b4edc54da341", "8300986ed0a04ea79904f6ae53f49ed3a0ff5b1df62bba622ecbd3777f156df8"),
        TIGER(SkillType.COMBAT, "33a69ead-44ac-3791-9425-52109aacdaa6", "fc42638744922b5fcf62cd9bf27eeab91b2e72d6c70e86cc5aa3883993e9d84"),
        TURTLE(SkillType.COMBAT, "f10d652b-906b-3065-adf5-9817983201ca", "212b58c841b394863dbcc54de1c2ad2648af8f03e648988c1f9cef0bc20ee23c"),
        WITHER_SKELETON(SkillType.MINING, "d928ce5e-e75e-3cdc-aaf1-0c93d49b5c31", "f5ec964645a8efac76be2f160d7c9956362f32b6517390c59c3085034f050cff"),
        WOLF(SkillType.COMBAT, "85b4606a-2fc7-4451-aa82-3b1afaeee9cd", "dc3dd984bb659849bd52994046964c22725f717e986b12d548fd169367d494"),
        ZOMBIE(SkillType.COMBAT, "1c760ea5-2e91-3c2e-b52a-e17d11733658", "56fc854bb84cf4b7697297973e02b79bc10698460b51a639c60e5e417734e11");

        private final SkillType type;
        private final String uuid;
        private final String value;

        private Type(SkillType type, String uuid, String value)
        {
            this.type = type;
            this.uuid = uuid;
            this.value = value;
        }

        public SkillType getSkillType()
        {
            return this.type;
        }

        public ItemStack getPetItem()
        {
            return RenderUtils.getSkullItemStack(this.uuid, this.value);
        }
    }

    public enum HeldItem
    {
        PET_ITEM_ALL_SKILLS_BOOST_COMMON("PET_ITEM_ALL_SKILLS_BOOST", EnumChatFormatting.WHITE),
        PET_ITEM_BIG_TEETH_COMMON("PET_ITEM_BIG_TEETH", EnumChatFormatting.WHITE),
        PET_ITEM_IRON_CLAWS_COMMON("PET_ITEM_IRON_CLAWS", EnumChatFormatting.WHITE),
        PET_ITEM_SHARPENED_CLAWS_UNCOMMON("PET_ITEM_SHARPENED_CLAWS", EnumChatFormatting.GREEN),
        PET_ITEM_HARDENED_SCALES_UNCOMMON("PET_ITEM_HARDENED_SCALES", EnumChatFormatting.GREEN),
        PET_ITEM_BUBBLEGUM(EnumChatFormatting.BLUE),
        PET_ITEM_LUCKY_CLOVER(EnumChatFormatting.DARK_PURPLE),
        PET_ITEM_TEXTBOOK(EnumChatFormatting.GOLD),
        PET_ITEM_SADDLE(EnumChatFormatting.GREEN),
        PET_ITEM_EXP_SHARE(EnumChatFormatting.DARK_PURPLE),
        PET_ITEM_TIER_BOOST(EnumChatFormatting.GOLD),
        PET_ITEM_COMBAT_SKILL_BOOST_COMMON("PET_ITEM_COMBAT_SKILL_BOOST", EnumChatFormatting.WHITE),
        PET_ITEM_COMBAT_SKILL_BOOST_UNCOMMON("PET_ITEM_COMBAT_SKILL_BOOST", EnumChatFormatting.GREEN),
        PET_ITEM_COMBAT_SKILL_BOOST_RARE("PET_ITEM_COMBAT_SKILL_BOOST", EnumChatFormatting.BLUE),
        PET_ITEM_COMBAT_SKILL_BOOST_EPIC("PET_ITEM_COMBAT_SKILL_BOOST", EnumChatFormatting.DARK_PURPLE),
        PET_ITEM_FISHING_SKILL_BOOST_COMMON("PET_ITEM_FISHING_SKILL_BOOST", EnumChatFormatting.WHITE),
        PET_ITEM_FISHING_SKILL_BOOST_UNCOMMON("PET_ITEM_FISHING_SKILL_BOOST", EnumChatFormatting.GREEN),
        PET_ITEM_FISHING_SKILL_BOOST_RARE("PET_ITEM_FISHING_SKILL_BOOST", EnumChatFormatting.BLUE),
        PET_ITEM_FISHING_SKILL_BOOST_EPIC("PET_ITEM_FISHING_SKILL_BOOST", EnumChatFormatting.DARK_PURPLE),
        PET_ITEM_FORAGING_SKILL_BOOST_COMMON("PET_ITEM_FORAGING_SKILL_BOOST", EnumChatFormatting.WHITE),
        PET_ITEM_FORAGING_SKILL_BOOST_UNCOMMON("PET_ITEM_FORAGING_SKILL_BOOST", EnumChatFormatting.GREEN),
        PET_ITEM_FORAGING_SKILL_BOOST_RARE("PET_ITEM_FORAGING_SKILL_BOOST", EnumChatFormatting.BLUE),
        PET_ITEM_FORAGING_SKILL_BOOST_EPIC("PET_ITEM_FORAGING_SKILL_BOOST", EnumChatFormatting.DARK_PURPLE),
        PET_ITEM_MINING_SKILL_BOOST_COMMON("PET_ITEM_MINING_SKILL_BOOST", EnumChatFormatting.WHITE),
        PET_ITEM_MINING_SKILL_BOOST_UNCOMMON("PET_ITEM_MINING_SKILL_BOOST", EnumChatFormatting.GREEN),
        PET_ITEM_MINING_SKILL_BOOST_RARE("PET_ITEM_MINING_SKILL_BOOST", EnumChatFormatting.BLUE),
        PET_ITEM_MINING_SKILL_BOOST_EPIC("PET_ITEM_MINING_SKILL_BOOST", EnumChatFormatting.DARK_PURPLE),
        PET_ITEM_FARMING_SKILL_BOOST_COMMON("PET_ITEM_FARMING_SKILL_BOOST", EnumChatFormatting.WHITE),
        PET_ITEM_FARMING_SKILL_BOOST_UNCOMMON("PET_ITEM_FARMING_SKILL_BOOST", EnumChatFormatting.GREEN),
        PET_ITEM_FARMING_SKILL_BOOST_RARE("PET_ITEM_FARMING_SKILL_BOOST", EnumChatFormatting.BLUE),
        PET_ITEM_FARMING_SKILL_BOOST_EPIC("PET_ITEM_FARMING_SKILL_BOOST", EnumChatFormatting.DARK_PURPLE),
        REINFORCED_SCALES(EnumChatFormatting.BLUE),
        GOLD_CLAWS(EnumChatFormatting.GREEN),
        ALL_SKILLS_SUPER_BOOST(EnumChatFormatting.WHITE),
        BIGGER_TEETH(EnumChatFormatting.GREEN),
        SERRATED_CLAWS(EnumChatFormatting.BLUE),
        WASHED_UP_SOUVENIR("WASHED-UP_SOUVENIR", EnumChatFormatting.GOLD),
        ANTIQUE_REMEDIES(EnumChatFormatting.DARK_PURPLE),
        CROCHET_TIGER_PLUSHIE(EnumChatFormatting.DARK_PURPLE),
        DWARF_TURTLE_SHELMET(EnumChatFormatting.BLUE),
        SPOOKY_CUPCAKE(EnumChatFormatting.GREEN),
        PET_ITEM_VAMPIRE_FANG(EnumChatFormatting.GOLD);

        private final String altName;
        private final EnumChatFormatting color;

        private HeldItem(EnumChatFormatting color)
        {
            this(null, color);
        }

        private HeldItem(String altName, EnumChatFormatting color)
        {
            this.altName = altName;
            this.color = color;
        }

        public String getAltName()
        {
            return this.altName;
        }

        public EnumChatFormatting getColor()
        {
            return this.color;
        }
    }
}