@file:SpirePatch(clz = AbstractPlayer::class, method = SpirePatch.CLASS)
package com.github.kobting.thepain.patches

import com.evacipated.cardcrawl.modthespire.lib.SpireField
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.github.kobting.thepain.blood.BloodBottle
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.helpers.ImageMaster

private val texture by lazy { ImageMaster.loadImage("com/github/kobting/thepain/images/blood_bottle.png") }
private val bloodBottle by lazy { BloodBottle(texture) }

@JvmField
val bloodBottlef = SpireField { bloodBottle }