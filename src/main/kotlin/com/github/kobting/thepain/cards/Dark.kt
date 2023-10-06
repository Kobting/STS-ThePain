package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Dark.NAME, description = "Unplayable. NL Lose !M! HP when Discarded.", language = Language.ENGLISH)
class Dark : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE) {

    companion object {
        const val NAME = "Dark"
        const val ID = "$PREFIX:$NAME"

        private const val COST = -2
        private const val LOSE_HP_AMOUNT = 3
        private const val UPGRADE_LOSE_HP_AMOUNT = 2
    }

    init {
        setInitialMagicNumber(LOSE_HP_AMOUNT)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_LOSE_HP_AMOUNT)
        }
    }

    override fun use(p0: AbstractPlayer?, p1: AbstractMonster?) {
        //Cannot be used
    }

    override fun canUse(p: AbstractPlayer?, m: AbstractMonster?): Boolean {
        return false
    }

    override fun triggerOnManualDiscard() {
        addToBot(DamageAction(AbstractDungeon.player, DamageInfo(AbstractDungeon.player, magicNumber, DamageInfo.DamageType.HP_LOSS)))
    }

    override fun makeCopy(): AbstractCard {
        return Dark()
    }

}