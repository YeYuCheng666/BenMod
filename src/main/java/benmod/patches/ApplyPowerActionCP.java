package benmod.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

@SpirePatch(clz = com.megacrit.cardcrawl.actions.common.ApplyPowerAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCreature.class, AbstractCreature.class, AbstractPower.class, int.class, boolean.class, AbstractGameAction.AttackEffect.class})
public class ApplyPowerActionCP
{
    @SpirePostfixPatch
    public static void Postfix(ApplyPowerAction __instance, AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect)
    {
        AbstractPower p = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
        if (AbstractDungeon.player.hasRelic("BenMod:DemonSlayer") && source != null && source.isPlayer && target != source && powerToApply.ID.equals("BenMod:SinPower")) {
            AbstractDungeon.player.getRelic("BenMod:DemonSlayer").flash();
            ++p.amount;
            ++__instance.amount;
        }
        if (AbstractDungeon.player.hasPower("BenMod:BecomeBuddhaPower") && source != null && source.isPlayer && target != source && powerToApply.ID.equals("BenMod:ForgivenessPower")) {
            AbstractDungeon.player.getPower("BenMod:BecomeBuddhaPower").flash();
            ++p.amount;
            ++__instance.amount;
        }
    }
}
