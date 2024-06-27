package Rabbit.cards;

import Rabbit.actions.DoAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.green.Prepared;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Rabbit.MainModfile.makeID;

public class MagicSack extends AbstractEasyCard {
    public final static String ID = makeID(MagicSack.class.getSimpleName());

    public MagicSack() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoAction(() -> {
            CardGroup group = new CardGroup(p.drawPile, CardGroup.CardGroupType.UNSPECIFIED);
            group.shuffle(AbstractDungeon.cardRandomRng);
            while (group.size() > magicNumber) {
                group.removeTopCard();
            }
            addToTop(new MoveCardsAction(p.hand, group, 1));
        }));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Prepared.ID;
    }
}