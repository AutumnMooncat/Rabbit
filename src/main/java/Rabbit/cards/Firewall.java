package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractClutchCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.red.FlameBarrier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Firewall extends AbstractClutchCard {
    public final static String ID = makeID(Firewall.class.getSimpleName());

    public Firewall() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 12;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }

    @Override
    public String cardArtCopy() {
        return FlameBarrier.ID;
    }

    @Override
    public void onClutch() {
        addToBot(new ExhaustAction(Wiz.adp().hand.size(), false, true, true));
    }
}