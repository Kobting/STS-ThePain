package com.github.kobting.thepain.blood

import basemod.ClickableUIElement
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.kobting.thepain.patches.CharacterPatches.Companion.subscribeToOnSelfDamage
import com.github.kobting.thepain.patches.bloodBottlef
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.helpers.FontHelper
import com.megacrit.cardcrawl.helpers.TipHelper
import com.megacrit.cardcrawl.helpers.input.InputHelper
import java.lang.ref.WeakReference

class BloodBottle(texture: Texture) : ClickableUIElement(texture, 0f, 0f, 128f, 128f), BloodBottleOnSelfDamage {

    private val xPos = 0f * Settings.scale
    private val yPos = 128f * Settings.scale
    private val textXPos = 64f * Settings.scale
    private val textYPos = 190f * Settings.scale
    private val tipHeader = "Blood Bottle"
    private val tipText = "Every time you take #bself HP damage gain charges equal to that amount."
    var bloodCount: Int = 0
    var retainTurns: Int = 0

    init {
        subscribeToOnSelfDamage(WeakReference(this))
        this.setX(xPos)
        this.setY(yPos)
    }

    override fun onSelfDamage(damage: Int) {
        bloodCount+=damage
    }

    override fun onHover() {
        TipHelper.renderGenericTip(50.0f * Settings.scale, 380.0f * Settings.scale, tipHeader, tipText)
    }

    override fun onUnhover() { /* No-op */ }

    override fun onClick() {
        if (Settings.isDebug) {
            bloodBottlef.get(AbstractDungeon.player).bloodCount++
        }
    }

    override fun update() {
        super.update()
        if (Settings.isDebug && hitbox.hovered && InputHelper.justClickedRight) {
            bloodBottlef.get(AbstractDungeon.player).bloodCount--
        }
    }

    override fun render(sb: SpriteBatch?) {
        super.render(sb)
        FontHelper.renderFontCentered(sb, FontHelper.energyNumFontRed, bloodCount.toString(), textXPos, textYPos, Color.WHITE)
    }


}