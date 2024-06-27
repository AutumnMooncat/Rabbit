package Rabbit.cards;

import Rabbit.actions.DoAction;
import Rabbit.actions.JumpAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.patches.CardCounterPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.red.Bludgeon;
import com.megacrit.cardcrawl.cards.red.SeverSoul;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static Rabbit.MainModfile.makeID;

public class HelmBreaker extends AbstractEasyCard {
    public final static String ID = makeID(HelmBreaker.class.getSimpleName());

    public HelmBreaker() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new JumpAction(1));
        addToBot(new DoAction(() -> {
            for (int i = 0 ; i < CardCounterPatches.jumpsThisTurn ; i++) {
                dmgTop(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, true);
            }
        }));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        baseInfo = info = damage * (CardCounterPatches.jumpsThisTurn + 1);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        baseInfo = info = damage * (CardCounterPatches.jumpsThisTurn + 1);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return SeverSoul.ID;
    }
}