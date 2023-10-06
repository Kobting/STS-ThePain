package com.github.kobting.thepain.actions

import com.github.kobting.thepain.patches.bloodBottlef
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class ModifyBloodChargesAction(amount: Int) : AbstractGameAction() {

    companion object {
        private const val DURATION = 0.0f
    }

    init {
        this.amount = amount
        this.duration = DURATION
    }

    override fun update() {
        if (duration == DURATION) {
            bloodBottlef.get(AbstractDungeon.player).bloodCount = this.amount
        }
        tickDuration()
    }

}