package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.NextTurnDamagePower;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Headbutt;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class SkullBash extends AbstractEasyCard {
    public final static String ID = makeID(SkullBash.class.getSimpleName());

    public SkullBash() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 24;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new NextTurnDamagePower(p, m, new DamageInfo(p, damage, damageTypeForTurn)));
    }

    @Override
    public void upp() {
        upgradeDamage(8);
    }

    @Override
    public String cardArtCopy() {
        return Headbutt.ID;
    }
}