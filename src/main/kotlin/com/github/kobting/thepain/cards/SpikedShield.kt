package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = SpikedShield.NAME, description = "Gain !B! Block. NL Take !M! Damage.", language = Language.ENGLISH)
class SpikedShield : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF) {

    companion object {
        const val NAME = "Spiked Shield"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 1
        private const val BLOCK_AMT = 10
        private const val UPGRADE_PLUS_BLOCK = 2
        private const val SELF_DMG_AMT = 2
        private const val UPGRADE_SELF_DMG_ATM = 2
    }

    init {
        setInitialBlock(BLOCK_AMT)
        setInitialMagicNumber(SELF_DMG_AMT)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeBlock(UPGRADE_PLUS_BLOCK)
            upgradeMagicNumber(UPGRADE_SELF_DMG_ATM)
        }
    }

    override fun makeCopy(): AbstractCard {
        return SpikedShield()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        addToBot(DamageAction(abstractPlayer, DamageInfo(abstractPlayer, magicNumber, DamageInfo.DamageType.HP_LOSS)))
        addToBot(GainBlockAction(abstractPlayer, abstractPlayer, block))
    }
}
