package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.DrawCardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Sacrifice.NAME, description = "Lose 2 HP. NL Draw !M! Cards", language = Language.ENGLISH)
class Sacrifice : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF) {

    companion object {
        const val NAME = "Sacrifice"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 1
        private const val SELF_DMG = 2
        private const val CARD_DRAW_AMT = 2
        private const val UPGRADE_CARD_DRAW_AMT = 1
    }

    init {
        setInitialMagicNumber(CARD_DRAW_AMT)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_CARD_DRAW_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Sacrifice()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        addToBot(DamageAction(abstractPlayer, DamageInfo(abstractPlayer, SELF_DMG, DamageInfo.DamageType.HP_LOSS)))
        addToBot(DrawCardAction(abstractMonster, magicNumber))
    }
}
