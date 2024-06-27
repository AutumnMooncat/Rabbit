package Rabbit.cards;

import Rabbit.actions.DoIfAction;
import Rabbit.actions.JumpAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.patches.CardCounterPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Dropkick;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class DiveBomb extends AbstractEasyCard {
    public final static String ID = makeID(DiveBomb.class.getSimpleName());

    public DiveBomb() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new DoIfAction(
                () -> CardCounterPatches.jumpsThisTurn > 0,
                () -> {
                    addToTop(new GainEnergyAction(1));
                    addToTop(new JumpAction(1));
                }
        ));
    }

    public void triggerOnGlowCheck() {
        if (CardCounterPatches.jumpsThisTurn > 0) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return Dropkick.ID;
    }
}