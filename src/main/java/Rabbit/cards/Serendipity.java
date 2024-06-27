package Rabbit.cards;

import Rabbit.actions.JumpAction;
import Rabbit.cards.abstracts.AbstractClutchCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.purple.InnerPeace;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static Rabbit.MainModfile.makeID;

public class Serendipity extends AbstractClutchCard {
    public final static String ID = makeID(Serendipity.class.getSimpleName());

    public Serendipity() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new VigorPower(p, 8));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return InnerPeace.ID;
    }

    @Override
    public void onClutch() {
        addToBot(new JumpAction(magicNumber));
    }
}