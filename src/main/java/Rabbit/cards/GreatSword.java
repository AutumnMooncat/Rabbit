package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractClutchCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.red.HeavyBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static Rabbit.MainModfile.makeID;

public class GreatSword extends AbstractClutchCard {
    public final static String ID = makeID(GreatSword.class.getSimpleName());

    public GreatSword() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 10;
        baseBlock = block = 5;
        baseMagicNumber = magicNumber = 2;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new VulnerablePower(mon, magicNumber, false)));
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