package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Strike_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Kris extends AbstractEasyCard {
    public final static String ID = makeID(Kris.class.getSimpleName());

    public Kris() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, m.currentBlock > 0 ? AbstractGameAction.AttackEffect.SLASH_HEAVY : AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int base = baseDamage;
        if (mo.currentBlock > 0) {
            baseDamage *= 3;
        }
        super.calculateCardDamage(mo);
        baseDamage = base;
        isDamageModified = baseDamage != damage;
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster mon : Wiz.getEnemies()) {
            if (mon.currentBlock > 0) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return Strike_Green.ID;
    }
}