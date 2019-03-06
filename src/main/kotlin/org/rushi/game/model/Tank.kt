package org.rushi.game.model

import org.itheima.kotlin.game.core.Painter
import org.rushi.game.Config
import org.rushi.game.enums.Direction


class Tank(override var x: Int, override var y: Int) :View{
    override var width: Int= Config.block
    override var height: Int = Config.block
//    var xs: Int = this.x
//    var ys: Int = this.y
    val speed:Int = 8
    //方向
    var currentDirection:Direction = Direction.UP

    override fun draw() {
        //根据坦克的方向来进行监测
        when(currentDirection){
            Direction.UP -> Painter.drawImage("img/tank_u.gif",x,y)
            Direction.DOWN -> Painter.drawImage("img/tank_d.gif",x,y)
            Direction.LEFT -> Painter.drawImage("img/tank_l.gif",x,y)
            Direction.RIGHT -> Painter.drawImage("img/tank_r.gif",x,y)
        }
    }
    fun move(direction: Direction){
        this.currentDirection = direction
        // tank 坐标需要发生变化 根据方向来改变坐标
        when(currentDirection){
            Direction.UP -> y -=speed
            Direction.DOWN -> y +=speed
            Direction.LEFT -> x -=speed
            Direction.RIGHT -> x +=speed
        }
        println("${x}----${y}")
    }

}