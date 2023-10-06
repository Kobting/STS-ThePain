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
@CardString(prefix = PREFIX, name = RepairBoot.NAME, description = "Deal !D! damage. NL Heal !M! HP", language = Language.ENGLISH)
class RepairBoot : PainCustomCard(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY) {

    companion object {
        const val NAME = "Repair Boot"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 2
        private const val ATTACK_DMG = 15
        private const val UPGRADE_PLUS_DMG = 5
        private const val HEAL_AMT = 5
    }

    init {
        setInitialDamage(ATTACK_DMG)
        setInitialMagicNumber(HEAL_AMT)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DMG)
        }
    }

    override fun makeCopy(): AbstractCard {
        return RepairBoot()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        addToBot(DamageAction(abstractMonster, DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)))
        addToBot(HealAction(abstractPlayer, abstractPlayer, magicNumber))
    }
}
