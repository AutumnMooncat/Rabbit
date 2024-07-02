package Rabbit.cards;

import Rabbit.actions.BetterSelectCardsInHandAction;
import Rabbit.cards.abstracts.AbstractClutchCard;
import Rabbit.powers.CounterPower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Concentrate;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class CastOff extends AbstractClutchCard {
    public final static String ID = makeID(CastOff.class.getSimpleName());

    public CastOff() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 4;
        baseBlock = block = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(magicNumber, DiscardAction.TEXT[0], true, true, c -> true, cards -> {
            for (AbstractCard card : cards) {
                addToTop(new GainBlockAction(p, block));
                addToTop(new DiscardSpecificCardAction(card, p.hand));
            }
        }));
    }

    @Override
    public void upp() {
        //upgradeMagicNumber(1);
        upgradeBlock(2);
        upgradeSecondMagic(2);
    }

    @Override
    public String cardArtCopy() {
        return Concentrate.ID;
    }

    @Override
    public void onClutch() {
        Wiz.applyToSelf(new CounterPower(Wiz.adp(), secondMagic));
    }
}