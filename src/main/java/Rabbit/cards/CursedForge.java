package Rabbit.cards;

import Rabbit.actions.MultiUpgradeAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.purple.ConjureBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class CursedForge extends AbstractEasyCard {
    public final static String ID = makeID(CursedForge.class.getSimpleName());

    public CursedForge() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MultiUpgradeAction(magicNumber, c -> c.type == CardType.ATTACK));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return ConjureBlade.ID;
    }
}