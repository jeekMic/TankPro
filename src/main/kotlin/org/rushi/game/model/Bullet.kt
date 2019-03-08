package org.rushi.game.model

import org.itheima.kotlin.game.core.Painter
import org.rushi.game.Config
import org.rushi.game.buiness.Attack
import org.rushi.game.buiness.AutoMovable
import org.rushi.game.buiness.Destroyable
import org.rushi.game.buiness.SUfferable
import org.rushi.game.enums.Direction
import org.rushi.game.ext.checkColision

//create 函数返回两个值
class Bullet(override  var currentDirection:
             Direction,create:(width:Int,height:Int)->Pair<Int,Int>) :
    View, AutoMovable,Destroyable,Attack
{
    override var attaclPower: Int = 1
    override var width: Int = 0
    override var speed: Int = 8
    override var height: Int = 0

    override var x: Int = 0
    override var y: Int = 0
    private var isDestrory:Boolean = false
    val imagePath:String
    //初始化
    init {
        //先计算宽度和高度
       imagePath = when(currentDirection){
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
    override fun autoMove() {
        when(currentDirection){
            Direction.UP   -> y -=speed
            Direction.DOWN -> y +=speed
            Direction.LEFT -> x -=speed
            Direction.RIGHT -> x +=speed
        }


    }
    //判断是否销毁了
    override fun isDestory(): Boolean {
        if(isDestrory) return true
        //子弹在脱离屏幕后需要被销毁
        if (x<-width){
            return true
        }
        if (x>width+Config.gameWidth)
        {
            return true
        }
        if (y<-height){
            return true
        }
        if (y>Config.gameHeight+height){
            return true
        }
        return false
    }
    override fun isCollosion(sufferable: SUfferable): Boolean {
        return checkColision(sufferable)
    }

    override fun notifyAttack(sufferable: SUfferable) {
        println("子弹接收到碰撞....")
        isDestrory = true
    }
}