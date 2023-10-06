package com.github.kobting.thepain.relics


import com.github.kobting.annotations.RelicString
import com.github.kobting.annotations.data.Language
import com.github.kobting.thepain.PREFIX
import com.github.kobting.thepain.actions.ModifyBloodChargesAction
import com.github.kobting.thepain.patches.bloodBottlef
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

@RelicString(prefix = PREFIX, name = BloodBag.NAME, descriptions = ["Start each combat with #b3 charges of blood"], flavor = "Squishy", language = Language.ENGLISH)
class BloodBag : PainCustomRelic(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, "com/github/kobting/thepain/images/relics/arcanosphere.png", "com/github/kobting/thepain/images/relics/arcanosphere.png") {

    companion object {
        const val NAME = "Blood Bag"
        const val ID = "$PREFIX:$NAME"
    }

    override fun getUpdatedDescription(): String {
        return DESCRIPTIONS[0]
    }

    override fun onEquip() {
        super.onEquip()
        val player = AbstractDungeon.player
        val charges = bloodBottlef.get(player).bloodCount
        AbstractDungeon.actionManager.addToNextCombat(ModifyBloodChargesAction(charges + 3))
    }

    override fun atBattleStart() {
        val player = AbstractDungeon.player
        val charges = bloodBottlef.get(player).bloodCount
        AbstractDungeon.actionManager.addToTop(ModifyBloodChargesAction(charges + 3))
    }
}