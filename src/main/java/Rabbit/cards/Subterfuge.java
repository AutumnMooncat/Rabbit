package Rabbit.cards;

import Rabbit.actions.ApplyPowerActionWithFollowup;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.green.Setup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Rabbit.MainModfile.makeID;

public class Subterfuge extends AbstractEasyCard {
    public final static String ID = makeID(Subterfuge.class.getSimpleName());

    public Subterfuge() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.forAllMonstersLiving(mon -> addToBot(new ApplyPowerActionWithFollowup(
                new ApplyPowerAction(mon, p, new StrengthPower(mon, -magicNumber)),
                new ApplyPowerAction(mon, p, new GainStrengthPower(mon, magicNumber))
        )));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Setup.ID;
    }
}