package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.github.kobting.thepain.powers.BleedPower
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Bleed.NAME, description = "Lost 2 HP at the start of your turn. NL Draw 1 extra card a turn.", language = Language.ENGLISH)
class Bleed : PainCustomCard(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF) {

    companion object {
        const val NAME = "Bleed"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 3
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeBaseCost(2)
        }
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {
        addToBot(ApplyPowerAction(player, player, BleedPower(player)))
    }

    override fun makeCopy(): AbstractCard {
        return Bleed()
    }

}