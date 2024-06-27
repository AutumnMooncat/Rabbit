package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.VelocityPower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Burst;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Velocity extends AbstractEasyCard {
    public final static String ID = makeID(Velocity.class.getSimpleName());

    public Velocity() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new VelocityPower(p, magicNumber));
    }

    @Override
    public void upp() {
        isInnate = true;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Burst.ID;
    }
}