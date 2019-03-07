package org.rushi.game.model

import org.itheima.kotlin.game.core.Painter
import org.rushi.game.Config
import org.rushi.game.buiness.Blockable
import org.rushi.game.buiness.Movable
import org.rushi.game.enums.Direction


class Tank(override var x: Int, override var y: Int) :Movable{


    override var width: Int= Config.block
    override var height: Int = Config.block
//    var xs: Int = this.x
//    var ys: Int = this.y
    override val speed:Int = 8
    private var badDirection:Direction? = null
    //方向
    override var currentDirection:Direction = Direction.UP

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
        //判断是否是往要碰撞的地方走
        if (direction==badDirection){
            return
        }
        //点击一下方向的时候不移动只变方向
        if (direction != this.currentDirection) {
            this.currentDirection = direction
            return
        }
        this.currentDirection = direction
        // tank 坐标需要发生变化 根据方向来改变坐标
        when(currentDirection){
            Direction.UP -> y -=speed
            Direction.DOWN -> y +=speed
            Direction.LEFT -> x -=speed
            Direction.RIGHT -> x +=speed
        }
        //越界判断
        if(x<0) x= 0
        if(x> Config.gameWidth-width) x = Config.gameWidth-width

        if (y<0) y = 0
        if(y> Config.gameHeight-height) y = Config.gameHeight-height
    }
    override fun willCollision(block: Blockable): Direction? {
        var x = this.x
        var y = this.y
        //将要碰撞左判断

        //TODO("not implemented") 监测下一步碰撞
        var collision = when {
            block.y+block.height<=y -> //如果阻挡物在运动物的上方，则不会放生碰撞
            {
                println("1")
                println(x)
                println(y)
                println(block.x)
                println(block.y)
                println(block.height)
                println(currentDirection)
                false
            }

            block.y>=y+height -> //如果阻挡物在运动物的下方，则不会放生碰撞
            {
                println("2")
                println( "${block.y}----${y+height}")
                false
            }
            block.x+block.width<=x -> //如果阻挡物在运动物的左方，则不会放生碰撞
            {
                println("3")
                println( "${block.x+block.width}----${x}")
                false
            }
            block.x >= x+width -> //如果阻挡物在运动物的右方，则不会放生碰撞
            {
                println("4")
                println( "${ block.x+block.width}----${x+width}")
                false
            }
            else -> {
                println(x)
                println(y)
                println(block.x)
                println(block.y)
                println(block.height)
                println(currentDirection)
                true
            }
        }
        return if (collision) currentDirection else null

    }
    override fun notifyBlockCollision(direction: Direction?, block: Blockable?) {
        //TODO("not implemented") 接收碰撞信息
        this.badDirection = direction
    }
    fun shot():Bullet{

        //计算坦克真实的坐标
        return Bullet(currentDirection,{bulletWidth,bulletHeight->
            val tankX = x
            val tankY = y
            val tankWidth = width
            val tankHeight = height
            var bulletX = 0
            var bulletY = 0
//            var bulletWidth = 16
//            var bulletHeight = 32
            when(currentDirection){
                Direction.UP->{
                    bulletX = tankX+(tankWidth-bulletWidth)/2
                    bulletY = tankY - bulletHeight/2
                }
                Direction.DOWN->{
                    bulletX = tankX+(tankWidth-bulletWidth)/2
                    bulletY = tankY +tankHeight
                }
                Direction.LEFT->{
                    bulletX = tankX-bulletWidth/2
                    bulletY = tankY+(tankHeight-bulletHeight)/2
                }
                Direction.RIGHT->{
                    bulletX = tankX+tankWidth
                    bulletY = tankY+(tankHeight-bulletHeight)/2
                }

            }
            Pair(bulletX,bulletY)
        })
    }
}