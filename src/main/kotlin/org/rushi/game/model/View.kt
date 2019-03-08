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
        println("${x1}--${y1}--${w1}--${h1}--${x2}--${y2}--${w2}--${h2}")
        //TODO("not implemented") 监测下一步碰撞
        //x1   y1  w1  h1  x2   y2  w2  h2
        //64--192--64--64--192--64--64--64
        if (x2==192){
            println("---")
        }
        var isColl =  when {
            y2+h2<=x1 -> //如果阻挡物在运动物的上方，则不会放生碰撞
            {
                println("---1")
                false
            }
            y2>=y1+h1 -> //如果阻挡物在运动物的下方，则不会放生碰撞
            {
                println("---2")
                false
            }
            x2+w2<=x1-> //如果阻挡物在运动物的左方，则不会放生碰撞
            {
                println("---3")
                false
            }
            x1 >= x2+w2 -> //如果阻挡物在运动物的右方，则不会放生碰撞
            {
                println("--4-")
                false
            }
            else -> {
                true
            }
        }
        return isColl
    }

}