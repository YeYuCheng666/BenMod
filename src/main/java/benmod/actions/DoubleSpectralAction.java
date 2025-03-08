package benmod.actions;

import benmod.powers.SpectralPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DoubleSpectralAction extends AbstractGameAction {

    public DoubleSpectralAction(AbstractCreature target, AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.actionType = ActionType.SPECIAL;
        this.attackEffect = AttackEffect.FIRE;
    }

    @Override
    public void update() {
        if (this.target != null && this.target.hasPower("BenMod:SpectralPower")) {
            this.addToTop(new ApplyPowerAction(this.target, this.source, new SpectralPower(this.target, this.target.getPower("BenMod:SpectralPower").amount), this.target.getPower("BenMod:SpectralPower").amount));
        }
        this.isDone = true;
    }

}
