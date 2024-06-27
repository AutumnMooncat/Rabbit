package Rabbit.cards;

import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.LikeWater;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class Flow extends AbstractEasyCard {
    public final static String ID = makeID(Flow.class.getSimpleName());

    public Flow() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = block = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        addToTop(new GainBlockAction(Wiz.adp(), block));
    }

    @Override
    public void upp() {
        upgradeBlock(1);
    }

    @Override
    public String cardArtCopy() {
        return LikeWater.ID;
    }
}