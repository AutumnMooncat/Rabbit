package Rabbit.cards;

import Rabbit.actions.DoIfAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.RecklessCharge;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Rabbit.MainModfile.makeID;

public class DireStrike extends AbstractEasyCard {
    public final static String ID = makeID(DireStrike.class.getSimpleName());

    public DireStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        addToBot(new DoIfAction(
                () -> p.powers.stream().anyMatch(pow -> pow.type == AbstractPower.PowerType.DEBUFF),
                () -> dmgTop(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL)
        ));
    }

    public void triggerOnGlowCheck() {
        if (Wiz.adp().powers.stream().anyMatch(pow -> pow.type == AbstractPower.PowerType.DEBUFF)) {
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
        return RecklessCharge.ID;
    }
}