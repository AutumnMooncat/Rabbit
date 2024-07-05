package Rabbit.cutStuff.cards;

import Rabbit.actions.DamageFollowupAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.BleedingPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.LockOn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Snipe extends AbstractEasyCard {
    public final static String ID = makeID(Snipe.class.getSimpleName());

    public Snipe() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageFollowupAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY, false, mon -> {
            if (mon.lastDamageTaken > 0) {
                addToTop(new ApplyPowerAction(m, p, new BleedingPower(m, p, mon.lastDamageTaken)));
            }
        }));
        addToBot(new DamageFollowupAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY, false, mon -> {
            if (mon.lastDamageTaken > 0) {
                addToTop(new ApplyPowerAction(m, p, new BleedingPower(m, p, mon.lastDamageTaken)));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return LockOn.ID;
    }
}