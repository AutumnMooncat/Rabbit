package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.TimelessPower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.purple.MasterReality;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Timeless extends AbstractEasyCard {
    public final static String ID = makeID(Timeless.class.getSimpleName());

    public Timeless() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new TimelessPower(p, 1));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }

    @Override
    public String cardArtCopy() {
        return MasterReality.ID;
    }
}