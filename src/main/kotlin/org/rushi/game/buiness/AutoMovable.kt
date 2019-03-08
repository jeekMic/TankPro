package org.rushi.game.buiness


import org.rushi.game.enums.Direction
import org.rushi.game.model.View
// 自动移动的能力
interface AutoMovable: View {
    val currentDirection: Direction
    val speed:Int
    fun autoMove()
}