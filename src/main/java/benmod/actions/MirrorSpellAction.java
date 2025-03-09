package benmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class MirrorSpellAction extends AbstractGameAction {

    public MirrorSpellAction(AbstractCreature target, AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.actionType = ActionType.DEBUFF;
        this.attackEffect = AttackEffect.FIRE;
    }

    @Override
    public void update() {
        if (this.source.getPower("Vulnerable") != null ) {
            this.addToBot(new ApplyPowerAction(this.target, this.source, new VulnerablePower(this.target, this.source.getPower("Vulnerable").amount, false), this.source.getPower("Vulnerable").amount));
        }
        if (this.source.getPower("Weak") != null ) {
            this.addToBot(new ApplyPowerAction(this.target, this.source, new WeakPower(this.target, this.source.getPower("Weak").amount, false), this.source.getPower("Weak").amount));
        }
        this.isDone = true;
    }

}
