package org.rushi.game

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Painter
import org.itheima.kotlin.game.core.Window
import org.rushi.game.enums.Direction
import org.rushi.game.model.*
import java.io.File


class GameWindow:Window(title="标哥坦克大战",icon = "img/gamel.png",height = Config.gameHeight, width = Config.gameWidth){

//    var wall = Wall()
//    var grass = Grass()
    var views = arrayListOf<View>()
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
            when(column){
                '砖' -> views.add(Wall(columnNum * Config.block, lineNum * Config.block))
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
            KeyCode.W -> myTank.move(Direction.UP)
            KeyCode.S -> myTank.move(Direction.DOWN)
            KeyCode.A -> myTank.move(Direction.LEFT)
            KeyCode.D -> myTank.move(Direction.RIGHT)
            else -> println("请不要乱按")
        }
    }

    override fun onRefresh() {
    }

}