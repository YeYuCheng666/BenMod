package benmod.actions;

import benmod.powers.SpectralPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AbsorbAction extends AbstractGameAction {
    private DamageInfo info;

    public AbsorbAction(AbstractCreature target, DamageInfo info) {
        this.actionType = ActionType.BLOCK;
        this.target = target;
        this.info = info;
    }

    @Override
    public void update() {
        if (this.target != null && this.target.hasPower("BenMod:SinPower")) {
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SpectralPower(AbstractDungeon.player, 2), 2));
        }
        this.addToTop(new DamageAction(this.target, this.info, AttackEffect.BLUNT_HEAVY));
        this.isDone = true;
    }

}
