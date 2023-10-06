package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = LastResort.NAME, description = "Lose all but 1 HP.", language = Language.ENGLISH)
class LastResort : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF) {

    companion object {
        const val NAME = "Last Resort"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 1
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {
        val hpLose = player!!.currentHealth - 1
        addToBot(DamageAction(player, DamageInfo(player, hpLose, DamageInfo.DamageType.HP_LOSS)))
    }

    override fun upgrade() {
        if(!upgraded){
            upgradeName()
            upgradeBaseCost(0)
        }
    }


}