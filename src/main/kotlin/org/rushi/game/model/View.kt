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
}