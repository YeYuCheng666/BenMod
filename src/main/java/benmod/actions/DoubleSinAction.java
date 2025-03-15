package benmod.actions;

import benmod.powers.SinPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DoubleSinAction extends AbstractGameAction {

    public DoubleSinAction(AbstractCreature target, AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.actionType = ActionType.DEBUFF;
        this.attackEffect = AttackEffect.FIRE;
    }

    @Override
    public void update() {
        if (this.target != null && this.target.hasPower("BenMod:SinPower")) {
            this.addToTop(new ApplyPowerAction(this.target, this.source, new SinPower(this.target, this.target.getPower("BenMod:SinPower").amount), this.target.getPower("BenMod:SinPower").amount));
        }
        this.isDone = true;
    }

}
