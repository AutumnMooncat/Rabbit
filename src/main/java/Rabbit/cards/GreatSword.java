package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractClutchCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.red.HeavyBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static Rabbit.MainModfile.makeID;

public class GreatSword extends AbstractClutchCard {
    public final static String ID = makeID(GreatSword.class.getSimpleName());

    public GreatSword() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 14;
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        Wiz.applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    @Override
    public void upp() {
        upgradeDamage(4);
        upgradeBlock(2);
    }

    @Override
    public String cardArtCopy() {
        return HeavyBlade.ID;
    }

    @Override
    public void onClutch() {
        addToBot(new GainBlockAction(Wiz.adp(), block));
    }
}