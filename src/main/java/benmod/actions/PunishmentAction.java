package benmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class PunishmentAction extends AbstractGameAction {
    private DamageInfo info;

    public PunishmentAction(AbstractCreature target, DamageInfo info) {
        this.actionType = ActionType.BLOCK;
        this.target = target;
        this.info = info;
    }

    @Override
    public void update() {
        if (this.target != null && this.target.hasPower("BenMod:SinPower")) {
            this.addToTop(new GainEnergyAction(1));
        }
        this.addToTop(new DamageAction(this.target, this.info, AttackEffect.BLUNT_HEAVY));
        this.isDone = true;
    }

}
