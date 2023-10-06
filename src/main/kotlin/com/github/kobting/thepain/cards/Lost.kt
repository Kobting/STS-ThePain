package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.github.kobting.thepain.actions.DiscardHealAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Lost.NAME, description = "Discard up to 3 Cards. NL Heal !M! HP for every Card Discarded.", language = Language.ENGLISH)
class Lost : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF) {

    companion object {
        const val NAME = "Lost"
        const val ID = "thepain:Lost"

        private const val COST = 1
        private const val DISCARD_AMT = 3
        private const val HEAL_AMT = 1
        private const val UPGRADE_HEAL_AMT = 1
    }

    init {
        setInitialMagicNumber(HEAL_AMT)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_HEAL_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Lost()
    }

    override fun use(abstractPlayer: AbstractPlayer, abstractMonster: AbstractMonster?) {
        addToBot(DiscardHealAction(abstractPlayer, abstractPlayer, DISCARD_AMT, true, magicNumber))
    }

}
