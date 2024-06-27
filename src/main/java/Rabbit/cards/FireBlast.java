package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.vfx.BigExplosionVFX;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Eruption;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class FireBlast extends AbstractEasyCard {
    public final static String ID = makeID(FireBlast.class.getSimpleName());

    public FireBlast() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 16;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BigExplosionVFX(m));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void applyPowers() {
        int base = baseDamage;
        super.applyPowers();
        baseDamage = damage;
        super.applyPowers();
        baseDamage = base;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int base = baseDamage;
        super.calculateCardDamage(mo);
        baseDamage = damage;
        super.calculateCardDamage(mo);
        baseDamage = base;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public String cardArtCopy() {
        return Eruption.ID;
    }
}