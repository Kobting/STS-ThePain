package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Defend.NAME, description = "Gain !B! block.", language = Language.ENGLISH)
class Defend : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF) {

    companion object {
        const val NAME = "Defend"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 1
        private const val BLOCK_AMOUNT = 5
        private const val UPGRADE_PLUS_BLOCK = 3
    }

    init {
        setInitialBlock(BLOCK_AMOUNT)
        setTags(CardTags.STARTER_DEFEND)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_BLOCK)
        }
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {
        addToBot(GainBlockAction(player, player, block))
    }

    override fun makeCopy(): AbstractCard {
        return Defend()
    }

}