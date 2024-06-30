package Rabbit.cards;

import Rabbit.actions.DoIfAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.patches.CardCounterPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.FlyingKnee;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class SoaringStrike extends AbstractEasyCard {
    public final static String ID = makeID(SoaringStrike.class.getSimpleName());

    public SoaringStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 2;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new DoIfAction(
                () -> CardCounterPatches.jumpsThisTurn > 0,
                () -> addToTop(new GainEnergyAction(magicNumber))
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
        upgradeDamage(4);
    }

    @Override
    public String cardArtCopy() {
        return FlyingKnee.ID;
    }
}