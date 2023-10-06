package com.github.kobting.thepain.cards

import com.github.kobting.annotations.Card
import com.github.kobting.annotations.CardString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster

@Card
@CardString(prefix = PREFIX, name = Strike.NAME, description = "Deal !D! damage.", language = Language.ENGLISH)
class Strike : PainCustomCard(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY) {

    companion object {
        const val NAME = "Strike"
        const val ID = "$PREFIX:$NAME"

        private const val COST = 1
        private const val ATTACK_DAMAGE = 6
        private const val UPGRADE_PLUS_DAMAGE = 3
    }

    init {
        setInitialDamage(ATTACK_DAMAGE)
        setTags(CardTags.STARTER_STRIKE)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DAMAGE)
        }
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {
        addToBot(DamageAction(monster, DamageInfo(player, damage, DamageInfo.DamageType.NORMAL)))
    }

    override fun makeCopy(): AbstractCard {
        return Strike()
    }

}