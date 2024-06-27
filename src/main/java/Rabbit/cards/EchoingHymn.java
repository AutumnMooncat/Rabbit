package Rabbit.cards;

import Rabbit.cardmods.EchoMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.BattleHymn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class EchoingHymn extends AbstractEasyCard {
    public final static String ID = makeID(EchoingHymn.class.getSimpleName());

    public EchoingHymn() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        AbstractCard copy = makeStatEquivalentCopy();
        if (!CardModifierManager.hasModifier(copy, EchoMod.ID)) {
            CardModifierManager.addModifier(copy, new EchoMod());
        }
        addToBot(new MakeTempCardInDiscardAction(copy, 1));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return BattleHymn.ID;
    }
}