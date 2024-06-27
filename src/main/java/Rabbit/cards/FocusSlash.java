package Rabbit.cards;

import Rabbit.cardmods.EchoMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Rampage;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;

import static Rabbit.MainModfile.makeID;

public class FocusSlash extends AbstractEasyCard {
    public final static String ID = makeID(FocusSlash.class.getSimpleName());

    public FocusSlash() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 3;
        baseMagicNumber = magicNumber = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (damage > 16) {
            addToBot(new VFXAction(new BorderLongFlashEffect(Color.LIGHT_GRAY)));
            addToBot(new VFXAction(new DieDieDieEffect(), 0.7F));
            addToBot(new ShakeScreenAction(0.0F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH));
        }
        dmg(m, damage > 8 ? AbstractGameAction.AttackEffect.SLASH_HEAVY : AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        AbstractCard copy = makeStatEquivalentCopy();
        copy.baseDamage += magicNumber;
        copy.cost += 1;
        copy.costForTurn += 1;
        if (!CardModifierManager.hasModifier(copy, EchoMod.ID)) {
            CardModifierManager.addModifier(copy, new EchoMod());
        }
        addToBot(new MakeTempCardInHandAction(copy, 1));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return Rampage.ID;
    }
}