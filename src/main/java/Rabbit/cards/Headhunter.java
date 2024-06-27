package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.SwordBoomerang;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Rabbit.MainModfile.makeID;

public class Headhunter extends AbstractEasyCard {
    public final static String ID = makeID(Headhunter.class.getSimpleName());

    public Headhunter() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 4;
        baseMagicNumber = magicNumber = 1;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new WeakPower(mon, magicNumber, false)));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return SwordBoomerang.ID;
    }
}