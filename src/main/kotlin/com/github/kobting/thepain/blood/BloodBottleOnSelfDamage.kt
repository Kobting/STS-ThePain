package com.github.kobting.thepain.blood

interface BloodBottleOnSelfDamage : BloodBottleSubscriber {

    fun onSelfDamage(damage: Int)

}