package benmod.actions;

import benmod.powers.ForgivenessPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class SneakyAttackAction extends AbstractGameAction {
    private DamageInfo info;

    public SneakyAttackAction(AbstractCreature target, AbstractCreature source, DamageInfo info, int blockAmt) {
        this.actionType = ActionType.DEBUFF;
        this.target = target;
        this.source = source;
        this.info = info;
        this.amount = blockAmt;
    }

    @Override
    public void update() {
        this.addToTop(new DamageAction(this.target, this.info, AttackEffect.BLUNT_HEAVY));
        this.addToBot(new GainBlockAction(this.source, this.source, this.amount));
        if (this.target != null && this.target.hasPower("BenMod:ForgivenessPower")) {
            this.addToBot(new ApplyPowerAction(this.target, this.source, new ForgivenessPower(this.target, this.source, 1), 1));
        }
        this.isDone = true;
    }

}
