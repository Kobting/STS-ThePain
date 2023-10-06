package com.github.kobting.thepain.patches

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch
import com.github.kobting.thepain.actions.ModifyBloodChargesAction
import com.github.kobting.thepain.blood.BloodBottleOnSelfDamage
import com.github.kobting.thepain.cards.PainCustomCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower
import com.megacrit.cardcrawl.rooms.AbstractRoom
import com.megacrit.cardcrawl.rooms.MonsterRoom
import java.lang.ref.WeakReference

class CharacterPatches {

    companion object {
        @JvmStatic
        private val onSelfDamageSubscribers = mutableListOf<WeakReference<BloodBottleOnSelfDamage>>()

        @JvmStatic
        fun subscribeToOnSelfDamage(subscriber: WeakReference<BloodBottleOnSelfDamage>) {
            onSelfDamageSubscribers.add(subscriber)
            clearOldSubscribers()
        }

        @JvmStatic
        fun notifyOnSelfDamage(damage: Int) {
            onSelfDamageSubscribers.forEach {
                it.get()?.onSelfDamage(damage)
            }
        }

        @JvmStatic
        private fun clearOldSubscribers() {
            val temp = onSelfDamageSubscribers.filter { it.get() != null }
            onSelfDamageSubscribers.clear()
            onSelfDamageSubscribers.addAll(temp)
        }

        @JvmStatic
        @SpireEnum
        var THE_PAIN: AbstractPlayer.PlayerClass? = null

        @SpireEnum
        @JvmStatic
        var THE_PAIN_MAROON: AbstractCard.CardColor? = null
    }

    @SpirePatch(
        clz = AbstractPlayer::class,
        method = "renderHand"
    )
    class RenderPatch {
        companion object {
            @JvmStatic
            @SpirePrefixPatch
            fun prefix(instance: AbstractPlayer, sb: SpriteBatch) {
                if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT || AbstractDungeon.getCurrRoom() is MonsterRoom && !instance.isDead) {
                    if (instance.masterDeck.group.any { it is PainCustomCard }) { //TODO: Should probably do this somewhere else as this could be a big check during rendering
                        val color = sb.color
                        sb.color = Color.WHITE
                        bloodBottlef.get(instance).render(sb)
                        sb.color = color
                    }
                }
            }
        }
    }

    @SpirePatch(
        clz = AbstractPlayer::class,
        method = "combatUpdate"
    )
    class CombatUpdatePatch {
        companion object {
            @JvmStatic
            @SpirePrefixPatch
            fun prefix(instance: AbstractPlayer) {
                bloodBottlef.get(instance).update()
            }
        }
    }

    @SpirePatch(
        clz = AbstractPlayer::class,
        method = "damage"
    )
    class DamagePatch {
        companion object {
            @JvmStatic
            @SpirePrefixPatch
            fun prefix(instance: AbstractPlayer, info: DamageInfo) {
                if (info.owner == instance && info.type == DamageInfo.DamageType.HP_LOSS) {
                    val damageAmount = info.output.takeIf { !instance.hasPower(IntangiblePlayerPower.POWER_ID) } ?: 1
                    notifyOnSelfDamage(damageAmount)
                }
            }
        }
    }

    @SpirePatch(
        clz = AbstractPlayer::class,
        method = "onVictory"
    )
    class OnVictoryPatch {
        companion object {
            @JvmStatic
            @SpirePrefixPatch
            fun prefix(instance: AbstractPlayer) {
                with(bloodBottlef.get(instance)) {
                    bloodCount = 0
                    retainTurns = 0
                    ApplyStartOfTurnRelicsPatch.realTurn = 0
                }
            }
        }
    }

    @SpirePatch(
        clz = AbstractPlayer::class,
        method = "applyStartOfTurnRelics"
    )
    class ApplyStartOfTurnRelicsPatch() {
        companion object {
            var realTurn = 0

            @JvmStatic
            @SpirePrefixPatch
            fun prefix(instance: AbstractPlayer) {
                if (bloodBottlef.get(instance).retainTurns > 0) {
                    bloodBottlef.get(instance).retainTurns--
                } else if (realTurn != 1) {
                    AbstractDungeon.actionManager.addToBottom(ModifyBloodChargesAction(0))
                } else {
                    //No-op
                }
            }
        }
    }

}