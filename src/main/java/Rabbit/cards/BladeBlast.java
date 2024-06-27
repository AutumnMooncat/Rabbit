package Rabbit.cards;

import Rabbit.cardmods.ExhaustMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.green.RiddleWithHoles;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class BladeBlast extends AbstractEasyCard {
    public final static String ID = makeID(BladeBlast.class.getSimpleName());

    public BladeBlast() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
        Strike strike = new Strike();
        strike.cost = strike.costForTurn = 0;
        CardModifierManager.addModifier(strike, new ExhaustMod());
        cardsToPreview = strike;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(cardsToPreview.makeStatEquivalentCopy(), magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return RiddleWithHoles.ID;
    }
}