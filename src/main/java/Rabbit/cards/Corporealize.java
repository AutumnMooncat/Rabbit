package Rabbit.cards;

import Rabbit.actions.BetterSelectCardsCenteredAction;
import Rabbit.cardmods.EchoMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.DemonForm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Corporealize extends AbstractEasyCard {
    public final static String ID = makeID(Corporealize.class.getSimpleName());

    public Corporealize() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsCenteredAction(p.exhaustPile.group, cardStrings.EXTENDED_DESCRIPTION[0], card -> CardModifierManager.hasModifier(card, EchoMod.ID), cards -> {
            for (AbstractCard c : cards) {
                AbstractCard copy = c.makeStatEquivalentCopy();
                CardModifierManager.removeModifiersById(copy, EchoMod.ID, false);
                addToTop(new MakeTempCardInHandAction(copy));
            }
        }));
    }

    @Override
    public void upp(){
        exhaust = false;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return DemonForm.ID;
    }
}