package Rabbit.cards;

import Rabbit.actions.JumpAction;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Acrobatics;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;

import static Rabbit.MainModfile.makeID;

public class FlipKick extends AbstractEasyCard {
    public final static String ID = makeID(FlipKick.class.getSimpleName());

    public FlipKick() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new JumpAction(2));
        Wiz.applyToSelf(new FreeAttackPower(p, 1));
    }

    @Override
    public void upp() {
        exhaust = false;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Acrobatics.ID;
    }
}