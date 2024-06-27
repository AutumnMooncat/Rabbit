package Rabbit.cards;

import Rabbit.cardmods.CarrotMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.SnackPower;
import Rabbit.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.red.Feed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Snack extends AbstractEasyCard {
    public final static String ID = makeID(Snack.class.getSimpleName());

    public Snack() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        CardModifierManager.addModifier(this, new CarrotMod(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new SnackPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        CardModifierManager.addModifier(this, new CarrotMod(1));
    }

    @Override
    public String cardArtCopy() {
        return Feed.ID;
    }
}