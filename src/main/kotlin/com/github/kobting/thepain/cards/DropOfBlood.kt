package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = DropOfBlood.NAME, description = "Heal !M! HP. NL Ethereal. NL Exhaust.", language = Language.ENGLISH)
class DropOfBlood : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF) {

    companion object {

        const val NAME = "Drop of Blood"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 2
        private const val HEAL_AMT = 15
        private const val UPGRADE_HEAL_AMT = 5
    }

    init {
        setInitialMagicNumber(HEAL_AMT)
        this.exhaust = true
        this.isEthereal = true
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_HEAL_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return DropOfBlood()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        addToBot(HealAction(abstractPlayer, abstractPlayer, magicNumber))
    }

}
