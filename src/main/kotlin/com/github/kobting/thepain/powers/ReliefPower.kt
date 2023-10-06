package com.github.kobting.thepain.powers

import com.github.kobting.annotations.PowerString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

@PowerString(prefix = PREFIX, name = ReliefPower.NAME, descriptions = ["Lose #b2 HP at the start of your turn. NL Heal #b2 HP at the end of your turn."], language = Language.ENGLISH)
class ReliefPower(owner: AbstractCreature, amount: Int) : PainCustomPower(ID, owner, amount, "com/github/kobting/thepain/images/powers/protection.png"){

    companion object {
        const val NAME = "Relief"
        const val ID = "$PREFIX:$NAME"
    }

    init {
        this.type = PowerType.BUFF
        this.isTurnBased = false
    }

    override fun atEndOfTurn(isPlayer: Boolean) {
        val player = AbstractDungeon.player
        addToBot(HealAction(player, player, 2))
    }

    override fun atStartOfTurn() {
        val player = AbstractDungeon.player
        addToBot(DamageAction(player, DamageInfo(player, 2, DamageInfo.DamageType.HP_LOSS)))
    }
}