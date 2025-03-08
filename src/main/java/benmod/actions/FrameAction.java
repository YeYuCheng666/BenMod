package benmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FrameAction extends AbstractGameAction {
    private DamageInfo info;

    public FrameAction(AbstractCreature target, DamageInfo info) {
        this.actionType = ActionType.BLOCK;
        this.target = target;
        this.info = info;
    }

    @Override
    public void update() {
        if (this.target != null && this.target.hasPower("Weak")) {
            this.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
        }
        this.addToTop(new DamageAction(this.target, this.info, AttackEffect.BLUNT_HEAVY));
        this.isDone = true;
    }

}
