package com.github.kobting.thepain

import basemod.BaseMod
import basemod.interfaces.EditCharactersSubscriber
import basemod.interfaces.EditKeywordsSubscriber
import basemod.interfaces.EditRelicsSubscriber
import com.badlogic.gdx.graphics.Color
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
import com.github.kobting.annotations.AutoSpireInitializer
import com.github.kobting.thepain.character.CharacterPain
import com.github.kobting.thepain.patches.CharacterPatches.Companion.THE_PAIN
import com.github.kobting.thepain.patches.CharacterPatches.Companion.THE_PAIN_MAROON
import com.github.kobting.thepain.powers.BleedPower
import com.github.kobting.thepain.powers.ProtectionPower
import com.github.kobting.thepain.powers.ReliefPower
import com.github.kobting.thepain.relics.BloodBag
import com.github.kobting.thepain.relics.ShatteredGlass
import com.megacrit.cardcrawl.core.CardCrawlGame

const val PREFIX = "thepain"

@AutoSpireInitializer
@SpireInitializer
class ThePainMod : AutoInitialize(), EditKeywordsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber {

    init {
        BaseMod.subscribe(this)
    }

    companion object {
        private val color = Color(102f, 0f, 0f, 1f)

        @JvmStatic
        fun initialize() {

            BaseMod.addColor(THE_PAIN_MAROON, color, color, color, color, color, color, color,
                "com/github/kobting/thepain/images/ui/bg_attack.png", "com/github/kobting/thepain/images/ui/bg_skill.png",
                "com/github/kobting/thepain/images/ui/bg_power.png", "com/github/kobting/thepain/images/ui/card_orb_p.png",
                "com/github/kobting/thepain/images/ui/bg_attack_p.png", "com/github/kobting/thepain/images/ui/bg_skill_p.png",
                "com/github/kobting/thepain/images/ui/bg_power_p.png", "com/github/kobting/thepain/images/ui/card_orb.png")

            ThePainMod()
        }
    }

    override fun receiveEditKeywords() {
        val protection = arrayOf("Protection", "protection")
        BaseMod.addKeyword(protection, CardCrawlGame.languagePack.getPowerStrings(ProtectionPower.ID).DESCRIPTIONS[0])

        val relief = arrayOf("Relief", "relief")
        BaseMod.addKeyword(relief, CardCrawlGame.languagePack.getPowerStrings(ReliefPower.ID).DESCRIPTIONS[0])

        val bleed = arrayOf("Bleed", "bleed")
        BaseMod.addKeyword(bleed, CardCrawlGame.languagePack.getPowerStrings(BleedPower.ID).DESCRIPTIONS[0])
    }

    override fun receiveEditCharacters() {
        BaseMod.addCharacter(
            CharacterPain("The Pain"),
            "com/github/kobting/thepain/images/characters/select.png",
            "com/github/kobting/thepain/images/characters/portrait.png",
            THE_PAIN)
    }

    override fun receiveEditRelics() {
        BaseMod.addRelicToCustomPool(ShatteredGlass(), THE_PAIN_MAROON)
        BaseMod.addRelicToCustomPool(BloodBag(), THE_PAIN_MAROON)
    }

}