package benmod.cards;

import benmod.helpers.ModHelper;
import benmod.powers.SoulFlamePower;
import benmod.powers.SpectralPower;
import benmod.powers.StunnedPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

import static benmod.characters.SoulWizard.PlayerColorEnum.BENMOD_GOLDEN;

public class SoulFlame extends CustomCard {
    public static final String ID = ModHelper.makePath("SoulFlame");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "BenModResources/images/SkillCards/HardShield.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = BENMOD_GOLDEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public SoulFlame() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 6;
        this.magicNumber = 6;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
        this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SpectralPower(p, -3), -3));
        this.addToBot(new ApplyPowerAction(p, p, new SoulFlamePower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            if (AbstractDungeon.player.getPower("BenMod:SpectralPower") == null ||AbstractDungeon.player.getPower("BenMod:SpectralPower").amount < 3) {
                canUse = false;
            }
            return canUse;
        }
    }

    public AbstractCard makeCopy() {
        return new SoulFlame();
    }
}
