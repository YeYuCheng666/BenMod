package benmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SoulReturningAction extends AbstractGameAction {
    private AbstractPlayer p;

    public SoulReturningAction() {
        this.actionType = ActionType.POWER;
        this.p = AbstractDungeon.player;
    }

    @Override
    public void update() {
        if (this.p.exhaustPile.isEmpty()) {
            this.isDone = true;
        } else {
            for(AbstractCard card : this.p.exhaustPile.group) {
                if (card.type != AbstractCard.CardType.STATUS && card.type != AbstractCard.CardType.CURSE) {
                    this.addToBot(new ExhaustToDrawPileAction(card));
                }
            }
        }
        this.tickDuration();
        this.isDone = true;
    }

}
