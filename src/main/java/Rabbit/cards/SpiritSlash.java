package Rabbit.cards;

import Rabbit.cardmods.EchoMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.CardToHandPower;
import Rabbit.powers.DeathblowPower;
import Rabbit.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Havoc;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class SpiritSlash extends AbstractEasyCard {
    public final static String ID = makeID(SpiritSlash.class.getSimpleName());

    public SpiritSlash() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 10;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new DeathblowPower(m, magicNumber), magicNumber, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        AbstractCard copy = makeStatEquivalentCopy();
        if (!CardModifierManager.hasModifier(copy, EchoMod.ID)) {
            CardModifierManager.addModifier(copy, new EchoMod());
        }
        Wiz.applyToSelf(new CardToHandPower(p, 1, copy));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(4);
    }

    @Override
    public String cardArtCopy() {
        return Havoc.ID;
    }
}