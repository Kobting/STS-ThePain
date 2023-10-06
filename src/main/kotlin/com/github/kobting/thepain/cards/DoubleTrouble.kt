package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.github.kobting.thepain.patches.bloodBottlef
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = DoubleTrouble.NAME, description = "Double your charges of blood. NL Cannot be upgraded.", language = Language.ENGLISH)
class DoubleTrouble : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF) {

    companion object {

        const val NAME = "Double Trouble"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 1
        private const val DOUBLE_AMT = 2
    }

    init {
        setInitialMagicNumber(DOUBLE_AMT)
    }
    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        bloodBottlef.get(abstractPlayer).bloodCount *= 2
    }

    override fun canUpgrade(): Boolean {
        return false
    }

    override fun upgrade() { /* No upgrade */ }


}