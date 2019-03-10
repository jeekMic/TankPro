package org.rushi.game.model

import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import org.rushi.game.Config
import org.rushi.game.buiness.*
import org.rushi.game.enums.Direction


class Tank(override var x: Int, override var y: Int) :Movable,Blockable,SUfferable,Destroyable{


    override var blood: Int = 1
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
        when(currentDirection){
            Direction.UP -> y -=speed
            Direction.DOWN -> y +=speed
            Direction.LEFT -> x -=speed
            Direction.RIGHT -> x +=speed
        }

        //将要碰撞左判断
        var collision = checkColision(block.x, block.y,block.width,block.height,x,y,width,height)
        return if (collision) currentDirection else null

    }
    override fun notifyBlockCollision(direction: Direction?, block: Blockable?) {
        //TODO("not implemented") 接收碰撞信息
        this.badDirection = direction
    }
    fun shot():Bullet{

        //计算坦克真实的坐标
        return Bullet(this,currentDirection) { bulletWidth, bulletHeight->
            val tankX = x
            val tankY = y
            val tankWidth = width
            val tankHeight = height
            var bulletX = 0
            var bulletY = 0
            when(currentDirection){
                Direction.UP->{
                    bulletX = tankX+(tankWidth-bulletWidth)/2
                    bulletY = tankY - bulletHeight/2
                }
                Direction.DOWN->{
                    bulletX = tankX+(tankWidth-bulletWidth)/2
                    bulletY = tankY +tankHeight -bulletHeight/2
                }
                Direction.LEFT->{
                    bulletX = tankX-bulletWidth/2
                    bulletY = tankY+(tankHeight-bulletHeight)/2
                }
                Direction.RIGHT->{
                    bulletX = tankX+tankWidth- bulletWidth/2
                    bulletY = tankY+(tankHeight-bulletHeight)/2
                }

            }
            Pair(bulletX,bulletY)
        }
    }
    override fun notifySuffer(attack: Attack): Array<View>? {
        blood -=attack.attaclPower
        return arrayOf(Blast(x,y))
    }
    override fun isDestory(): Boolean {
        return  blood<=0
    }
}