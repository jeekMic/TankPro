package org.rushi.game.model

/**
 * 视图显示的规范
 */
interface View {
    //可以定义属性 让实现类去实现
    //位置信息
    val x:Int
    val y:Int
    //宽高
    var width:Int
    var height:Int
    //显示
    fun draw()

    fun checkColision(x1:Int,y1:Int, w1:Int,h1:Int,x2:Int,y2:Int, w2:Int,h2:Int):Boolean{
        return when{
            y2+h2<=y1->  //阻挡物在运动物的上方
                false
            y1+h1<=y2-> //度党务在运动物的下方
                false
            x2+w2<=x1->  //阻挡物在运动物的左方
                false
            x1+w1<=x2->  //阻挡物在运动物的右方
                false
            else->{
                true
            }

        }
    }

}