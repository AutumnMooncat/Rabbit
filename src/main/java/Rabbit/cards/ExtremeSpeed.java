package Rabbit.cards;

import Rabbit.actions.EasyXCostAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.Blur;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Arrays;

import static Rabbit.MainModfile.makeID;

public class ExtremeSpeed extends AbstractEasyCard {
    public final static String ID = makeID(ExtremeSpeed.class.getSimpleName());

    public ExtremeSpeed() {
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 0;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EasyXCostAction(this, (base, args) -> {
            int effect = base + Arrays.stream(args).sum();
            if (effect > 0) {
                CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard card : p.drawPile.group) {
                    group.addToTop(card);
                }
                group.shuffle(AbstractDungeon.cardRandomRng);
                while (group.size() > effect) {
                    group.removeTopCard();
                }
                addToTop(new MoveCardsAction(p.hand, group, effect, cards -> {
                    for (AbstractCard card : cards) {
                        p.drawPile.removeCard(card);
                        card.setCostForTurn(0);
                    }
                }));
            }
            return true;
            }, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Blur.ID;
    }
}