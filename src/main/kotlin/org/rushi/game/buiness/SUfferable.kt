package org.rushi.game.buiness

import org.rushi.game.model.View


interface SUfferable: View {
    fun notifySuffer(attack:Attack)
    val blood:Int
}