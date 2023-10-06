package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.github.kobting.thepain.patches.bloodBottlef
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = BloodySkewer.NAME, description = "Deal !D! damage for each charge of blood you have.", language = Language.ENGLISH)
class BloodySkewer : PainCustomCard(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY) {

    companion object {
        const val NAME = "BloodySkewer"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 2
        private const val DAMAGE_AMOUNT = 3
        private const val UPGRADE_PLUS_DAMAGE = 2
    }

    init {
        setInitialDamage(DAMAGE_AMOUNT)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DAMAGE)
        }
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {
        val lostHp = bloodBottlef.get(player).bloodCount
        for (i in 0 until lostHp) {
            addToBot(DamageAction(monster, DamageInfo(player, damage, DamageInfo.DamageType.NORMAL)))
        }
    }

    override fun makeCopy(): AbstractCard {
        return BloodySkewer()
    }

}