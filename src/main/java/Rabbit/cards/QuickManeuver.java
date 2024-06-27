package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.green.DodgeAndRoll;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;

import static Rabbit.MainModfile.makeID;

public class QuickManeuver extends AbstractEasyCard {
    public final static String ID = makeID(QuickManeuver.class.getSimpleName());

    public QuickManeuver() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 5;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new DexterityPower(p, magicNumber));
        Wiz.applyToSelf(new LoseStrengthPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return DodgeAndRoll.ID;
    }
}