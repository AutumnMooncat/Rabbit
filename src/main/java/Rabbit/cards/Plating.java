package Rabbit.cards;

import Rabbit.actions.DoAction;
import Rabbit.cards.abstracts.AbstractClutchCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Armaments;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Plating extends AbstractClutchCard {
    public final static String ID = makeID(Plating.class.getSimpleName());

    public Plating() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return Armaments.ID;
    }

    @Override
    public void onClutch() {
        addToBot(new DoAction(() -> {
            for (AbstractCard card : Wiz.adp().hand.group) {
                if (card.canUpgrade()) {
                    card.upgrade();
                }
            }
        }));
    }
}