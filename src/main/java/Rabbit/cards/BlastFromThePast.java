package Rabbit.cards;

import Rabbit.cardmods.EchoMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.SashWhip;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class BlastFromThePast extends AbstractEasyCard {
    public final static String ID = makeID(BlastFromThePast.class.getSimpleName());

    public BlastFromThePast() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 3;
        selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void onRetained() {
        AbstractCard copy = makeStatEquivalentCopy();
        if (!CardModifierManager.hasModifier(copy, EchoMod.ID)) {
            CardModifierManager.addModifier(copy, new EchoMod());
        }
        addToBot(new MakeTempCardInHandAction(copy));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return SashWhip.ID;
    }
}