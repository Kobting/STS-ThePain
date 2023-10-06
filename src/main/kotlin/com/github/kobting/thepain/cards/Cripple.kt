package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.powers.WeakPower

@Card
@CardString(prefix = PREFIX, name = Cripple.NAME, description = "Apply 2 Weak. NL Heal !M! HP.", language = Language.ENGLISH)
class Cripple : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY) {

    companion object {
        const val NAME = "Cripple"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 2
        private const val HEAL_AMOUNT = 3
        private const val UPGRADE_PLUS_HEAL = 2
    }

    init {
        setInitialMagicNumber(HEAL_AMOUNT)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_PLUS_HEAL)
        }
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {
        addToBot(ApplyPowerAction(player, player, WeakPower(monster, magicNumber, false), magicNumber))
        addToBot(HealAction(player, player, magicNumber))
    }

    override fun makeCopy(): AbstractCard {
        return Cripple()
    }

}