package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Cuts.NAME, description = "Deal !D! Damage. NL Lose !M! HP.", language = Language.ENGLISH)
class Cuts : PainCustomCard(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY) {

    companion object {
        const val NAME = "Cuts"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 0
        private const val DAMAGE_AMOUNT = 6
        private const val UPGRADE_PLUS_DAMAGE = 3
        private const val SELF_DAMAGE_AMOUNT = 1
    }

    init {
        setInitialDamage(DAMAGE_AMOUNT)
        setInitialMagicNumber(SELF_DAMAGE_AMOUNT)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DAMAGE)
        }
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {
        addToBot(DamageAction(monster, DamageInfo(player, damage, DamageInfo.DamageType.NORMAL)))
        addToBot(DamageAction(player, DamageInfo(player, magicNumber, DamageInfo.DamageType.HP_LOSS)))
    }

    override fun makeCopy(): AbstractCard {
        return Cuts()
    }

}