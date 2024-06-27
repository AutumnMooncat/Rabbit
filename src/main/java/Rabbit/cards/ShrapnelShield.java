package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.LosePowerLaterPower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.blue.AutoShields;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static Rabbit.MainModfile.makeID;

public class ShrapnelShield extends AbstractEasyCard {
    public final static String ID = makeID(ShrapnelShield.class.getSimpleName());

    public ShrapnelShield() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 4;
        baseMagicNumber = magicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new ThornsPower(p, magicNumber));
        Wiz.applyToSelf(new LosePowerLaterPower(p, new ThornsPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return AutoShields.ID;
    }
}