package cz.cvut.fit.nidip.troksfil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.fit.nidip.troksfil.data.local.DatabaseDriverFactory
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class MainActivity : ComponentActivity() {

    private val dbDriverFactoryModule = module {
        single { DatabaseDriverFactory(applicationContext) }
    }

    init {
        loadKoinModules(dbDriverFactoryModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}