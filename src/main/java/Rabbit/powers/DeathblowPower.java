package Rabbit.powers;

import Rabbit.MainModfile;
import Rabbit.actions.DoAction;
import Rabbit.powers.interfaces.OnModifyMaxHPPower;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;

public class DeathblowPower extends AbstractPower implements HealthBarRenderPower, OnModifyMaxHPPower {
    public static final String POWER_ID = MainModfile.makeID(DeathblowPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final Color hpBarColor = Color.DARK_GRAY.cpy();

    public DeathblowPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.priority = 101;
        this.loadRegion("end_turn_death");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    private void checkStacks() {
        if (amount >= owner.currentHealth && owner.currentHealth > 0) {
            flash();
            addToTop(new InstantKillAction(owner));
            addToTop(new RemoveSpecificPowerAction(owner, owner, this));
            addToTop(new VFXAction(new HeartBuffEffect(owner.hb.cX, owner.hb.cY)));
        }
    }

    @Override
    public void onInitialApplication() {
        addToBot(new DoAction(this::checkStacks));
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        addToBot(new DoAction(this::checkStacks));
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        addToBot(new DoAction(this::checkStacks));
    }

    @Override
    public int onHeal(int healAmount) {
        addToBot(new DoAction(this::checkStacks));
        return healAmount;
    }

    @Override
    public int onModifyMaxHP(AbstractCreature target, int amount) {
        addToBot(new DoAction(this::checkStacks));
        return amount;
    }

    @Override
    public int getHealthBarAmount() {
        return amount;
    }

    @Override
    public Color getColor() {
        return hpBarColor;
    }
}