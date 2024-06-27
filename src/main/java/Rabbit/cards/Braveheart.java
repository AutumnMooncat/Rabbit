package Rabbit.cards;

import Rabbit.cardmods.CarrotMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.red.Warcry;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static Rabbit.MainModfile.makeID;

public class Braveheart extends AbstractEasyCard {
    public final static String ID = makeID(Braveheart.class.getSimpleName());

    public Braveheart() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        CardModifierManager.addModifier(this, new CarrotMod(2));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new VigorPower(p, Wiz.carrotCount(this)));
    }

    @Override
    public void upp() {
        CardModifierManager.addModifier(this, new CarrotMod(1));
    }

    @Override
    public String cardArtCopy() {
        return Warcry.ID;
    }
}