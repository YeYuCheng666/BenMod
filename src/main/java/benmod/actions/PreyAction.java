package benmod.actions;

import benmod.powers.SpectralPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class PreyAction extends AbstractGameAction {
    private int energyGainAmt;
    private int spectralGainAmt;
    private DamageInfo info;

    public PreyAction(AbstractCreature target, DamageInfo info, int energyAmt, int spectralAmt) {
        this.info = info;
        this.setValues(target, info);
        this.energyGainAmt = energyAmt;
        this.spectralGainAmt = spectralAmt;
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY));
            this.target.damage(this.info);
            if (((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) {
                this.addToBot(new GainEnergyAction(this.energyGainAmt));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SpectralPower(AbstractDungeon.player, this.spectralGainAmt), this.spectralGainAmt));
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        this.tickDuration();
    }

}
