package Rabbit.cards;

import Rabbit.actions.BetterSelectCardsInHandAction;
import Rabbit.actions.DoAction;
import Rabbit.cardmods.CarrotMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.KeywordManager;
import Rabbit.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.WreathOfFlame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Roast extends AbstractEasyCard {
    public final static String ID = makeID(Roast.class.getSimpleName());

    public Roast() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        CardModifierManager.addModifier(this, new CarrotMod(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], false, false, c -> c.cost >= -1, cards -> {
            for (AbstractCard card : cards) {
                CardModifierManager.addModifier(card, new CarrotMod(1));
            }
        }));
        addToBot(new GainEnergyAction(Wiz.carrotCount(this)));
        /*addToBot(new DoAction(() -> {
            int carrots = 0;
            for (AbstractCard card : p.hand.group) {
                if (CardModifierManager.hasModifier(card, CarrotMod.ID)) {
                    carrots++;
                }
            }
            if (carrots > 0) {
                addToTop(new GainEnergyAction(carrots));
            }
        }));*/
    }

    @Override
    public void initializeDescription() {
        super.initializeDescription();
        keywords.add(KeywordManager.FERVOR);
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new CarrotMod(1));
    }

    @Override
    public String cardArtCopy() {
        return WreathOfFlame.ID;
    }
}