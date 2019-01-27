package catgirlmod.powers;

import catgirlmod.CatGirlMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CatGirlPowerBuff_EvadeDontDecay extends AbstractPower {

    public static final String POWER_ID = CatGirlMod.makeID("EvadeDontDecay");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "images/powers/catgirl_evade_dont_decay.png";

    public CatGirlPowerBuff_EvadeDontDecay(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        type = PowerType.BUFF;
        isTurnBased = false;
        img = ImageMaster.loadImage(IMG);
    }

    @Override
    public void atEndOfTurn(boolean bIsPlayer) {
        AbstractPower evade = this.owner.getPower(CatGirlPowerBuff_Evade.POWER_ID);

        if (evade == null) {
            AbstractDungeon.actionManager.addToBottom(
                    new ReducePowerAction(this.owner, this.owner, CatGirlPowerBuff_EvadeDontDecay.POWER_ID, 1)
            );
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
