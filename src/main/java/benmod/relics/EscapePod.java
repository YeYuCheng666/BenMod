package benmod.relics;

import basemod.abstracts.CustomRelic;
import benmod.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class EscapePod extends CustomRelic {
    public static final String ID = ModHelper.makePath("EscapePod");
    private static final String IMG_PATH = "BenModResources/images/Relics/BlackHeart.png";
    private static final String OUTLINE_PATH = "BenModResources/images/Relics/MyRelic_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public EscapePod() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new EscapePod();
    }

    @Override
    public void update() {
        super.update();
        if (this.hb.hovered && InputHelper.justReleasedClickRight) {
            if (!this.usedUp){
                this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 50));
            }
            this.usedUp();
        }
    }
}