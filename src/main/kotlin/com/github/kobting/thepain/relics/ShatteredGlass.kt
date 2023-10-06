package com.github.kobting.thepain.relics

import com.github.kobting.annotations.RelicString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.github.kobting.thepain.patches.bloodBottlef
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.relics.AbstractRelic

@RelicString(prefix = PREFIX, name = ShatteredGlass.NAME, descriptions = ["Deal damage to all enemies at the end of your turn equal to #b2 times the charges of blood you have."], flavor = "Sharp", language = Language.ENGLISH)
class ShatteredGlass : PainCustomRelic(ID, RelicTier.STARTER, LandingSound.MAGICAL, "com/github/kobting/thepain/images/relics/shattered_glass.png", "com/github/kobting/thepain/images/relics/outline-shattered_glass.png") {

    companion object {
        const val NAME = "Shattered Glass"
        const val ID = "$PREFIX:$NAME"
    }

    override fun makeCopy(): AbstractRelic {
        return ShatteredGlass()
    }

    override fun getUpdatedDescription(): String {
        return DESCRIPTIONS[0]
    }


    override fun onPlayerEndTurn() {
        super.onPlayerEndTurn()
        val bloodCount = bloodBottlef.get(AbstractDungeon.player).bloodCount
        if (bloodCount > 0) {
            for (monster in AbstractDungeon.getCurrRoom().monsters.monsters) {
                monster.damage(DamageInfo(AbstractDungeon.player, bloodCount * 2, DamageInfo.DamageType.THORNS))
            }
        }

    }

}
