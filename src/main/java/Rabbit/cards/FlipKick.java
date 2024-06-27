package Rabbit.cards;

import Rabbit.actions.DoAction;
import Rabbit.actions.JumpAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Acrobatics;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class FlipKick extends AbstractEasyCard {
    public final static String ID = makeID(FlipKick.class.getSimpleName());

    public FlipKick() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new JumpAction(2));
        addToBot(new DoAction(() -> {
            for (AbstractCard card : JumpAction.drawnCards) {
                if (card.type == CardType.ATTACK) {
                    card.setCostForTurn(0);
                }
            }
        }));
    }

    @Override
    public void upp() {
        exhaust = false;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Acrobatics.ID;
    }
}