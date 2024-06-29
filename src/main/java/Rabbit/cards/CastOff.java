package Rabbit.cards;

import Rabbit.actions.BetterSelectCardsInHandAction;
import Rabbit.cards.abstracts.AbstractClutchCard;
import Rabbit.powers.CounterPower;
import Rabbit.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Concentrate;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class CastOff extends AbstractClutchCard {
    public final static String ID = makeID(CastOff.class.getSimpleName());

    public CastOff() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 7;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(1, ExhaustAction.TEXT[0], false, false, c -> c.type == CardType.ATTACK, cards -> {
            for (AbstractCard card : cards) {
                Wiz.applyToSelfTop(new CounterPower(p, 2 * (int) CardModifierManager.onModifyBaseDamage(card.baseDamage, card, null)));
                addToTop(new ExhaustSpecificCardAction(card, p.hand));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return Concentrate.ID;
    }

    @Override
    public void onClutch() {
        Wiz.applyToSelf(new CounterPower(Wiz.adp(), magicNumber));
    }
}