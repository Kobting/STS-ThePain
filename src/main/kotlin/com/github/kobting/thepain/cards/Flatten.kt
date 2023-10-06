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
@CardString(prefix = PREFIX, name = Flatten.NAME, description = "Deal !D! damage. NL Gain !M! X gold of Damage given.", language = Language.ENGLISH)
class Flatten : PainCustomCard(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY) {

    companion object {
        const val NAME = "Flatten"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 1
        private const val ATTACK_DMG = 15
        private const val UPGRADE_PLUS_DMG = 3
        private const val GOLD_MULTIPLIER = 1
        private const val UPGRADE_GOLD_MULTIPLIER = 1
    }

    init {
        this.damage = ATTACK_DMG
        this.baseDamage = this.damage
        this.magicNumber = GOLD_MULTIPLIER
        this.baseMagicNumber = this.magicNumber
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DMG)
            upgradeMagicNumber(UPGRADE_GOLD_MULTIPLIER)
        }
    }


    override fun makeCopy(): AbstractCard {
        return Flatten()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        val monsterHealth = abstractMonster!!.currentHealth
        val action = DamageAction(abstractMonster, DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL))
        addToBot(action)

        //Don't steal more gold than the monster had health left
        val damageApplied = if (monsterHealth > action.amount) action.amount else action.amount - monsterHealth
        AbstractDungeon.player.gainGold(damageApplied * magicNumber)
    }

}
