package org.rushi.game.model

import org.itheima.kotlin.game.core.Painter
import org.rushi.game.Config
import org.rushi.game.enums.Direction

//create 函数返回两个值
class Bullet(var direction: Direction,create:(width:Int,height:Int)->Pair<Int,Int>) :View
{
    override var width: Int = 0
    override var height: Int = 0

    override val x: Int
    override val y: Int
    val imagePath:String
    //初始化
    init {
        //先计算宽度和高度
       imagePath = when(direction){
            Direction.UP -> "img/shot_top.gif"
            Direction.DOWN -> "img/shot_bottom.gif"
            Direction.LEFT -> "img/shot_left.gif"
            Direction.RIGHT -> "img/shot_right.gif"
        }
        val size = Painter.size(imagePath)
        width = size[0]
        height = size[1]
        val invoke = create.invoke(width, height)
        x = invoke.first
        y = invoke.second
    }
    //给子弹一个方向
    override fun draw() {
        Painter.drawImage(imagePath,x,y)
    }

}