package Rabbit.patches;

import Rabbit.powers.BlessingPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

public class UpgradeHeldCardPatches {
    @SpirePatch2(clz = AbstractPlayer.class, method = "renderHand")
    public static class RenderHack {
        static AbstractCard backup;

        @SpireInsertPatch(locator = Locator.class)
        public static void change(AbstractPlayer __instance) {
            backup = __instance.hoveredCard;
            if (__instance.hasPower(BlessingPower.POWER_ID)) {
                __instance.hoveredCard = backup.makeSameInstanceOf();
                __instance.hoveredCard.upgrade();
                __instance.hoveredCard.current_x = backup.current_x;
                __instance.hoveredCard.current_y = backup.current_y;
                __instance.hoveredCard.angle = backup.angle;
                __instance.hoveredCard.drawScale = backup.drawScale;
                __instance.hoveredCard.applyPowers();
            }
        }

        @SpireInsertPatch(locator = Locator2.class)
        public static void reset(AbstractPlayer __instance) {
            __instance.hoveredCard = backup;
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.MethodCallMatcher(AbstractCard.class, "renderHoverShadow");
                return LineFinder.findInOrder(ctBehavior, m);
            }
        }

        public static class Locator2 extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "screen");
                return new int[]{LineFinder.findInOrder(ctBehavior, m)[0]-1};
            }
        }
    }
}
