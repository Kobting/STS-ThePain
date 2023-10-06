package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Light.NAME, description = "Unplayable. NL Heal !M! HP when Discarded.", language = Language.ENGLISH)
class Light : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE) {

    companion object {
        const val NAME = "Light"
        const val ID = "$PREFIX:$NAME"

        private const val COST = -2
        private const val HEAL_AMT = 5
        private const val UPGRADE_HEAL_AMT = 2
    }

    init {
        this.magicNumber = HEAL_AMT
        this.baseMagicNumber = this.magicNumber
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_HEAL_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Light()
    }

    override fun canUse(p: AbstractPlayer?, m: AbstractMonster?): Boolean {
        this.cantUseMessage = "Must be Discarded."
        return false
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {}

    override fun triggerOnManualDiscard() {
        AbstractDungeon.player.heal(this.magicNumber)
    }
}
