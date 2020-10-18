package com.stevekung.skyblockcatia.utils.skyblock;

import java.util.List;

import com.stevekung.skyblockcatia.utils.skyblock.api.ExpProgress;
import com.stevekung.stevekungslib.utils.NumberUtils;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class SBPets
{
    public enum Tier
    {
        COMMON(ExpProgress.PET_COMMON, TextFormatting.WHITE),
        UNCOMMON(ExpProgress.PET_UNCOMMON, TextFormatting.GREEN),
        RARE(ExpProgress.PET_RARE, TextFormatting.BLUE),
        EPIC(ExpProgress.PET_EPIC, TextFormatting.DARK_PURPLE),
        LEGENDARY(ExpProgress.PET_LEGENDARY, TextFormatting.GOLD);

        private final ExpProgress[] progression;
        private final TextFormatting color;

        private Tier(ExpProgress[] progression, TextFormatting color)
        {
            this.progression = progression;
            this.color = color;
        }

        public ExpProgress[] getProgression()
        {
            return this.progression;
        }

        public TextFormatting getTierColor()
        {
            return this.color;
        }
    }

    public enum Type
    {
        BABY_YETI(SBSkills.Type.FISHING, "7895e21a-8f3b-3e30-bea6-06108f64d5dc", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWIxMjY4MTRmYzNmYTg0NmRhZDkzNGMzNDk2MjhhN2ExZGU1YjQxNTAyMWEwM2VmNDIxMWQ2MjUxNGQ1In19fQ=="),
        BAT(SBSkills.Type.MINING, "1911c3bb-c0af-3474-98e2-486478c5b9ea", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzgyZmMzZjcxYjQxNzY5Mzc2YTllOTJmZTNhZGJhYWMzNzcyYjk5OWIyMTljOWQ2YjQ2ODBiYTk5ODNlNTI3In19fQ=="),
        BEE(SBSkills.Type.FARMING, "af894c68-45d0-3ae2-952c-b3cf925199ad", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2U5NDE5ODdlODI1YTI0ZWE3YmFhZmFiOTgxOTM0NGI2YzI0N2M3NWM1NGE2OTE5ODdjZDI5NmJjMTYzYzI2MyJ9fX0="),
        BLACK_CAT(SBSkills.Type.COMBAT, "5992f40a-6406-48a3-867b-232e414232f3", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRiNDVjYmFhMTlmZTNkNjhjODU2Y2QzODQ2YzAzYjVmNTlkZTgxYTQ4MGVlYzkyMWFiNGZhM2NkODEzMTcifX19"),
        BLAZE(SBSkills.Type.COMBAT, "118fe834-28aa-3b0d-afe6-f0c52d01afe8", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjc4ZWYyZTRjZjJjNDFhMmQxNGJmZGU5Y2FmZjEwMjE5ZjViMWJmNWIzNWE0OWViNTFjNjQ2Nzg4MmNiNWYwIn19fQ=="),
        BLUE_WHALE(SBSkills.Type.FISHING, "47c8ba46-82ac-3c09-b511-5502860eb012", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFiNzc5YmJjY2M4NDlmODgyNzNkODQ0ZThjYTJmM2E2N2ExNjk5Y2IyMTZjMGExMWI0NDMyNmNlMmNjMjAifX19"),
        CHICKEN(SBSkills.Type.FARMING, "635fdfb8-3c52-433e-87dc-70a9406c5ff0", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2YzN2Q1MjRjM2VlZDE3MWNlMTQ5ODg3ZWExZGVlNGVkMzk5OTA0NzI3ZDUyMTg2NTY4OGVjZTNiYWM3NWUifX19"),
        DOLPHIN(SBSkills.Type.FISHING, "48f53ffe-a3f0-3280-aac0-11cc0d6121f4", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2VmZTdkODAzYTQ1YWEyYWYxOTkzZGYyNTQ0YTI4ZGY4NDlhNzYyNjYzNzE5YmZlZmM1OGJmMzg5YWI3ZjUifX19"),
        ELEPHANT(SBSkills.Type.FARMING, "9a58e25a-cf47-447d-b13c-3ea36eccfa31", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzA3MWE3NmY2NjlkYjVlZDZkMzJiNDhiYjJkYmE1NWQ1MzE3ZDdmNDUyMjVjYjMyNjdlYzQzNWNmYTUxNCJ9fX0="),
        ENDER_DRAGON(SBSkills.Type.COMBAT, "3f9632a1-0ce2-311a-97e7-b144dfcb74f3", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWVjM2ZmNTYzMjkwYjEzZmYzYmNjMzY4OThhZjdlYWE5ODhiNmNjMThkYzI1NDE0N2Y1ODM3NGFmZTliMjFiOSJ9fX0="),
        ENDERMAN(SBSkills.Type.COMBAT, "fb3c5e13-61e9-4584-99db-9f9ef9fb834d", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmVhYjc1ZWFhNWM5ZjJjNDNhMGQyM2NmZGNlMzVmNGRmNjMyZTk4MTUwMDE4NTAzNzczODVmN2IyZjAzOWNlMSJ9fX0="),
        ENDERMITE(SBSkills.Type.MINING, "3302cdfe-6879-4659-ab0b-587b2cdb98e6", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWExYTA4MzFhYTAzYWZiNDIxMmFkY2JiMjRlNWRmYWE3ZjQ3NmExMTczZmNlMjU5ZWY3NWE4NTg1NSJ9fX0="),
        FLYING_FISH(SBSkills.Type.FISHING, "fd4a969b-c84c-4b59-979d-55eca6ec5f0e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjZDcxZmJiYmJiNjZjN2JhZjc4ODFmNDE1YzY0ZmE4NGY2NTA0OTU4YTU3Y2NkYjg1ODkyNTI2NDdlYSJ9fX0="),
        GHOUL(SBSkills.Type.COMBAT, "3fbb2c84-3693-4dcd-bc49-3b54ca6fa8cc", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc5MzQ1NjViZjUyMmY2ZjQ3MjZjZGZlMTI3MTM3YmUxMWQzN2MzMTBkYjM0ZDhjNzAyNTMzOTJiNWZmNWIifX19"),
        GIRAFFE(SBSkills.Type.FORAGING, "11216f12-2843-31c8-bf8a-b8535e6c6dce", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTc2YjRlMzkwZjJlY2RiOGE3OGRjNjExNzg5Y2EwYWYxZTdlMDkyMjkzMTljM2E3YWE4MjA5YjYzYjkifX19"),
        GOLEM(SBSkills.Type.COMBAT, "623fa763-a8d1-36c6-8dcf-09f100723d04", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODkwOTFkNzllYTBmNTllZjdlZjk0ZDdiYmE2ZTVmMTdmMmY3ZDQ1NzJjNDRmOTBmNzZjNDgxOWE3MTQifX19"),
        GUARDIAN(SBSkills.Type.COMBAT, "26508276-c01a-32a9-9201-7dae1724954e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjIxMDI1NDM0MDQ1YmRhNzAyNWIzZTUxNGIzMTZhNGI3NzBjNmZhYTRiYTlhZGI0YmUzODA5NTI2ZGI3N2Y5ZCJ9fX0="),
        HORSE(SBSkills.Type.COMBAT, "6d310633-c175-4b47-92ab-778287bb7a5e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZmY2QzZWMzYmM4NGJhZmI0MTIzZWE0Nzk0NzFmOWQyZjQyZDhmYjljNWYxMWNmNWY0ZTBkOTMyMjYifX19"),
        HOUND(SBSkills.Type.COMBAT, "802a167c-cbcd-3a1f-becd-5b1a25a4cf15", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjdjOGJlZjZiZWI3N2UyOWFmODYyN2VjZGMzOGQ4NmFhMmZlYTdjY2QxNjNkYzczYzAwZjlmMjU4ZjlhMTQ1NyJ9fX0="),
        JELLYFISH(SBSkills.Type.ALCHEMY, "a7be2bb4-70a1-32e4-a981-8f26c5864371", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzZjA4NmNjYjU2MzIzZjIzOGJhMzQ4OWZmMmExYTM0YzBmZGNlZWFmYzQ4M2FjZmYwZTU0ODhjZmQ2YzJmMSJ9fX0="),
        JERRY(SBSkills.Type.COMBAT, "0a9e8efb-9191-4c81-80f5-e27ca5433156", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODIyZDhlNzUxYzhmMmZkNGM4OTQyYzQ0YmRiMmY1Y2E0ZDhhZThlNTc1ZWQzZWIzNGMxOGE4NmU5M2IifX19"),
        LION(SBSkills.Type.FORAGING, "7e3ed445-3545-3c76-993b-8f292ea576c6", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzhmZjQ3M2JkNTJiNGRiMmMwNmYxYWM4N2ZlMTM2N2JjZTc1NzRmYWMzMzBmZmFjNzk1NjIyOWY4MmVmYmExIn19fQ=="),
        MAGMA_CUBE(SBSkills.Type.COMBAT, "35f02923-7bec-3869-9ef5-b42a4794cac8", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzg5NTdkNTAyM2M5MzdjNGM0MWFhMjQxMmQ0MzQxMGJkYTIzY2Y3OWE5ZjZhYjM2Yjc2ZmVmMmQ3YzQyOSJ9fX0="),
        MONKEY(SBSkills.Type.FORAGING, "e410c089-bb3a-40a3-add6-188d6187ac87", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTNjZjhkYjg0ODA3YzQ3MWQ3YzY5MjIzMDIyNjFhYzFiNWExNzlmOTZkMTE5MTE1NmVjZjNlMWIxZDNjYSJ9fX0="),
        OCELOT(SBSkills.Type.FORAGING, "664dd492-3fcd-443b-9e61-4c7ebd9e4e10", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY1N2NkNWMyOTg5ZmY5NzU3MGZlYzRkZGNkYzY5MjZhNjhhMzM5MzI1MGMxYmUxZjBiMTE0YTFkYjEifX19"),
        PARROT(SBSkills.Type.ALCHEMY, "cbd58638-f78c-3f85-9b08-6b02f0614215", "eyJ0aW1lc3RhbXAiOjE1ODIwODk4NjgyMDksInByb2ZpbGVJZCI6IjQxZDNhYmMyZDc0OTQwMGM5MDkwZDU0MzRkMDM4MzFiIiwicHJvZmlsZU5hbWUiOiJNZWdha2xvb24iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVkZjRiMzQwMWE0ZDA2YWQ2NmFjOGI1YzRkMTg5NjE4YWU2MTdmOWMxNDMwNzFjOGFjMzlhNTYzY2Y0ZTQyMDgifX19"),
        PHOENIX(SBSkills.Type.COMBAT, "4173bc61-9e2f-3c84-8d31-4517e64062ab", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjNhYWY3YjFhNzc4OTQ5Njk2Y2I5OWQ0ZjA0YWQxYWE1MThjZWVlMjU2YzcyZTVlZDY1YmZhNWMyZDg4ZDllIn19fQ=="),
        PIG(SBSkills.Type.FARMING, "e1e1c2e4-1ed2-473d-bde2-3ec718535399", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIxNjY4ZWY3Y2I3OWRkOWMyMmNlM2QxZjNmNGNiNmUyNTU5ODkzYjZkZjRhNDY5NTE0ZTY2N2MxNmFhNCJ9fX0="),
        PIGMAN(SBSkills.Type.COMBAT, "e3410337-d22b-4427-beab-d9ceae561d2c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNkOWNiNjUxM2YyMDcyZTVkNGU0MjZkNzBhNTU1N2JjMzk4NTU0Yzg4MGQ0ZTdiN2VjOGVmNDk0NWViMDJmMiJ9fX0="),
        RABBIT(SBSkills.Type.FARMING, "389b150b-1aed-4bd8-af89-129043e007d1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTE3YmZmYzE5NzJhY2Q3ZjNiNGE4ZjQzYjViNmM3NTM0Njk1YjhmZDYyNjc3ZTAzMDZiMjgzMTU3NGIifX19"),
        ROCK(SBSkills.Type.MINING, "1887aa6a-240a-4927-b868-7d3631f03577", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2IyYjVkNDhlNTc1Nzc1NjNhY2EzMTczNTUxOWNiNjIyMjE5YmMwNThiMWYzNDY0OGI2N2I4ZTcxYmMwZmEifX19"),
        SHEEP(SBSkills.Type.ALCHEMY, "37bacd66-7fe6-39e3-81cf-82911daf648b", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjRlMjJhNDYwNDdkMjcyZTg5YTFjZmExM2U5NzM0YjdlMTI4MjdlMjM1YzIwMTJjMWE5NTk2Mjg3NGRhMCJ9fX0="),
        SILVERFISH(SBSkills.Type.MINING, "79e570d8-f66e-375c-9e70-97224ccd5692", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGE5MWRhYjgzOTFhZjVmZGE1NGFjZDJjMGIxOGZiZDgxOWI4NjVlMWE4ZjFkNjIzODEzZmE3NjFlOTI0NTQwIn19fQ=="),
        SKELETON(SBSkills.Type.COMBAT, "baee4f79-051d-4b7e-9323-58494878ef5a", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNhNDQ1NzQ5MjUxYmRkODk4ZmI4M2Y2Njc4NDRlMzhhMWRmZjc5YTE1MjlmNzlhNDI0NDdhMDU5OTMxMGVhNCJ9fX0="),
        SKELETON_HORSE(SBSkills.Type.COMBAT, "8dfd0bbb-7ce2-444e-ad9a-0eb9518eaffd", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDdlZmZjZTM1MTMyYzg2ZmY3MmJjYWU3N2RmYmIxZDIyNTg3ZTk0ZGYzY2JjMjU3MGVkMTdjZjg5NzNhIn19fQ=="),
        SNOWMAN(SBSkills.Type.COMBAT, "b2b19dcd-dc67-31df-a790-e6cf07ae12ac", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTExMzY2MTZkOGM0YTg3YTU0Y2U3OGE5N2I1NTE2MTBjMmIyYzhmNmQ0MTBiYzM4Yjg1OGY5NzRiMTEzYjIwOCJ9fX0="),
        SPIDER(SBSkills.Type.COMBAT, "7c63f3cf-a963-311a-aeca-3a075b417806", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q1NDE1NDFkYWFmZjUwODk2Y2QyNThiZGJkZDRjZjgwYzNiYTgxNjczNTcyNjA3OGJmZTM5MzkyN2U1N2YxIn19fQ=="),
        SQUID(SBSkills.Type.FISHING, "7b5da593-80d3-39f4-8220-2cef27c5b9d9", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMDE0MzNiZTI0MjM2NmFmMTI2ZGE0MzRiODczNWRmMWViNWIzY2IyY2VkZTM5MTQ1OTc0ZTljNDgzNjA3YmFjIn19fQ=="),
        TARANTULA(SBSkills.Type.COMBAT, "3e5474d4-4365-3ea7-b4bc-b4edc54da341", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODMwMDk4NmVkMGEwNGVhNzk5MDRmNmFlNTNmNDllZDNhMGZmNWIxZGY2MmJiYTYyMmVjYmQzNzc3ZjE1NmRmOCJ9fX0="),
        TIGER(SBSkills.Type.COMBAT, "33a69ead-44ac-3791-9425-52109aacdaa6", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmM0MjYzODc0NDkyMmI1ZmNmNjJjZDliZjI3ZWVhYjkxYjJlNzJkNmM3MGU4NmNjNWFhMzg4Mzk5M2U5ZDg0In19fQ=="),
        TURTLE(SBSkills.Type.COMBAT, "f10d652b-906b-3065-adf5-9817983201ca", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjEyYjU4Yzg0MWIzOTQ4NjNkYmNjNTRkZTFjMmFkMjY0OGFmOGYwM2U2NDg5ODhjMWY5Y2VmMGJjMjBlZTIzYyJ9fX0="),
        WITHER_SKELETON(SBSkills.Type.MINING, "d928ce5e-e75e-3cdc-aaf1-0c93d49b5c31", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjVlYzk2NDY0NWE4ZWZhYzc2YmUyZjE2MGQ3Yzk5NTYzNjJmMzJiNjUxNzM5MGM1OWMzMDg1MDM0ZjA1MGNmZiJ9fX0="),
        WOLF(SBSkills.Type.COMBAT, "85b4606a-2fc7-4451-aa82-3b1afaeee9cd", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGMzZGQ5ODRiYjY1OTg0OWJkNTI5OTQwNDY5NjRjMjI3MjVmNzE3ZTk4NmIxMmQ1NDhmZDE2OTM2N2Q0OTQifX19"),
        ZOMBIE(SBSkills.Type.COMBAT, "1c760ea5-2e91-3c2e-b52a-e17d11733658", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZmYzg1NGJiODRjZjRiNzY5NzI5Nzk3M2UwMmI3OWJjMTA2OTg0NjBiNTFhNjM5YzYwZTVlNDE3NzM0ZTExIn19fQ==");

        private final SBSkills.Type type;
        private final String uuid;
        private final String value;

        private Type(SBSkills.Type type, String uuid, String value)
        {
            this.type = type;
            this.uuid = uuid;
            this.value = value;
        }

        public SBSkills.Type getType()
        {
            return this.type;
        }

        public ItemStack getPetItem()
        {
            return SBRenderUtils.getSkullItemStack(this.uuid, this.value);
        }
    }

    public enum HeldItem
    {
        PET_ITEM_ALL_SKILLS_BOOST_COMMON("PET_ITEM_ALL_SKILLS_BOOST", TextFormatting.WHITE),
        PET_ITEM_BIG_TEETH_COMMON("PET_ITEM_BIG_TEETH", TextFormatting.WHITE),
        PET_ITEM_IRON_CLAWS_COMMON("PET_ITEM_IRON_CLAWS", TextFormatting.WHITE),
        PET_ITEM_SHARPENED_CLAWS_UNCOMMON("PET_ITEM_SHARPENED_CLAWS", TextFormatting.GREEN),
        PET_ITEM_HARDENED_SCALES_UNCOMMON("PET_ITEM_HARDENED_SCALES", TextFormatting.GREEN),
        PET_ITEM_BUBBLEGUM(TextFormatting.BLUE),
        PET_ITEM_LUCKY_CLOVER(TextFormatting.DARK_PURPLE),
        PET_ITEM_TEXTBOOK(TextFormatting.GOLD),
        PET_ITEM_SADDLE(TextFormatting.GREEN),
        PET_ITEM_EXP_SHARE(TextFormatting.DARK_PURPLE),
        PET_ITEM_TIER_BOOST(TextFormatting.GOLD),
        PET_ITEM_COMBAT_SKILL_BOOST_COMMON("PET_ITEM_COMBAT_SKILL_BOOST", TextFormatting.WHITE),
        PET_ITEM_COMBAT_SKILL_BOOST_UNCOMMON("PET_ITEM_COMBAT_SKILL_BOOST", TextFormatting.GREEN),
        PET_ITEM_COMBAT_SKILL_BOOST_RARE("PET_ITEM_COMBAT_SKILL_BOOST", TextFormatting.BLUE),
        PET_ITEM_COMBAT_SKILL_BOOST_EPIC("PET_ITEM_COMBAT_SKILL_BOOST", TextFormatting.DARK_PURPLE),
        PET_ITEM_FISHING_SKILL_BOOST_COMMON("PET_ITEM_FISHING_SKILL_BOOST", TextFormatting.WHITE),
        PET_ITEM_FISHING_SKILL_BOOST_UNCOMMON("PET_ITEM_FISHING_SKILL_BOOST", TextFormatting.GREEN),
        PET_ITEM_FISHING_SKILL_BOOST_RARE("PET_ITEM_FISHING_SKILL_BOOST", TextFormatting.BLUE),
        PET_ITEM_FISHING_SKILL_BOOST_EPIC("PET_ITEM_FISHING_SKILL_BOOST", TextFormatting.DARK_PURPLE),
        PET_ITEM_FORAGING_SKILL_BOOST_COMMON("PET_ITEM_FORAGING_SKILL_BOOST", TextFormatting.WHITE),
        PET_ITEM_FORAGING_SKILL_BOOST_UNCOMMON("PET_ITEM_FORAGING_SKILL_BOOST", TextFormatting.GREEN),
        PET_ITEM_FORAGING_SKILL_BOOST_RARE("PET_ITEM_FORAGING_SKILL_BOOST", TextFormatting.BLUE),
        PET_ITEM_FORAGING_SKILL_BOOST_EPIC("PET_ITEM_FORAGING_SKILL_BOOST", TextFormatting.DARK_PURPLE),
        PET_ITEM_MINING_SKILL_BOOST_COMMON("PET_ITEM_MINING_SKILL_BOOST", TextFormatting.WHITE),
        PET_ITEM_MINING_SKILL_BOOST_UNCOMMON("PET_ITEM_MINING_SKILL_BOOST", TextFormatting.GREEN),
        PET_ITEM_MINING_SKILL_BOOST_RARE("PET_ITEM_MINING_SKILL_BOOST", TextFormatting.BLUE),
        PET_ITEM_MINING_SKILL_BOOST_EPIC("PET_ITEM_MINING_SKILL_BOOST", TextFormatting.DARK_PURPLE),
        PET_ITEM_FARMING_SKILL_BOOST_COMMON("PET_ITEM_FARMING_SKILL_BOOST", TextFormatting.WHITE),
        PET_ITEM_FARMING_SKILL_BOOST_UNCOMMON("PET_ITEM_FARMING_SKILL_BOOST", TextFormatting.GREEN),
        PET_ITEM_FARMING_SKILL_BOOST_RARE("PET_ITEM_FARMING_SKILL_BOOST", TextFormatting.BLUE),
        PET_ITEM_FARMING_SKILL_BOOST_EPIC("PET_ITEM_FARMING_SKILL_BOOST", TextFormatting.DARK_PURPLE),
        REINFORCED_SCALES(TextFormatting.BLUE),
        GOLD_CLAWS(TextFormatting.GREEN),
        ALL_SKILLS_SUPER_BOOST(TextFormatting.WHITE),
        BIGGER_TEETH(TextFormatting.GREEN),
        SERRATED_CLAWS(TextFormatting.BLUE),
        WASHED_UP_SOUVENIR("WASHED-UP_SOUVENIR", TextFormatting.GOLD),
        ANTIQUE_REMEDIES(TextFormatting.DARK_PURPLE),
        CROCHET_TIGER_PLUSHIE(TextFormatting.DARK_PURPLE),
        DWARF_TURTLE_SHELMET(TextFormatting.BLUE);

        private final String altName;
        private final TextFormatting color;

        private HeldItem(TextFormatting color)
        {
            this(null, color);
        }

        private HeldItem(String altName, TextFormatting color)
        {
            this.altName = altName;
            this.color = color;
        }

        public String getAltName()
        {
            return this.altName;
        }

        public TextFormatting getColor()
        {
            return this.color;
        }
    }

    public static class Info
    {
        private final int currentPetLevel;
        private final int nextPetLevel;
        private final double currentPetXp;
        private final int xpRequired;
        private final double petXp;
        private final int totalPetTypeXp;

        public Info(int currentPetLevel, int nextPetLevel, double currentPetXp, int xpRequired, double petXp, int totalPetTypeXp)
        {
            this.currentPetLevel = currentPetLevel;
            this.nextPetLevel = nextPetLevel;
            this.currentPetXp = currentPetXp;
            this.xpRequired = xpRequired;
            this.petXp = petXp;
            this.totalPetTypeXp = totalPetTypeXp;
        }

        public int getCurrentPetLevel()
        {
            return this.currentPetLevel;
        }

        public int getNextPetLevel()
        {
            return this.nextPetLevel;
        }

        public double getCurrentPetXp()
        {
            return this.currentPetXp;
        }

        public int getXpRequired()
        {
            return this.xpRequired;
        }

        public double getPetXp()
        {
            return this.petXp;
        }

        public int getTotalPetTypeXp()
        {
            return this.totalPetTypeXp;
        }

        public String getPercent()
        {
            if (this.xpRequired > 0)
            {
                double percent = this.currentPetXp * 100.0D / this.xpRequired;
                return NumberUtils.NUMBER_FORMAT_WITH_DECIMAL.format(percent) + "%";
            }
            else
            {
                return TextFormatting.AQUA + "MAX LEVEL";
            }
        }
    }

    public static class Data
    {
        private final SBPets.Tier tier;
        private final int currentLevel;
        private final double currentXp;
        private final boolean isActive;
        private final List<ItemStack> itemStack;

        public Data(SBPets.Tier tier, int currentLevel, double currentXp, boolean isActive, List<ItemStack> itemStack)
        {
            this.tier = tier;
            this.currentLevel = currentLevel;
            this.currentXp = currentXp;
            this.isActive = isActive;
            this.itemStack = itemStack;
        }

        public SBPets.Tier getTier()
        {
            return this.tier;
        }

        public List<ItemStack> getItemStack()
        {
            return this.itemStack;
        }

        public int getCurrentLevel()
        {
            return this.currentLevel;
        }

        public double getCurrentXp()
        {
            return this.currentXp;
        }

        public boolean isActive()
        {
            return this.isActive;
        }
    }
}