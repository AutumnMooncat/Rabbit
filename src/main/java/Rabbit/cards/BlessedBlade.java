package Rabbit.cards;

import Rabbit.actions.DoAction;
import Rabbit.actions.MultiUpgradeAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Inflame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;

import java.util.Collections;

import static Rabbit.MainModfile.makeID;

public class BlessedBlade extends AbstractEasyCard {
    public final static String ID = makeID(BlessedBlade.class.getSimpleName());

    public BlessedBlade() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, this.timesUpgraded), 0.2F));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new DoAction(() -> MultiUpgradeAction.performUpgrades(Collections.singletonList(this), 1)));
    }

    @Override
    public void upp() {
        upgradeDamage(5);
    }

    @Override
    public String cardArtCopy() {
        return Inflame.ID;
    }
}