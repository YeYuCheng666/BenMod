package benmod.relics;

import basemod.abstracts.CustomRelic;
import benmod.helpers.ModHelper;
import benmod.powers.SpectralPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class DevilOrder extends CustomRelic {
    public static final String ID = ModHelper.makePath("DevilOrder");
    private static final String IMG_PATH = "BenModResources/images/Relics/BlackHeart.png";
    private static final String OUTLINE_PATH = "BenModResources/images/Relics/MyRelic_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public DevilOrder() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic("BenMod:JudgeOrder");
    }

    public AbstractRelic makeCopy() {
        return new DevilOrder();
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SpectralPower(AbstractDungeon.player, 5), 5));
    }
}