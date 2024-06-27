package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import Rabbit.vfx.BigExplosionVFX;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.Combust;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Rabbit.MainModfile.makeID;

public class Explosion extends AbstractEasyCard {
    public final static String ID = makeID(Explosion.class.getSimpleName());

    public Explosion() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 25;
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BigExplosionVFX(m));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        Wiz.applyToEnemy(m, new WeakPower(m, magicNumber, false));
        Wiz.applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    @Override
    public void upp() {
        upgradeDamage(5);
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Combust.ID;
    }
}