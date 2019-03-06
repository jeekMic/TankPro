import javafx.application.Application
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window
import sun.plugin.javascript.navig.Array

/**
 * 窗口类，继承游戏引擎的总窗口
 */
class MyWindow:Window(){

    override fun onCreate() {
        println("onCreate")
    }

    override fun onDisplay() {
        println("onDisplay")
    }

    override fun onKeyPressed(event: KeyEvent) {
        println("onKeyPressed")
    }

    override fun onRefresh() {
        println("onRefresh")
    }

}

fun main() {
    Application.launch(MyWindow::class.java)
}
