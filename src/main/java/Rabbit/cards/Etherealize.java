package Rabbit.cards;

import Rabbit.actions.DoAction;
import Rabbit.cardmods.EchoMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.WraithForm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import static Rabbit.MainModfile.makeID;

public class Etherealize extends AbstractEasyCard {
    public final static String ID = makeID(Etherealize.class.getSimpleName());

    public Etherealize() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new IntangiblePlayerPower(p, magicNumber));
        addToBot(new DoAction(() -> {
            for (AbstractCard c : Wiz.getAllCardsInCardGroups(true, true)) {
                if (!CardModifierManager.hasModifier(c, EchoMod.ID)) {
                    CardModifierManager.addModifier(c, new EchoMod());
                }
            }
        }));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return WraithForm.ID;
    }
}