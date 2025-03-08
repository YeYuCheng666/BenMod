package benmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;

public class BloodPactAction extends AbstractGameAction {
    private int lossHPAmount;
    private int maxHPAmount;
    private AbstractPlayer p;

    public BloodPactAction(AbstractPlayer p, int lossHPAmount, int maxHPAmount) {
        this.p = p;
        this.lossHPAmount = lossHPAmount;
        this.maxHPAmount = maxHPAmount;
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        this.addToBot(new LoseHPAction(p, p, lossHPAmount));
        p.increaseMaxHp(this.maxHPAmount, false);
        this.isDone = true;
    }

}
