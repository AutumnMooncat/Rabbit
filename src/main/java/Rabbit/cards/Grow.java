package Rabbit.cards;

import Rabbit.actions.DoIfAction;
import Rabbit.cardmods.CarrotMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.interfaces.OnPlaceCarrotsPower;
import Rabbit.util.KeywordManager;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.blue.HelloWorld;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Rabbit.MainModfile.makeID;

public class Grow extends AbstractEasyCard {
    public final static String ID = makeID(Grow.class.getSimpleName());

    public Grow() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        CardModifierManager.addModifier(this, new CarrotMod(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoIfAction(
                () -> !p.drawPile.isEmpty(),
                () -> {
                    CardModifierManager.addModifier(p.drawPile.getTopCard(), new CarrotMod(magicNumber));
                    for (AbstractPower pow : p .powers) {
                        if (pow instanceof OnPlaceCarrotsPower) {
                            ((OnPlaceCarrotsPower) pow).onPlaceCarrots(p.drawPile.getTopCard(), magicNumber);
                        }
                    }
                }
        ));
    }

    @Override
    public void initializeDescription() {
        super.initializeDescription();
        keywords.add(KeywordManager.FERVOR);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return HelloWorld.ID;
    }
}