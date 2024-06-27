package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.red.LimitBreak;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;

import static Rabbit.MainModfile.makeID;

public class Infusion extends AbstractEasyCard {
    public final static String ID = makeID(Infusion.class.getSimpleName());

    public Infusion() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
        Wiz.applyToSelf(new EnergizedBluePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return LimitBreak.ID;
    }
}