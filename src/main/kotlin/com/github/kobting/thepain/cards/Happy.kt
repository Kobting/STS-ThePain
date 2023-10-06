package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Happy.NAME, description = "Heal !M! HP. NL Add a Sad to the discard pile. NL Exhaust.", upgradeDescription = "Heal !M! HP. NL Add a Sad+ to the discard pile. NL Exhaust.", language = Language.ENGLISH)
class Happy : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF) {

    companion object {
        const val NAME = "Happy"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 1
        private const val HEAL_AMT = 3
        private const val UPGRADE_HEAL_AMT = 2
    }

    init {
        setInitialMagicNumber(HEAL_AMT)
        this.exhaust = true
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDescription()
            upgradeMagicNumber(UPGRADE_HEAL_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Happy()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(HealAction(abstractPlayer, abstractPlayer, this.magicNumber))
        val sad = Sad()
        if (upgraded) sad.upgrade()
        AbstractDungeon.actionManager.addToBottom(MakeTempCardInDiscardAction(sad, 1))
    }

}
