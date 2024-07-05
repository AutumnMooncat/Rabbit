package Rabbit.powers;

import Rabbit.MainModfile;
import Rabbit.actions.DoAction;
import Rabbit.actions.MultiUpgradeAction;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class MoonbeamPower extends AbstractPower {
    public static final String POWER_ID = MainModfile.makeID(MoonbeamPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MoonbeamPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("focus");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        flash();
        addToBot(new DoAction(() -> {
            ArrayList<AbstractCard> validCards = new ArrayList<>();
            for (AbstractCard card : Wiz.adp().hand.group) {
                if (card.type != AbstractCard.CardType.STATUS && card.type != AbstractCard.CardType.CURSE) {
                    validCards.add(card);
                }
            }
            MultiUpgradeAction.performUpgrades(validCards, amount);
        }));
    }
}