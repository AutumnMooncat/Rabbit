package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.patches.EnterCardGroupPatches;
import Rabbit.powers.CounterPower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.Setup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import static Rabbit.MainModfile.makeID;

public class Subterfuge extends AbstractEasyCard implements EnterCardGroupPatches.OnEnterCardGroupCard {
    public final static String ID = makeID(Subterfuge.class.getSimpleName());

    public Subterfuge() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 9;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }

    @Override
    public String cardArtCopy() {
        return Setup.ID;
    }

    @Override
    public void onEnter(CardGroup g) {
        if (g == Wiz.adp().drawPile) {
            Wiz.applyToSelfTop(new CounterPower(Wiz.adp(), magicNumber));
            AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(this.makeStatEquivalentCopy()));;
        }
    }
}