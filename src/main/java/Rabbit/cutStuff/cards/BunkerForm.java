package Rabbit.cutStuff.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.BunkerFormPower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.red.Entrench;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class BunkerForm extends AbstractEasyCard {
    public final static String ID = makeID(BunkerForm.class.getSimpleName());

    public BunkerForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new BunkerFormPower(p, 1));
    }

    @Override
    public void upp() {
        isEthereal = false;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Entrench.ID;
    }
}