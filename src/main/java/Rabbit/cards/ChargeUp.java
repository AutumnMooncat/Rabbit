package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.blue.Aggregate;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

import static Rabbit.MainModfile.makeID;

public class ChargeUp extends AbstractEasyCard {
    public final static String ID = makeID(ChargeUp.class.getSimpleName());

    public ChargeUp() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 10;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new NextTurnBlockPower(p, block));
        Wiz.applyToSelf(new EnergizedBluePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }

    @Override
    public String cardArtCopy() {
        return Aggregate.ID;
    }
}