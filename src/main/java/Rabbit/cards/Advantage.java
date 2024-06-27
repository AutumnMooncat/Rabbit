package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.NextTurnJumpPower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Outmaneuver;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;

import static Rabbit.MainModfile.makeID;

public class Advantage extends AbstractEasyCard {
    public final static String ID = makeID(Advantage.class.getSimpleName());

    public Advantage() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new NextTurnJumpPower(p, 1));
        Wiz.applyToSelf(new EnergizedBluePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Outmaneuver.ID;
    }
}