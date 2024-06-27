package Rabbit.cards;

import Rabbit.actions.DoAction;
import Rabbit.cardmods.EchoMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Foresight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Clairvoyance extends AbstractEasyCard {
    public final static String ID = makeID(Clairvoyance.class.getSimpleName());

    public Clairvoyance() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoAction(() -> {
            int cards = Math.min(magicNumber, p.drawPile.size());
            for (int i = 0 ; i < cards ; i++) {
                AbstractCard copy = p.drawPile.getNCardFromTop(cards-i-1).makeStatEquivalentCopy();
                if (!CardModifierManager.hasModifier(copy, EchoMod.ID)) {
                    CardModifierManager.addModifier(copy, new EchoMod());
                }
                addToTop(new MakeTempCardInHandAction(copy));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Foresight.ID;
    }
}