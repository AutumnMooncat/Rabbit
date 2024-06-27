package Rabbit.cards;

import Rabbit.actions.BetterSelectCardsCenteredAction;
import Rabbit.cardmods.CarrotMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.interfaces.OnPlaceCarrotsPower;
import Rabbit.util.KeywordManager;
import Rabbit.util.Wiz;
import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.CloakAndDagger;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Rabbit.MainModfile.makeID;

public class Pluck extends AbstractEasyCard {
    public final static String ID = makeID(Pluck.class.getSimpleName());

    public Pluck() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
        CardModifierManager.addModifier(this, new CarrotMod(2));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsCenteredAction(
                p.discardPile.group,
                Wiz.carrotCount(this),
                BetterDiscardPileToHandAction.TEXT[1] + Wiz.carrotCount(this) + BetterDiscardPileToHandAction.TEXT[2],
                cards -> {
                    for (AbstractCard card : cards) {
                        CardModifierManager.addModifier(card, new CarrotMod(1));
                        for (AbstractPower pow : p .powers) {
                            if (pow instanceof OnPlaceCarrotsPower) {
                                ((OnPlaceCarrotsPower) pow).onPlaceCarrots(card, 1);
                            }
                        }
                        if (p.hand.size() < BaseMod.MAX_HAND_SIZE) {
                            p.hand.addToHand(card);
                            p.discardPile.removeCard(card);
                            card.lighten(false);
                            card.applyPowers();
                        } else {
                            AbstractDungeon.player.createHandIsFullDialog();
                        }
                    }
                }));
    }

    @Override
    public void initializeDescription() {
        super.initializeDescription();
        keywords.add(KeywordManager.FERVOR);
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new CarrotMod(1));
    }

    @Override
    public String cardArtCopy() {
        return CloakAndDagger.ID;
    }
}