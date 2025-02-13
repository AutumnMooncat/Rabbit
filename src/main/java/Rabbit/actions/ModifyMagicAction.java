package Rabbit.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class ModifyMagicAction extends AbstractGameAction {
    UUID uuid;

    public ModifyMagicAction(UUID targetUUID, int amount) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
    }

    public void update() {
        for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
            c.baseMagicNumber += this.amount;
            c.magicNumber += this.amount;
            if (c.baseMagicNumber < 0) {
                c.baseMagicNumber = 0;
            }
            if (c.magicNumber < 0) {
                c.magicNumber = 0;
            }
        }
        this.isDone = true;
    }
}
