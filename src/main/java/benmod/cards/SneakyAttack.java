package benmod.cards;

import benmod.actions.PunishmentAction;
import benmod.actions.SneakyAttackAction;
import benmod.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

import static benmod.characters.SoulWizard.PlayerColorEnum.BENMOD_GOLDEN;

public class SneakyAttack extends CustomCard {
    public static final String ID = ModHelper.makePath("SneakyAttack");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "BenModResources/images/AttackCards/SharpBlade.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = BENMOD_GOLDEN;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public SneakyAttack() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 2;
        this.block = this.baseBlock = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeBlock(2);
        }
        this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SneakyAttackAction(m, p, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.block));
    }

    public AbstractCard makeCopy() {
        return new SneakyAttack();
    }
}
