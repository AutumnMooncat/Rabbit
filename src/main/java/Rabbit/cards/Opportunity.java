package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.OpportunityPower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.green.GlassKnife;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Opportunity extends AbstractEasyCard {
    public final static String ID = makeID(Opportunity.class.getSimpleName());

    public Opportunity() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new OpportunityPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return GlassKnife.ID;
    }
}