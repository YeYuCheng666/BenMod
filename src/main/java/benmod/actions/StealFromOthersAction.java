package benmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class StealFromOthersAction extends AbstractGameAction {
    private DamageInfo info;

    public StealFromOthersAction(AbstractCreature target, DamageInfo info) {
        this.actionType = ActionType.BLOCK;
        this.target = target;
        this.info = info;
    }

    @Override
    public void update() {
        if (this.target != null && this.target.hasPower("Strength")) {
            if (this.target.getPower("Strength").amount > 0)
                this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.target.getPower("Strength").amount), this.target.getPower("Strength").amount));
        }
        this.addToTop(new DamageAction(this.target, this.info, AttackEffect.BLUNT_HEAVY));
        this.isDone = true;
    }

}
