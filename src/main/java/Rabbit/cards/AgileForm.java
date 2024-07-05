package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.AgilePower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Burst;
import com.megacrit.cardcrawl.cards.green.Expertise;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class AgileForm extends AbstractEasyCard {
    public final static String ID = makeID(AgileForm.class.getSimpleName());

    public AgileForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new AgilePower(p, 1));
    }

    @Override
    public void upp() {
        isEthereal = false;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Expertise.ID;
    }
}