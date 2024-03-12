package cz.cvut.fit.nidip.troksfil.di

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cz.cvut.fit.nidip.troksfil.presentation.screens.events.EventsScreenModel
import cz.cvut.fit.nidip.troksfil.presentation.screens.home.HomeScreenModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin

@Composable
inline fun <reified T : ScreenModel> Screen.getScreenModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T {
    val koin = getKoin()
    return rememberScreenModel(tag = qualifier?.value) { koin.get(qualifier, parameters) }
}

val viewModel = module {

    factory {
        HomeScreenModel(get())
    }
    factory {
        EventsScreenModel(get())
    }

}