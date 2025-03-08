package benmod.characters;

import basemod.abstracts.CustomPlayer;
import benmod.cards.Defend_Golden;
import benmod.cards.GoldenBody;
import benmod.cards.Strike_Golden;
import benmod.modcore.BenMod;
import benmod.relics.JudgeOrder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.relics.Vajra;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import java.util.ArrayList;

import static benmod.characters.SoulWizard.PlayerColorEnum.BENMOD_GOLDEN;
import static benmod.characters.SoulWizard.PlayerColorEnum.Soul_Wizard;

public class SoulWizard extends CustomPlayer {
    private static final String SoulWizard_SHOULDER_1 = "BenModResources/images/Characters/shoulder1.png";
    private static final String SoulWizard_SHOULDER_2 = "BenModResources/images/Characters/shoulder2.png";
    private static final String SoulWizard_CORPSE_IMAGE = "BenModResources/images/Characters/SoulWizard_CORPSE_IMAGE.png";
    private static final String[] SoulWizard_ORB_TEXTURES = new String[]{
            "BenModResources/images/Characters/layer5.png",
            "BenModResources/images/Characters/layer4.png",
            "BenModResources/images/Characters/layer3.png",
            "BenModResources/images/Characters/layer2.png",
            "BenModResources/images/Characters/layer1.png",
            "BenModResources/images/Characters/layer6.png",
            "BenModResources/images/Characters/layer5d.png",
            "BenModResources/images/Characters/layer4d.png",
            "BenModResources/images/Characters/layer3d.png",
            "BenModResources/images/Characters/layer2d.png",
            "BenModResources/images/Characters/layer1d.png"
    };
    private static final float[] SoulWizard_LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("BenMod:SoulWizard");

    public SoulWizard(String name) {
        super(name, Soul_Wizard, SoulWizard_ORB_TEXTURES,"BenModResources/images/Characters/vfx.png", SoulWizard_LAYER_SPEED, null, null);

        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);

        this.initializeClass(
                "BenModResources/images/Characters/SoulWizard.png",
                SoulWizard_SHOULDER_2, SoulWizard_SHOULDER_1,
                SoulWizard_CORPSE_IMAGE,
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F,
                new EnergyManager(3)
        );

    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for(int x = 0; x<4; x++) {
            retVal.add(Strike_Golden.ID);
            retVal.add(Defend_Golden.ID);
        }
        retVal.add("BenMod:GoldenBody");
        retVal.add("BenMod:Condensation");
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(JudgeOrder.ID);
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                characterStrings.NAMES[0],
                characterStrings.TEXT[0],
                45,
                45,
                0,
                99,
                5,
                this,
                this.getStartingRelics(),
                this.getStartingDeck(),
                false
        );
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return BENMOD_GOLDEN;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new GoldenBody();
    }

    // 卡牌轨迹颜色
    @Override
    public Color getCardTrailColor() {
        return BenMod.SoulWizard_Color;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel("BenModResources/images/Characters/Victory1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("BenModResources/images/Characters/Victory2.png"));
        panels.add(new CutscenePanel("BenModResources/images/Characters/Victory3.png"));
        return panels;
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new SoulWizard(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    @Override
    public Color getSlashAttackColor() {
        return BenMod.SoulWizard_Color;
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    @Override
    public Color getCardRenderColor() {
        return BenMod.SoulWizard_Color;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    public static class PlayerColorEnum {
        @SpireEnum
        public static PlayerClass Soul_Wizard;
        @SpireEnum
        public static AbstractCard.CardColor BENMOD_GOLDEN;
    }

    public static class PlayerLibraryEnum {
        @SpireEnum
        public static CardLibrary.LibraryType BENMOD_GOLDEN;
    }
}
