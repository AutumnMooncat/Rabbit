package Rabbit.powers;

import Rabbit.MainModfile;
import Rabbit.actions.ApplyCardModifierAction;
import Rabbit.cardmods.BlessingMod;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BlessingPower extends AbstractPower {
    public static final String POWER_ID = MainModfile.makeID(BlessingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BlessingPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("nirvana");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public float modifyBlock(float blockAmount) {
        return blockAmount + amount;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return damage + amount;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.baseBlock > -1 || card.baseDamage > -1) {
            flash();
            addToBot(new ApplyCardModifierAction(card, new BlessingMod(amount)));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }
}