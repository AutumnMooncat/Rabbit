package Rabbit.cards;

import Rabbit.actions.DoAction;
import Rabbit.actions.MultiUpgradeAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.PowerThrough;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;

import static Rabbit.MainModfile.makeID;

public class WeaponsReady extends AbstractEasyCard {
    public final static String ID = makeID(WeaponsReady.class.getSimpleName());

    public WeaponsReady() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new DoAction(() -> {
            ArrayList<AbstractCard> validCards = new ArrayList<>();
            for (AbstractCard card : p.hand.group) {
                if (card.type == CardType.ATTACK) {
                    validCards.add(card);
                }
            }
            MultiUpgradeAction.performUpgrades(validCards, magicNumber);
        }));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return PowerThrough.ID;
    }
}