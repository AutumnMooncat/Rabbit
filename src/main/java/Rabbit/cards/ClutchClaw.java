package Rabbit.cards;

import Rabbit.actions.ScaleAllByPredAction;
import Rabbit.cards.abstracts.AbstractClutchCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;

import static Rabbit.MainModfile.makeID;

public class ClutchClaw extends AbstractClutchCard {
    public final static String ID = makeID(ClutchClaw.class.getSimpleName());

    public ClutchClaw() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 9;
        baseMagicNumber = magicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.CYAN, Color.WHITE), 0.1F));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Claw.ID;
    }

    @Override
    public void onClutch() {
        addToBot(new ScaleAllByPredAction(this, magicNumber, ScaleAllByPredAction.StatBoost.DAMAGE, c -> c instanceof ClutchClaw));
    }
}