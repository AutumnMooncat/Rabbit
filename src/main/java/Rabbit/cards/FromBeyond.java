package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.red.Exhume;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class FromBeyond extends AbstractEasyCard {
    public final static String ID = makeID(FromBeyond.class.getSimpleName());

    public FromBeyond() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
        baseMagicNumber = magicNumber = 12;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.POISON);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(3);
    }

    @Override
    public String cardArtCopy() {
        return Exhume.ID;
    }

    @Override
    public void triggerOnExhaust() {
        Wiz.forAllMonstersLiving(mon -> addToBot(new LoseHPAction(mon, Wiz.adp(), magicNumber, AbstractGameAction.AttackEffect.POISON)));
    }
}