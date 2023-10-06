package com.github.kobting.thepain.cards

import basemod.abstracts.CustomCard
import com.github.kobting.thepain.patches.CharacterPatches.Companion.THE_PAIN_MAROON
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings

abstract class PainCustomCard(
    id: String,
    cost: Int,
    type: CardType,
    rarity: CardRarity,
    target: CardTarget,
    color: CardColor = THE_PAIN_MAROON!!,
    image: String = "com/github/kobting/thepain/images/cards/strike_purple.png"
) : CustomCard(id, "", image, cost, "", type, color, rarity, target) {

    private val cardStrings: CardStrings = CardCrawlGame.languagePack.getCardStrings(id)

    init {
        loadInitialStrings()
    }

    private fun loadInitialStrings() {
        this.name = cardStrings.NAME ?: "Error: No Name: ${this::class.java.simpleName}"
        this.initializeTitle()

        this.rawDescription =
            cardStrings.DESCRIPTION ?: "Error: No Description. NL OOPS! Add this card to card strings."
        this.initializeDescription()
    }

    fun setInitialDamage(damage: Int) {
        baseDamage = damage
        this.damage = baseDamage
    }

    fun setInitialBlock(block: Int) {
        baseBlock = block
        this.block = block
    }

    fun setInitialMagicNumber(magicNumber: Int) {
        baseMagicNumber = magicNumber
        this.magicNumber = magicNumber
    }

    fun setTags(vararg tags: CardTags) {
        this.tags.addAll(tags)
    }

    fun upgradeDescription() {
        val newDescription = this.cardStrings.UPGRADE_DESCRIPTION ?: ""
        if (newDescription != "") {
            this.rawDescription = newDescription
            this.initializeDescription()
        }
    }

}