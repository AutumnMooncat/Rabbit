package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.red.Impervious;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static Rabbit.MainModfile.makeID;

public class Fortified extends AbstractEasyCard {
    public final static String ID = makeID(Fortified.class.getSimpleName());

    public Fortified() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = block = 10;
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new ArtifactPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }

    @Override
    public String cardArtCopy() {
        return Impervious.ID;
    }
}