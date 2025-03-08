package benmod.relics;

import basemod.abstracts.CustomRelic;
import benmod.helpers.ModHelper;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class EnergyDrink extends CustomRelic {
    public static final String ID = ModHelper.makePath("EnergyDrink");
    private static final String IMG_PATH = "BenModResources/images/Relics/EnergyDrink.png";
    private static final String OUTLINE_PATH = "BenModResources/images/Relics/MyRelic_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public EnergyDrink() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new EnergyDrink();
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        this.flash();
        AbstractDungeon.player.heal(1);
    }
}