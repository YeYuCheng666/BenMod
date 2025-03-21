package benmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustToDrawPileAction extends AbstractGameAction {
    private AbstractCard card;
    private AbstractPlayer p;

    public ExhaustToDrawPileAction(AbstractCard card) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.card = card;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.p.exhaustPile.contains(this.card)) {
            this.p.exhaustPile.moveToDeck(this.card, true);
            this.card.stopGlowing();
            this.card.unhover();
            this.card.unfadeOut();
            this.card.lighten(false);
            this.card.applyPowers();
            AbstractDungeon.player.exhaustPile.removeCard(this.card);
        }
        this.tickDuration();
        this.isDone = true;
    }
}
