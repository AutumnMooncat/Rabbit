package Rabbit.cards;

import Rabbit.actions.ApplyPowerActionWithFollowup;
import Rabbit.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.purple.Halt;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Rabbit.MainModfile.makeID;

public class TimeOut extends AbstractEasyCard {
    public final static String ID = makeID(TimeOut.class.getSimpleName());

    public TimeOut() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerActionWithFollowup(
                new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumber)),
                new ApplyPowerAction(m, p, new GainStrengthPower(m, magicNumber))
        ));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Halt.ID;
    }
}