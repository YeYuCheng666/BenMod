package benmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class BounceBackAction extends AbstractGameAction {

    private int blockPerCard;

    public BounceBackAction(int blockAmount) {
        this.blockPerCard = blockAmount;
        this.setValues(AbstractDungeon.player, AbstractDungeon.player);
        this.actionType = ActionType.BLOCK;
    }

    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList();

        for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) {
                cardsToExhaust.add(c);
            }
        }

        for(AbstractCard c : cardsToExhaust) {
            this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.blockPerCard));
        }

        for(AbstractCard c : cardsToExhaust) {
            this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }

        this.isDone = true;
    }

}
