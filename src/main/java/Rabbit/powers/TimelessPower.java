package Rabbit.powers;

import Rabbit.MainModfile;
import Rabbit.actions.JumpAction;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.NewExpr;

public class TimelessPower extends AbstractPower implements OnReceivePowerPower {
    public static final String POWER_ID = MainModfile.makeID(TimelessPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TimelessPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("time");
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
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        try {
            //Grab the ctclass of the power
            CtClass ctPower = Loader.getClassPool().get(power.getClass().getName());
            int goodMethods = 0;
            int badMethods = 0;
            boolean[] callsRemove = {false};
            //Iterate the power's declared methods
            //We care that it overrides exactly one of the three next turn triggers and no other hooks
            for (CtMethod ctm : ctPower.getDeclaredMethods()) {
                if (ctm.getName().equals("atStartOfTurnPostDraw") || ctm.getName().equals("atStartOfTurn") || ctm.getName().equals("onEnergyRecharge")) {
                    goodMethods++;
                    ctPower.defrost();
                    ctm.instrument(new ExprEditor() {
                        @Override
                        public void edit(NewExpr e) throws CannotCompileException {
                            if (e.getClassName().equals(RemoveSpecificPowerAction.class.getName())) {
                                callsRemove[0] = true;
                            }
                        }
                    });
                } else if (!ctm.getName().equals("updateDescription") && !ctm.getName().equals("makeCopy") && !ctm.getName().equals("stackPower")) {
                    badMethods++;
                }
            }
            //We probably have a match
            if (goodMethods == 1 && badMethods == 0 && callsRemove[0]) {
                flash();
                for (int i = 0 ; i < amount ; i++) {
                    power.onEnergyRecharge();
                    power.atStartOfTurn();
                    power.atStartOfTurnPostDraw();
                }
            }
        } catch (NotFoundException | CannotCompileException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}