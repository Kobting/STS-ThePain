package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.github.kobting.thepain.powers.ProtectionPower
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = DeepCut.NAME, description = "Deal !D! Damage. NL Gain 1 Protection", language = Language.ENGLISH)
class DeepCut : PainCustomCard(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.SELF_AND_ENEMY) {

    companion object {

        const val NAME = "Deep Cut"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 2
        private const val DAMAGE_AMOUNT = 10
        private const val UPGRADE_PLUS_DAMAGE = 5
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

    override fun makeCopy(): AbstractCard {
        return DeepCut()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        addToBot(DamageAction(abstractMonster, DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)))
        addToBot(ApplyPowerAction(abstractPlayer, abstractPlayer, ProtectionPower(abstractPlayer as AbstractCreature, 1), 1))
    }

}
