package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.BleedingPower;
import Rabbit.powers.DeathblowPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.red.Bludgeon;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static Rabbit.MainModfile.makeID;

public class Lethality extends AbstractEasyCard {
    public final static String ID = makeID(Lethality.class.getSimpleName());

    public Lethality() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 20;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        }
        addToBot(new WaitAction(0.8F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int base = baseDamage;
        AbstractPower pow = mo.getPower(BleedingPower.POWER_ID);
        if (pow != null) {
            baseDamage += pow.amount;
        }
        super.calculateCardDamage(mo);
        baseDamage = base;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void upp() {
        upgradeDamage(8);
    }

    @Override
    public String cardArtCopy() {
        return Bludgeon.ID;
    }
}