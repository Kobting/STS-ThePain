package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.GainEnergyAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Gulp.NAME, description = "Lose !M! HP. NL Gain [R]", upgradeDescription = "Lose !M! HP. NL Gain [R] [R]", language = Language.ENGLISH)
class Gulp : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF) {

    companion object {

        const val NAME = "Gulp"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 0
        private const val SELF_DAMAGE = 5
        private const val UPGRADE_SELF_DAMAGE = 3
    }

    init {
        setInitialMagicNumber(SELF_DAMAGE)
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {
        addToBot(DamageAction(player, DamageInfo(player, magicNumber, DamageInfo.DamageType.HP_LOSS)))
        addToBot(GainEnergyAction(if(upgraded) 2 else 1))
    }

    override fun upgrade() {
        if(!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_SELF_DAMAGE)
            upgradeDescription()
        }
    }

    override fun makeCopy(): AbstractCard {
        return Gulp()
    }
}