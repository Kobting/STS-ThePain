package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Sad.NAME, description = "Lose !M! HP. NL Add a Happy to the discard pile. NL Exhaust.", upgradeDescription = "Lose !M! HP. NL Add a Happy+ to the discard pile. NL Exhaust.", language = Language.ENGLISH)
class Sad : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF) {

    companion object {
        const val NAME = "Sad"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 0
        private const val SELF_DAMAGE = 3
        private const val UPGRADE_SELF_DAMAGE = 2
    }

    init {
        setInitialMagicNumber(SELF_DAMAGE)
        this.exhaust = true
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDescription()
            upgradeMagicNumber(UPGRADE_SELF_DAMAGE)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Sad()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        addToBot(DamageAction(abstractPlayer, DamageInfo(abstractPlayer, magicNumber, DamageInfo.DamageType.HP_LOSS)))
        val happy = Happy()
        if (upgraded) happy.upgrade()
        addToBot(MakeTempCardInDiscardAction(happy, 1))
    }

}
