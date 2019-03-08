package org.rushi.game.ext

import org.rushi.game.model.View

fun View.checkColision(view: View):Boolean{
    return  checkColision(x,y,width,height,view.x,view.y,view.width,view.height)
}