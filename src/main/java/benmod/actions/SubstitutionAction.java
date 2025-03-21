package benmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SubstitutionAction extends AbstractGameAction {
    private int temp1;
    private int temp2;
    private int temp3;
    private int temp4;

    public SubstitutionAction() {
        this.actionType = ActionType.POWER;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.getPower("Strength") != null && AbstractDungeon.player.getPower("Dexterity") != null) {
            temp1 = AbstractDungeon.player.getPower("Strength").amount;
            temp2 = AbstractDungeon.player.getPower("Dexterity").amount;
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -temp1), -temp1));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, temp2), temp2));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, -temp2), -temp2));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, temp1), temp1));
        }
        else if (AbstractDungeon.player.getPower("Strength") != null && AbstractDungeon.player.getPower("Dexterity") == null) {
            temp3 = AbstractDungeon.player.getPower("Strength").amount;
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -temp3), -temp3));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, temp3), temp3));
        }
        else if (AbstractDungeon.player.getPower("Strength") == null && AbstractDungeon.player.getPower("Dexterity") != null) {
            temp4 = AbstractDungeon.player.getPower("Dexterity").amount;
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, temp4), temp4));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, -temp4), -temp4));
        }
        this.isDone = true;
    }

}
