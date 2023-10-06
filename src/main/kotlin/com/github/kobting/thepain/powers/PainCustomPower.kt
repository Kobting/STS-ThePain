package com.github.kobting.thepain.powers

import com.badlogic.gdx.graphics.Texture
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.powers.AbstractPower

abstract class PainCustomPower(id: String, owner: AbstractCreature?, amount: Int = -1, private val image: String) : AbstractPower() {

    private val powerStrings: PowerStrings = CardCrawlGame.languagePack.getPowerStrings(id)

    companion object {
        private val imageMap: MutableMap<String, Texture> = mutableMapOf()
        @JvmStatic
        @SpireEnum
        lateinit var NEUTRAL: AbstractPower.PowerType
    }

    init {
        loadInitialStrings()
        loadImage()

        this.ID = id
        this.owner = owner
        this.amount = amount
    }

    private fun loadInitialStrings() {
        this.name = powerStrings.NAME
        this.description = powerStrings.DESCRIPTIONS[0]
    }

    private fun loadImage() {
        if (imageMap.containsKey(image)) {
            img = imageMap[image]
        } else {
            val newImage = Texture(image)
            imageMap[image] = newImage
            img = newImage
        }
    }

}