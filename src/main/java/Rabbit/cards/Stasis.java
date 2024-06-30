package Rabbit.cards;

import Rabbit.actions.BetterSelectCardsInHandAction;
import Rabbit.cardmods.EchoMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.CardToHandPower;
import Rabbit.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.BattleTrance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Stasis extends AbstractEasyCard {
    public final static String ID = makeID(Stasis.class.getSimpleName());

    public Stasis() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(1, ExhaustAction.TEXT[0], false, false, c -> true, cards -> {
            for (AbstractCard card : cards) {
                AbstractCard copy = card.makeStatEquivalentCopy();
                if (!CardModifierManager.hasModifier(copy, EchoMod.ID)) {
                    CardModifierManager.addModifier(copy, new EchoMod());
                }
                Wiz.applyToSelfTop(new CardToHandPower(p, magicNumber, copy));
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
        return BattleTrance.ID;
    }
}