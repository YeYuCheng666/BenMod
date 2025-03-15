package benmod.actions;

import benmod.powers.ForgivenessPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DoubleForgivenessAction extends AbstractGameAction {

    public DoubleForgivenessAction(AbstractCreature target, AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.actionType = ActionType.DEBUFF;
        this.attackEffect = AttackEffect.FIRE;
    }

    @Override
    public void update() {
        if (this.target != null && this.target.hasPower("BenMod:ForgivenessPower")) {
            this.addToTop(new ApplyPowerAction(this.target, this.source, new ForgivenessPower(this.target, this.source, this.target.getPower("BenMod:ForgivenessPower").amount), this.target.getPower("BenMod:ForgivenessPower").amount));
        }
        this.isDone = true;
    }

}
