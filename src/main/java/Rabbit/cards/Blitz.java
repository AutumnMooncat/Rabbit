package Rabbit.cards;

import Rabbit.actions.AttackDamageRandomEnemyFollowupAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.green.AllOutAttack;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Rabbit.MainModfile.makeID;

public class Blitz extends AbstractEasyCard {
    public final static String ID = makeID(Blitz.class.getSimpleName());

    public Blitz() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 5;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AttackDamageRandomEnemyFollowupAction(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT, mon -> {
            if (mon instanceof AbstractMonster) {
                Wiz.applyToEnemyTop((AbstractMonster) mon, new WeakPower(mon, magicNumber, false));
            }
        }));
        addToBot(new AttackDamageRandomEnemyFollowupAction(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT, mon -> {
            if (mon instanceof AbstractMonster) {
                Wiz.applyToEnemyTop((AbstractMonster) mon, new WeakPower(mon, magicNumber, false));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return AllOutAttack.ID;
    }
}