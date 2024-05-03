import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cz.cvut.fit.nidip.troksfil.App
import cz.cvut.fit.nidip.troksfil.data.local.DatabaseDriverFactory
import cz.cvut.fit.nidip.troksfil.di.appModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module


private val dbDriverFactoryModule = module {
    single { DatabaseDriverFactory() }
}

fun main() = application {

    startKoin {
        modules(appModule())
    }

    loadKoinModules(dbDriverFactoryModule)

    Window(onCloseRequest = ::exitApplication, title = "ComposeMultiplatformProject") {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}