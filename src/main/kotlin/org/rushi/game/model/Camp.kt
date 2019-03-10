package org.rushi.game.model

import org.itheima.kotlin.game.core.Painter
import org.rushi.game.Config
import org.rushi.game.buiness.Attack
import org.rushi.game.buiness.Blockable
import org.rushi.game.buiness.SUfferable

//阻挡和 攻击
class Camp(override var x: Int, override var y: Int):Blockable,SUfferable{

    override var blood: Int = 20
    override var width: Int = Config.block*2
    override var height: Int = Config.block+32
    override fun draw() {
        if (blood<=3){
            width = Config.block
            height = Config.block
            x = (Config.gameWidth - Config.block)/2
            y = Config.gameHeight - Config.block
            Painter.drawImage("img/symbol.gif",x,y)
        }else if(blood<6){
            Painter.drawImage("img/wall_small.gif",x,y)
            Painter.drawImage("img/wall_small.gif",x+32,y)
            Painter.drawImage("img/wall_small.gif",x+64,y)
            Painter.drawImage("img/wall_small.gif",x+96,y)
            Painter.drawImage("img/wall_small.gif",x,y+32)
            Painter.drawImage("img/wall_small.gif",x,y+64)
            Painter.drawImage("img/wall_small.gif",x+96,y+32)
            Painter.drawImage("img/wall_small.gif",x+96,y+64)
            Painter.drawImage("img/symbol.gif",x+32,y+32)
        }else {
            //回执外围的砖块
            Painter.drawImage("img/steel_small.gif", x, y)
            Painter.drawImage("img/steel_small.gif", x + 32, y)
            Painter.drawImage("img/steel_small.gif", x + 64, y)
            Painter.drawImage("img/steel_small.gif", x + 96, y)

            Painter.drawImage("img/steel_small.gif", x, y + 32)
            Painter.drawImage("img/steel_small.gif", x, y + 64)
            Painter.drawImage("img/steel_small.gif", x + 96, y + 32)
            Painter.drawImage("img/steel_small.gif", x + 96, y + 64)
            Painter.drawImage("img/symbol.gif",x+32,y+32)
        }



    }
    override fun notifySuffer(attack: Attack): Array<View>? {
        blood -= attack.attaclPower
        if (blood ==3){
            return arrayOf(Blast(x,y)
                ,Blast(x+32,y)
                ,Blast(x+Config.block,y)
                ,Blast(x+Config.block+32,y)
                ,Blast(x+Config.block*2,y)
                ,Blast(x,y+Config.block)
                ,Blast(x,y+Config.block+32)
                ,Blast(x+Config.block,y+32)
                ,Blast(x+Config.block*2,y+Config.block)
                ,Blast(x+Config.block*2,y+Config.block+32)
            )
        }
        return null
    }


}

