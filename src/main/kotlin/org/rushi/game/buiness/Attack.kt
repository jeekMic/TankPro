package org.rushi.game.buiness

import org.rushi.game.model.View

//具备攻击的能力
interface Attack: View {
    val attaclPower:Int
    //判断是否发生碰撞
    fun isCollosion(sufferable:SUfferable):Boolean
    fun notifyAttack(sufferable:SUfferable)
}