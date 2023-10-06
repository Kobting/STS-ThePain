package com.github.kobting.thepain.powers

import com.github.kobting.annotations.PowerString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.DrawCardAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature

@PowerString(prefix = PREFIX, name = BleedPower.NAME, descriptions = ["Lose #b2 HP at the start of your turn. NL Draw #b1 extra card a turn."], language = Language.ENGLISH)
class BleedPower(owner: AbstractCreature?) : PainCustomPower(ID, owner, image = "com/github/kobting/thepain/images/powers/blood.png") {

    companion object {
        const val NAME = "Bleed"
        const val ID = "$PREFIX:$NAME"
    }

    init {
        type = NEUTRAL
        isTurnBased = true
    }

    override fun atStartOfTurn() {
        super.atStartOfTurn()
        addToBot(DamageAction(owner, DamageInfo(owner, 2, DamageInfo.DamageType.HP_LOSS)))
        addToBot(DrawCardAction(owner, 1))
    }

}