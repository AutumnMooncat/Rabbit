package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.CounterPower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.red.PowerThrough;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class WeaponsReady extends AbstractEasyCard {
    public final static String ID = makeID(WeaponsReady.class.getSimpleName());

    public WeaponsReady() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new CounterPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return PowerThrough.ID;
    }
}