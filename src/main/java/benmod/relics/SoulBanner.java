package benmod.relics;

import basemod.abstracts.CustomRelic;
import benmod.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SoulBanner extends CustomRelic {
    public static final String ID = ModHelper.makePath("SoulBanner");
    private static final String IMG_PATH = "BenModResources/images/Relics/BlackHeart.png";
    private static final String OUTLINE_PATH = "BenModResources/images/Relics/MyRelic_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public SoulBanner() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new SoulBanner();
    }

}