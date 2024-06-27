package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.BloodForBlood;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Gambit extends AbstractEasyCard {
    public final static String ID = makeID(Gambit.class.getSimpleName());

    public Gambit() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 18;
        baseMagicNumber = magicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void tookDamage() {
        baseDamage += magicNumber;
    }

    @Override
    public void upp() {
        upgradeDamage(6);
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return BloodForBlood.ID;
    }
}