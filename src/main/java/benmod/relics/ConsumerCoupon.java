package benmod.relics;

import basemod.abstracts.CustomRelic;
import benmod.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;

public class ConsumerCoupon extends CustomRelic {
    public static final String ID = ModHelper.makePath("ConsumerCoupon");
    private static final String IMG_PATH = "BenModResources/images/Relics/ConsumerCoupon.png";
    private static final String OUTLINE_PATH = "BenModResources/images/Relics/MyRelic_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public ConsumerCoupon() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ConsumerCoupon();
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        if (room instanceof ShopRoom) {
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.player.gainGold(50);
        }
    }
}