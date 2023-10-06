package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.github.kobting.thepain.powers.ReliefPower
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Relief.NAME, description = "Lose 2 HP at the start of your turn. NL Heal 2 HP at the end of your turn.", language = Language.ENGLISH)
class Relief : PainCustomCard(ID, COST,CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF) {

    companion object {

        const val NAME = "Relief"
        const val ID = "$PREFIX:$NAME"
        private const val COST = 2
    }

    override fun use(abstractPlayer: AbstractPlayer, abstractMonster: AbstractMonster?) {
        addToBot(ApplyPowerAction(abstractPlayer, abstractPlayer, ReliefPower(abstractPlayer, 0)))
    }

    override fun upgrade() {
        if(!upgraded) {
            this.upgradeName()
            this.upgradeBaseCost(1)
        }
    }


}