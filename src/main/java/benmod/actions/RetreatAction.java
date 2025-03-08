package benmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class RetreatAction extends AbstractGameAction {
    private int damageIncrease;
    private AbstractMonster targetMonster;

    public RetreatAction(int damageIncrease, AbstractMonster m) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.damageIncrease = damageIncrease;
        this.targetMonster = m;
    }

    @Override
    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.damageIncrease), this.damageIncrease));
        }
        this.isDone = true;
    }

}
