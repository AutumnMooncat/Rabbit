package Rabbit.cards;

import Rabbit.actions.JumpAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.purple.Vault;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Dive extends AbstractEasyCard {
    public final static String ID = makeID(Dive.class.getSimpleName());

    public Dive() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new JumpAction(magicNumber));
    }

    @Override
    public void upp() {
        isInnate = true;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Vault.ID;
    }
}