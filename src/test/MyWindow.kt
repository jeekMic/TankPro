import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Painter
import org.itheima.kotlin.game.core.Window
import sun.plugin.javascript.navig.Array
import javafx.scene.paint.Color
import org.itheima.kotlin.game.core.Composer

/**
 * 窗口类，继承游戏引擎的总窗口
 */
class MyWindow:Window(){

    override fun onCreate() {
        println("onCreate")
    }

    override fun onDisplay() {
        println("onDisplay")
        Painter.drawImage("img/tank_2_u.gif",0,0)
        Painter.drawColor(Color.BLUE,100,100,100,100)
    }

    override fun onKeyPressed(event: KeyEvent) {
        when(event.code){
            KeyCode.ENTER -> println("enter--------------------")
            KeyCode.L -> Composer.play("snd/blast.wav")
        }
    }

    override fun onRefresh() {
        //业务逻辑 做耗时操作
        println("onRefresh")
    }

}

fun main() {
    Application.launch(MyWindow::class.java)
}
