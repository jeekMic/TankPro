package org.rushi.game.buiness

import org.rushi.game.enums.Direction
import org.rushi.game.model.View

interface  Movable: View {
    /**
     * 可移动物体的方向
     */
    val currentDirection:Direction
    /**
     * 移动物体的速度
     */
    val speed:Int

    /**
     * 判断移动的物体是否和阻塞物体发生碰撞
     * @return 方向
     * null 表示没有碰撞
     */
    fun willCollision(block:Blockable):Direction?

    fun notifyBlockCollision(direction: Direction?,block: Blockable?)
}