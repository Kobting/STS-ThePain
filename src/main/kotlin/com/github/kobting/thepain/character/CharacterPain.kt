package com.github.kobting.thepain.character

import basemod.abstracts.CustomPlayer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.github.kobting.thepain.cards.CutDry
import com.github.kobting.thepain.cards.Defend
import com.github.kobting.thepain.cards.Strike
import com.github.kobting.thepain.patches.CharacterPatches.Companion.THE_PAIN
import com.github.kobting.thepain.patches.CharacterPatches.Companion.THE_PAIN_MAROON
import com.github.kobting.thepain.relics.ShatteredGlass
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.EnergyManager
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.helpers.FontHelper
import com.megacrit.cardcrawl.screens.CharSelectInfo

class CharacterPain(name: String) : CustomPlayer(name, THE_PAIN, orbs, orbVfx, null as String?, null as String?) {


    companion object {

        private val orbs = arrayOf(
                "com/github/kobting/thepain/images/characters/orb/layer1.png",
                "com/github/kobting/thepain/images/characters/orb/layer2.png",
                "com/github/kobting/thepain/images/characters/orb/layer3.png",
                "com/github/kobting/thepain/images/characters/orb/layer4.png",
                "com/github/kobting/thepain/images/characters/orb/layer5.png",
                "com/github/kobting/thepain/images/characters/orb/layer6.png",
                "com/github/kobting/thepain/images/characters/orb/layer1d.png",
                "com/github/kobting/thepain/images/characters/orb/layer2d.png",
                "com/github/kobting/thepain/images/characters/orb/layer3d.png",
                "com/github/kobting/thepain/images/characters/orb/layer4d.png",
                "com/github/kobting/thepain/images/characters/orb/layer5d.png")

        private const val orbVfx = "com/github/kobting/thepain/images/characters/orb/vfx.png"

    }
    

    init {

        this.dialogX = this.drawX + 0.0f * Settings.scale
        this.dialogY = this.drawY + 220.0f * Settings.scale

        initializeClass(null, "images/characters/ironclad/shoulder2.png", "images/characters/ironclad/shoulder.png", "images/characters/ironclad/corpse.png",
                loadout, 20.0f, -10.0f, 220.0f, 290.0f, EnergyManager(3))

        loadAnimation("images/characters/ironclad/idle/skeleton.atlas", "images/characters/ironclad/idle/skeleton.json", 1.0f)

//        val e = this.state.setAnimation(0, "animation", true)
//        e.time = e.endTime * MathUtils.random()

        initializeStarterRelics(this.chosenClass)


    }

    override fun getStartingRelics(): ArrayList<String> {
        val relics = ArrayList<String>()

        relics.add(ShatteredGlass.ID)

        return relics
    }

    override fun getLoadout(): CharSelectInfo {
        return CharSelectInfo(
                "The Pain",
                "Use HP as a resource for killing",
                80,
                80,
                0,
                99,
                5,
                this,
                startingRelics,
                startingDeck,
                false)    }

    override fun getCardColor(): AbstractCard.CardColor {
        return THE_PAIN_MAROON!!
    }

    override fun getEnergyNumFont(): BitmapFont {
        return FontHelper.energyNumFontRed
    }

    override fun getAscensionMaxHPLoss(): Int {
        return 7
    }

    override fun getCustomModeCharacterButtonSoundKey(): String {
        return "ATTACK_FIRE"
    }

    override fun getStartingDeck(): ArrayList<String> {
        val deck = ArrayList<String>()

        deck.add(Strike.ID)
        deck.add(Strike.ID)
        deck.add(Strike.ID)
        deck.add(Strike.ID)
        deck.add(Strike.ID)
        deck.add(Defend.ID)
        deck.add(Defend.ID)
        deck.add(Defend.ID)
        deck.add(Defend.ID)
        deck.add(Defend.ID)
        deck.add(CutDry.ID)


        return deck
    }

    override fun doCharSelectScreenSelectEffect() {
    }

    override fun getStartCardForEvent(): AbstractCard {
        return CutDry()
    }

    override fun getTitle(playerClass: PlayerClass?): String {
        return "The Pain"
    }

    override fun newInstance(): AbstractPlayer {
        return CharacterPain("The Pain");
    }

    override fun getLocalizedCharacterName(): String {
        return "The Pain"
    }

    override fun getCardTrailColor(): Color {
        return Color.MAROON
    }

    override fun getCardRenderColor(): Color {
        return Color.MAROON
    }

    override fun getSlashAttackColor(): Color {
        return Color.MAROON
    }

    override fun getSpireHeartText(): String {
        return "Bleed! Bleed! Bleeeeeeed!!!"
    }

    override fun getSpireHeartSlashEffect(): Array<AbstractGameAction.AttackEffect> {
        return Array(2){
            AbstractGameAction.AttackEffect.FIRE
            AbstractGameAction.AttackEffect.BLUNT_LIGHT
        }
    }

    override fun getVampireText(): String {
        return "Umm idk?"
    }

}
