package benmod.monsters;

import basemod.abstracts.CustomMonster;
import benmod.helpers.ModHelper;
import benmod.powers.RecklessPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.WeakPower;

public class RecklessGoblin extends CustomMonster {
    private static final String ID = ModHelper.makePath("RecklessGoblin");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String IMG = "BenModResources/images/AttackCards/SharpBlade.png";
    private final int recklessAmount;
    private final int weakAmount;
    private boolean firstMove;
    private static final String RECKLESS_NAME = MOVES[0];

    public RecklessGoblin(float x, float y) {
        super(NAME, ID, 128, 0.0F, 0.0F, 250.0F, 270.0F, IMG, x, y);
        this.recklessAmount = 2;
        this.weakAmount = 1;

        if (AbstractDungeon.ascensionLevel >= 7) {
            this.setHp(124, 133);
        } else {
            this.setHp(120, 128);
        }

        int lightDmg;
        int heavyDmg;
        if (AbstractDungeon.ascensionLevel >= 2) {
            lightDmg = 15;
            heavyDmg = 23;
        } else {
            lightDmg = 12;
            heavyDmg = 20;
        }
        this.damage.add(new DamageInfo(this, lightDmg));
        this.damage.add(new DamageInfo(this, heavyDmg));
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
    }

    @Override
    public void getMove(int num) {
        if (this.firstMove) {
            this.firstMove = false;
            this.setMove(RECKLESS_NAME, (byte)2, Intent.BUFF);
        } else {
            if (num < 50) {
                this.setMove((byte)0, Intent.ATTACK_DEBUFF, damage.get(0).base);
            } else {
                this.setMove((byte)1, Intent.ATTACK, damage.get(1).base);
            }
        }
    }

    @Override
    public void takeTurn() {
        switch(this.nextMove) {
            case 0:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                if (AbstractDungeon.ascensionLevel >= 17) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, this.weakAmount + 1, true), this.weakAmount + 1));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, this.weakAmount, true), this.weakAmount));
                }
                break;
            case 1:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                break;
            case 2:
                if (AbstractDungeon.ascensionLevel >= 17) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new RecklessPower(this, this.recklessAmount + 1)));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new RecklessPower(this, this.recklessAmount)));
                }
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

}
