package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Bandaid.NAME, description = "Gain !B! Block. NL Heal !M! HP.", language = Language.ENGLISH)
class Bandaid : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF) {

    companion object {
        const val NAME = "Bandaid"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 1
        private const val BLOCK_AMOUNT = 8
        private const val UPGRADE_PLUS_BLOCK = 2
        private const val HEAL_AMOUNT = 2
        private const val UPGRADE_PLUS_HEAL = 1
    }

    init {
        setInitialBlock(BLOCK_AMOUNT)
        setInitialMagicNumber(HEAL_AMOUNT)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeBlock(UPGRADE_PLUS_BLOCK)
            upgradeMagicNumber(UPGRADE_PLUS_HEAL)
        }
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {
        addToBot(GainBlockAction(player, player, block))
        addToBot(HealAction(player, player, magicNumber))
    }

    override fun makeCopy(): AbstractCard {
        return Bandaid()
    }

}