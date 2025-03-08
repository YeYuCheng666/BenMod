package benmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FastChargeEnergyAction extends AbstractGameAction {
    private int LightingCount;

    public FastChargeEnergyAction(int LightingCountNum) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.LightingCount = LightingCountNum;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.player.gainEnergy(LightingCount);
        }
        this.isDone = true;
    }

}
