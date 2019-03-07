package org.rushi.game.model

import org.itheima.kotlin.game.core.Painter
import org.rushi.game.Config
import org.rushi.game.buiness.Blockable


class Steel(override var x: Int, override var y: Int):Blockable{
//    override val x: Int = 200
//    override val y: Int = 200
    override var width: Int = Config.block
    override var height: Int = Config.block

    override fun draw() {
        Painter.drawImage("img/steel.gif",x,y)
    }
}