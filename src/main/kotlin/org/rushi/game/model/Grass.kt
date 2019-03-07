package org.rushi.game.model

import org.itheima.kotlin.game.core.Painter
import org.rushi.game.Config

class Grass(override val x: Int, override val y: Int):View{
//    override val x: Int = 200
//    override val y: Int = 200
    override var width: Int = Config.block
    override var height: Int = Config.block

    override fun draw() {
        Painter.drawImage("img/grass.gif",x,y)
    }
}