package org.rushi.game

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Painter
import org.itheima.kotlin.game.core.Window
import org.rushi.game.buiness.*
import org.rushi.game.enums.Direction
import org.rushi.game.model.*
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList


class GameWindow:Window(title="标哥坦克大战",icon = "img/gamel.png",height = Config.gameHeight, width = Config.gameWidth){

//    var wall = Wall()
//    var grass = Grass()
    var views = CopyOnWriteArrayList<View>()
    // lateinit 是单例的模式，只在初始化的时候创建 如Android的onCreate方法中
    lateinit var myTank: Tank
    override fun onCreate() {
    //地图
    val file = File(javaClass.getResource("/map/1.map").path)
    val readlines = file.readLines()
    var lineNum =0
    readlines.forEach {
        var columnNum = 0
        //it表示一行
        it.toCharArray().forEach {column->
//            println(lineNum)
            when(column){
                '砖' ->
                {
                    println("${columnNum * Config.block}--${lineNum * Config.block}")
                    views.add(Wall(columnNum * Config.block, lineNum * Config.block))
                }
                '铁' -> views.add(Steel(columnNum * Config.block, lineNum * Config.block))
                '水' -> views.add(Water(columnNum * Config.block, lineNum * Config.block))
                '草' -> views.add(Grass(columnNum * Config.block, lineNum * Config.block))
            }
            columnNum ++
        }
        lineNum ++
    }
       myTank = Tank(Config.block * 10, Config.block * 12)
        views.add(myTank)
    }

    override fun onDisplay() {
        views.forEach {
            it.draw()
        }
    }

    override fun onKeyPressed(event: KeyEvent) {
        println(event.code)
        //用户操作时调用这个生命周期的方法
        when(event.code){
            KeyCode.W ->myTank.move(Direction.UP)
            KeyCode.S -> myTank.move(Direction.DOWN)
            KeyCode.A -> myTank.move(Direction.LEFT)
            KeyCode.D -> myTank.move(Direction.RIGHT)
            KeyCode.ENTER ->{
               val bullet= myTank.shot()
                //拿到一个子弹，交给views
                views.add(bullet)
            }
            else -> println("请不要乱按")
        }
    }

    override fun onRefresh() {
        //耗时业务逻辑 判断运动的物体和阻塞的物体是否发生碰撞
        //1 找到运动的物体
        views.filter {it is Movable }.forEach {move->
                move as Movable
                var badDirection:Direction? = null
                var badBlockable:Blockable? = null
                views.filter {mv -> mv is Blockable }.forEach blackTag@{block->
                    block as Blockable
//                    println("----------${block.y}----${block.height}")
                    val willCollision:Direction? =  move.willCollision(block)
                    willCollision?.let {
                        //移动发生碰撞 跳出当前循环
                        badDirection = willCollision
                        return@blackTag
                    }
                }
            //找到阻塞快block 会碰撞的方向
            //通知可以移动的物体会在那个方向和那个物体碰撞
            move.notifyBlockCollision(badDirection,badBlockable)
        }
        //2 找到阻塞物体

        //3 遍历集合找到是否发生碰撞



        //自动检测移动的物体，让他们动起来
        views.filter { it is AutoMovable }.forEach {
            (it as AutoMovable).autoMove()
        }

        views.filter { it is Destroyable }.forEach {
            //判断是否碰撞到相关物体
            if ((it as Destroyable).isDestory()){
                views.remove(it)
            }
        }

    //检测具备攻击能力和被攻击能力的物体之间产生碰撞
        views.filter{it is Attack}.forEach { attack->
            attack as Attack
            views.filter { it is SUfferable }.forEach suffered@{ sufferable->
                sufferable as SUfferable
                //判断是否发生碰撞
                if (attack.isCollosion(sufferable)){
                    //产生碰撞
                    attack.notifyAttack(sufferable)
                    sufferable.notifySuffer(attack)
                    return@suffered
                }

            }
        }


    }

}