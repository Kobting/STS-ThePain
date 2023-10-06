package com.github.kobting.thepain.relics

import com.badlogic.gdx.graphics.Texture
import com.megacrit.cardcrawl.helpers.ImageMaster
import com.megacrit.cardcrawl.relics.AbstractRelic

abstract class PainCustomRelic(private var id: String, tier: RelicTier, landingSound: LandingSound, image: String, outlineImage: String) : AbstractRelic(id, "", tier, landingSound) {

    companion object {
        private val imageMap: MutableMap<String, Texture> = mutableMapOf()
    }

    init {
        val normalImage = loadImage(image)
        this.img = normalImage
        this.largeImg = normalImage
        this.outlineImg = loadImage(outlineImage)
    }

    private fun loadImage(image: String): Texture? {
        return if (imageMap.containsKey(image)) {
            imageMap[image]
        } else {
            val newImage = ImageMaster.loadImage(image)
            newImage.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
            imageMap[image] = newImage
            newImage
        }
    }

    override fun makeCopy(): AbstractRelic {
        try {
            return this::class.java.newInstance() as AbstractRelic
        } catch (exception: IllegalAccessException) {
            throw RuntimeException("Failed to auto-generate makeCopy for relic: $id")
        } catch (exception: InstantiationException) {
            throw RuntimeException("Failed to auto-generate makeCopy for relic: $id")
        }
    }


}