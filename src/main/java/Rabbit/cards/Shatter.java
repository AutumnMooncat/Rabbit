package Rabbit.cards;

import Rabbit.actions.BetterSelectCardsInHandAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Consume;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static Rabbit.MainModfile.makeID;

public class Shatter extends AbstractEasyCard {
    public final static String ID = makeID(Shatter.class.getSimpleName());

    public Shatter() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(1, ExhaustAction.TEXT[0], false, false, c -> c.type == CardType.ATTACK, cards -> {
            for (AbstractCard card : cards) {
                Wiz.applyToSelfTop(new VigorPower(p, 2 * (int) CardModifierManager.onModifyBaseDamage(card.baseDamage, card, null)));
                addToTop(new ExhaustSpecificCardAction(card, p.hand));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return Consume.ID;
    }
}