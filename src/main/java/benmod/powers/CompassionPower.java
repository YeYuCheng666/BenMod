package benmod.powers;

import benmod.helpers.ModHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CompassionPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makePath("CompassionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int hpLoss;

    public CompassionPower(AbstractCreature owner, int hploss, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = Amount;
        this.hpLoss = hploss;
        String path128 = "BenModResources/images/Powers/Example84.png";
        String path48 = "BenModResources/images/Powers/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void atEndOfRound() {
        this.flash();
        this.addToBot(new LoseHPAction(this.owner, this.owner, this.hpLoss, AbstractGameAction.AttackEffect.FIRE));
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new ForgivenessPower(mo, AbstractDungeon.player, this.amount), this.amount, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}