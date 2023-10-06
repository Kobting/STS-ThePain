package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = CutDry.NAME, description = "Lose 2 HP. NL Heal !M! HP.", language = Language.ENGLISH)
class CutDry : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF) {

    companion object {
        const val NAME = "Cut and Dry"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 1
        private const val SELF_DAMAGE_AMOUNT = 2
        private const val HEAL_AMOUNT = 3
        private const val UPGRADE_PLUS_HEAL = 1
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
        addToBot(DamageAction(player, DamageInfo(player, SELF_DAMAGE_AMOUNT, DamageInfo.DamageType.HP_LOSS)))
        addToBot(HealAction(player, player, magicNumber))
    }

    override fun makeCopy(): AbstractCard {
        return CutDry()
    }

}