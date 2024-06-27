package Rabbit.cards;

import Rabbit.cardmods.CarrotMod;
import Rabbit.cardmods.ExhaustMod;
import Rabbit.cards.abstracts.AbstractClutchCard;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.green.RiddleWithHoles;
import com.megacrit.cardcrawl.cards.purple.Tranquility;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;

import static Rabbit.MainModfile.makeID;

public class BriefRespite extends AbstractClutchCard {
    public final static String ID = makeID(BriefRespite.class.getSimpleName());

    public BriefRespite() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        CardModifierManager.addModifier(this, new CarrotMod(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new EnergizedBluePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Tranquility.ID;
    }

    @Override
    public void onClutch() {
        addToBot(new GainEnergyAction(magicNumber));
    }
}