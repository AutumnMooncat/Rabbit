package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.CirclesOfDeathPower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.red.Shockwave;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class CirclesOfDeath extends AbstractEasyCard {
    public final static String ID = makeID(CirclesOfDeath.class.getSimpleName());

    public CirclesOfDeath() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new CirclesOfDeathPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Shockwave.ID;
    }
}