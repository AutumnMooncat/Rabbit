package Rabbit.cards;

import Rabbit.cardmods.CarrotMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import basemod.helpers.CardModifierManager;
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
        CardModifierManager.addModifier(this, new CarrotMod(1));
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new ArtifactPower(p, Wiz.carrotCount(this)));
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