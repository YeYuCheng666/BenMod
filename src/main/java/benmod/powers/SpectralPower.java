package benmod.powers;

import benmod.helpers.ModHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SpectralPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makePath("SpectralPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SpectralPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = Amount;
        String path128 = "BenModResources/images/Powers/Example84.png";
        String path48 = "BenModResources/images/Powers/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.owner != this.owner && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0 && !AbstractDungeon.player.hasRelic("BenMod:SoulBanner")) {
            this.flash();
            this.addToTop(new ReducePowerAction(this.owner, this.owner, "BenMod:SpectralPower", 1));
            if (this.amount == 0)
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "BenMod:SpectralPower"));
        }
    }

    public void atStartOfTurn() {
        this.flash();
        this.addToTop(new HealAction(this.owner, this.owner, this.amount));
        this.addToTop(new ReducePowerAction(this.owner, this.owner, "BenMod:SpectralPower", 1));
        if (this.amount == 0)
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "BenMod:SpectralPower"));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}