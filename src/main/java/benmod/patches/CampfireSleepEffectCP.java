package benmod.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.campfire.CampfireSleepEffect;

@SpirePatch(clz = com.megacrit.cardcrawl.vfx.campfire.CampfireSleepEffect.class, method = SpirePatch.CONSTRUCTOR)
public class CampfireSleepEffectCP
{
    @SpirePostfixPatch
    public static void Postfix(CampfireSleepEffect __instance)
    {
        int h = ReflectionHacks.getPrivate(__instance, CampfireSleepEffect.class, "healAmount");
        if (AbstractDungeon.player.hasRelic("BenMod:GoldenFleece") && AbstractDungeon.player.currentHealth >= (float)AbstractDungeon.player.maxHealth / 2.0F) {
            h += (int) (AbstractDungeon.player.maxHealth * 1.0F);
        }
        ReflectionHacks.setPrivate(__instance, CampfireSleepEffect.class, "healAmount", h);
    }
}
