package benmod.powers;

import benmod.helpers.ModHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SoulLinkPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makePath("SoulLinkPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SoulLinkPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        String path128 = "BenModResources/images/Powers/Example84.png";
        String path48 = "BenModResources/images/Powers/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void onDeath() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead() && this.owner.currentHealth <= 0) {
            if (this.owner.hasPower("BenMod:SoulLinkPower") && this.owner.hasPower("BenMod:ForgivenessPower")) {
                int amount = this.owner.getPower("BenMod:ForgivenessPower").amount;
                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    this.flash();
                    this.addToBot(new ApplyPowerToRandomEnemyAction(AbstractDungeon.player, new ForgivenessPower((AbstractCreature)null, AbstractDungeon.player, amount), amount, false, AbstractGameAction.AttackEffect.NONE));
                }
            }
        }

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}