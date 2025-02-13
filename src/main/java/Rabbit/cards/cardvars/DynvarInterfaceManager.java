package Rabbit.cards.cardvars;

import Rabbit.MainModfile;
import Rabbit.cardmods.DynvarInterface;
import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.abstracts.DynamicVariable;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.SmithPreview;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.util.HashMap;

public class DynvarInterfaceManager extends DynamicVariable {
    public static DynvarInterfaceManager instance = new DynvarInterfaceManager();
    public static String workingKey = "";

    @Override
    public String key() {
        return MainModfile.makeID("DynvarInterfaceManager");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        for (AbstractCardModifier m : CardModifierManager.modifiers(card)) {
            if (m instanceof DynvarInterface && ((DynvarInterface) m).key().equals(workingKey)) {
                return ((DynvarInterface) m).modified(card);
            }
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        for (AbstractCardModifier m : CardModifierManager.modifiers(card)) {
            if (m instanceof DynvarInterface && ((DynvarInterface) m).key().equals(workingKey)) {
                return ((DynvarInterface) m).val(card);
            }
        }
        return 0;
    }

    @Override
    public int baseValue(AbstractCard card) {
        for (AbstractCardModifier m : CardModifierManager.modifiers(card)) {
            if (m instanceof DynvarInterface && ((DynvarInterface) m).key().equals(workingKey)) {
                return ((DynvarInterface) m).baseVal(card);
            }
        }
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        for (AbstractCardModifier m : CardModifierManager.modifiers(card)) {
            if (m instanceof DynvarInterface && ((DynvarInterface) m).key().equals(workingKey)) {
                return ((DynvarInterface) m).upgraded(card);
            }
        }
        return false;
    }

    public static void registerDynvarCarrier(DynvarInterface dv) {
        registerDynvarCarrier(dv.key());
    }

    public static void registerDynvarCarrier(String dynvarKey) {
        BaseMod.cardDynamicVariableMap.put(dynvarKey, instance);
    }

    @SpirePatch2(clz = SmithPreview.class, method = "ForEachDynamicVariable")
    @SpirePatch2(clz = basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderCustomDynamicVariable.Inner.class, method = "subRenderDynamicVariable")
    @SpirePatch2(clz = basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderCustomDynamicVariableCN.class, method = "Insert")
    @SpirePatch2(clz = basemod.patches.com.megacrit.cardcrawl.screens.SingleCardViewPopup.RenderCustomDynamicVariable.Inner.class, method = "subRenderDynamicVariable")
    @SpirePatch2(clz = basemod.patches.com.megacrit.cardcrawl.screens.SingleCardViewPopup.RenderCustomDynamicVariableCN.class, method = "Insert")
    public static class GrabWorkingKey {
        @SpireInstrumentPatch
        public static ExprEditor patch() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals(HashMap.class.getName()) && m.getMethodName().equals("get")) {
                        m.replace("{ " +
                                "$1 = " + GrabWorkingKey.class.getName() + ".grabWorkingKey($1); " +
                                "$_ = $proceed($$);" +
                                "}");
                    }
                }
            };
        }

        public static Object grabWorkingKey(Object key) {
            if (key instanceof String) {
                workingKey = (String) key;
            }
            return key;
        }
    }

}
