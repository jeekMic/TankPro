package org.rushi.game.model

import org.itheima.kotlin.game.core.Painter
import org.rushi.game.Config
import org.rushi.game.buiness.Destroyable


class Blast(override val x: Int, override val y: Int) :Destroyable{


    override var width: Int = Config.block
    override var height: Int = Config.block
    private val imagePaths = arrayListOf<String>()
    private var index:Int = 0
    init {
        (1 .. 32).forEach {
            var path = "img/blast_${it}.png"
            println(path)
            imagePaths.add(path)
        }




    }
    override fun draw() {
        var i = index%imagePaths.size
       Painter.drawImage(imagePaths[i],x,y)
        index++
    }
    override fun isDestory(): Boolean {
        return  index >= imagePaths.size
    }
}