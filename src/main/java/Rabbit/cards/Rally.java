package Rabbit.cards;

import Rabbit.actions.DoAction;
import Rabbit.actions.JumpAction;
import Rabbit.cardmods.CarrotMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.powers.interfaces.OnPlaceCarrotsPower;
import Rabbit.util.KeywordManager;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Survivor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Rabbit.MainModfile.makeID;

public class Rally extends AbstractEasyCard {
    public final static String ID = makeID(Rally.class.getSimpleName());

    public Rally() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new JumpAction(magicNumber));
        addToBot(new DoAction(() -> {
            for (AbstractCard card : JumpAction.drawnCards) {
                CardModifierManager.addModifier(card, new CarrotMod(1));
                for (AbstractPower pow : p .powers) {
                    if (pow instanceof OnPlaceCarrotsPower) {
                        ((OnPlaceCarrotsPower) pow).onPlaceCarrots(card, 1);
                    }
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
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Survivor.ID;
    }
}