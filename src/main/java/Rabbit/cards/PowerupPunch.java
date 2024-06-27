package Rabbit.cards;

import Rabbit.cardmods.CarrotMod;
import Rabbit.cards.abstracts.AbstractEasyCard;
import Rabbit.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.green.SuckerPunch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Rabbit.MainModfile.makeID;

public class PowerupPunch extends AbstractEasyCard {
    public final static String ID = makeID(PowerupPunch.class.getSimpleName());

    public PowerupPunch() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        CardModifierManager.addModifier(this, new CarrotMod(2));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Wiz.applyToSelf(new StrengthPower(p, Wiz.carrotCount(this)));
        Wiz.applyToSelf(new LoseStrengthPower(p, Wiz.carrotCount(this)));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        CardModifierManager.addModifier(this, new CarrotMod(1));
    }

    @Override
    public String cardArtCopy() {
        return SuckerPunch.ID;
    }
}