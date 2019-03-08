package org.rushi.game.model

import org.itheima.kotlin.game.core.Painter
import org.rushi.game.Config
import org.rushi.game.buiness.Attack
import org.rushi.game.buiness.Blockable
import org.rushi.game.buiness.Destroyable
import org.rushi.game.buiness.SUfferable


class Wall(override var x: Int, override var y: Int):Blockable,SUfferable,Destroyable{



    //    override val x: Int = 200
//    override val y: Int = 200
    override var width: Int = Config.block
    override var blood: Int = 3
    override var height: Int = Config.block

    override fun draw() {
        Painter.drawImage("img/wall.gif",x,y)
    }
    override fun notifySuffer(attack: Attack) {
        println("砖墙接收到打击了")
        //自己销毁
        blood -=attack.attaclPower
    }
    override fun isDestory(): Boolean=blood<=0
}