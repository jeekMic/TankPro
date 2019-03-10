package org.rushi.game.model

import org.itheima.kotlin.game.core.Painter
import org.rushi.game.Config
import org.rushi.game.buiness.*
import org.rushi.game.enums.Direction
import kotlin.random.Random

/**
 * 地方坦克是可以移动的 地方坦克可以自动的移动
 */
class Enemy(override var x: Int, override var y: Int) :AutoMovable,Movable,Blockable, AutoShot , SUfferable ,Destroyable{



    override var blood: Int = 5
    override var currentDirection: Direction = Direction.DOWN
    override val speed: Int = 3
    private var lastShotTime = 0L
    private var shotFrequency = 5000

    override var width: Int = Config.block
    override var height: Int = Config.block
    private var badDirection:Direction? = null
    override fun draw() {
        //根据坦克的方向来进行监测
        when(currentDirection){
            Direction.UP -> Painter.drawImage("img/enemy_1_u.gif",x,y)
            Direction.DOWN -> Painter.drawImage("img/enemy_1_d.gif",x,y)
            Direction.LEFT -> Painter.drawImage("img/enemy_1_l.gif",x,y)
            Direction.RIGHT -> Painter.drawImage("img/enemy_1_r.gif",x,y)
        }
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
        //和边界检测
        //越界判断
        if(x<0) return Direction.LEFT
        if(x>Config.gameWidth-width) return Direction.RIGHT

        if (y<0) return Direction.UP
        if(y>Config.gameHeight-height) return Direction.DOWN
        //将要碰撞做判断
        var collision = checkColision(block.x, block.y,block.width,block.height,x,y,width,height)
        return if (collision) currentDirection else null
    }

    override fun notifyBlockCollision(direction: Direction?, block: Blockable?) {
        badDirection = direction
    }
    override fun autoMove() {
        println("${x}--${y}")
        println(currentDirection)
        println(badDirection)
        if (currentDirection ==badDirection){
            currentDirection = rdmDireciton(badDirection)
            return
        }
        //越界判断
        if(x<0) x= 0
        if(x> Config.gameWidth-width) x = Config.gameWidth-width

        if (y<0) y = 0
        if(y>= Config.gameHeight-height) y = Config.gameHeight-height
        //自动动起来，避开阻碍物
        //根据坦克的方向来进行监测
        when(currentDirection){
            Direction.UP -> y -=speed
            Direction.DOWN -> y +=speed
            Direction.LEFT -> x -=speed
            Direction.RIGHT -> x +=speed
        }
    }

    private  fun rdmDireciton(bad:Direction?):Direction{
        val i = Random.nextInt(4)
        println(i)
       var direction=  when(i){
            0 ->Direction.UP
            1 -> Direction.DOWN
            2 -> Direction.RIGHT
            3 -> Direction.LEFT
           else->Direction.UP
        }
        if (bad == direction){
            return rdmDireciton(bad)
        }
        return direction
    }
    override fun autoShot(): View? {
        var current = System.currentTimeMillis()
        if(current-lastShotTime<shotFrequency) return null
        lastShotTime = current
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

        //敌人互打不掉血
        if (attack.source is Enemy) return null
        blood -=attack.attaclPower
        return arrayOf(Blast(x,y))
    }
    override fun isDestory(): Boolean {
        return  blood<=0
    }
}