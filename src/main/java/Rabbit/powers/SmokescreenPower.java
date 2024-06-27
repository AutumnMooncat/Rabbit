package Rabbit.powers;

import Rabbit.MainModfile;
import Rabbit.actions.ApplyPowerActionWithFollowup;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SmokescreenPower extends AbstractPower {
    public static final String POWER_ID = MainModfile.makeID(SmokescreenPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SmokescreenPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("fumes");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onExhaust(AbstractCard card) {
        flash();
        Wiz.forAllMonstersLiving(m -> addToBot(new ApplyPowerActionWithFollowup(
                new ApplyPowerAction(m, owner, new StrengthPower(m, -amount)),
                new ApplyPowerAction(m, owner, new GainStrengthPower(m, amount))
        )));
    }
}