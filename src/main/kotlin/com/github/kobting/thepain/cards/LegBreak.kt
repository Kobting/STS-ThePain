package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.powers.WeakPower

@Card
@CardString(prefix = PREFIX, name = LegBreak.NAME, description = "Apply !M! Weak to all enemies", language = Language.ENGLISH)
class LegBreak : PainCustomCard(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY) {

    companion object {
        const val NAME = "Leg Break"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 2
        private const val WEAK_AMT = 3
        private const val UPGRADE_WEAK_AMT = 2
    }

    init {
        setInitialMagicNumber(WEAK_AMT)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_WEAK_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return LegBreak()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        for (monster in AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(ApplyPowerAction(monster, abstractMonster, WeakPower(monster, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE))
        }
    }
}
