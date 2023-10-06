package com.github.kobting.thepain.powers

import com.github.kobting.annotations.PowerString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.github.kobting.thepain.patches.bloodBottlef
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

@PowerString(prefix = PREFIX, name = ProtectionPower.NAME, descriptions = ["Heal for the number of stacks of blood you have at the end of your turn."], language = Language.ENGLISH)
class ProtectionPower(owner: AbstractCreature, amount: Int) : PainCustomPower(ID, owner, amount, "com/github/kobting/thepain/images/powers/protection.png") {

    companion object {
        const val NAME = "Protection"
        const val ID = "$PREFIX:$NAME"
    }

    init {
        type = PowerType.BUFF
        isTurnBased = true
        priority = 98
    }

    override fun atEndOfTurn(isPlayer: Boolean) {
        if (isPlayer) {
            val bloodCount = bloodBottlef.get(AbstractDungeon.player).bloodCount
            addToTop(HealAction(owner, owner, bloodCount))
            flash()
        }
        decrement()
    }

    private fun decrement() {
        val player = AbstractDungeon.player
        val power = player.getPower(ID) ?: return
        if (power.amount <= 0) {
            addToBot(RemoveSpecificPowerAction(owner, owner, ID))
        } else {
            addToBot(ReducePowerAction(owner, owner, power, 1))
        }
    }

}