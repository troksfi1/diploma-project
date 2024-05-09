package cz.cvut.fit.nidip.troksfil

import android.content.Context
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import cz.cvut.fit.nidip.troksfil.data.local.DatabaseDriverFactory
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import kotlin.test.Test

class NavigationUiTest {

    private lateinit var instrumentationContext: Context


    private val dbDriverFactoryModule = module {
        single { DatabaseDriverFactory(instrumentationContext) }
    }

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun nav_to_news_detail_screen_and_back() {

        val titleString = "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře"
        val textString =
            "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici."
        val textHomeScreen = "Aktuality"

        rule.setContent { App() }

        rule.onNodeWithText("Domů").performClick()
        rule.onNodeWithText(textHomeScreen).assertExists()

        rule.onNodeWithText(titleString).performClick()
        rule.onNodeWithText(textString).assertExists()

        rule.onNodeWithContentDescription("Arrow Back").performClick()

        rule.onNodeWithText(textHomeScreen).assertExists()

    }

    @Test
    fun nav_to_parking_search_select_item_pay() {

        loadKoinModules(dbDriverFactoryModule)

        rule.setContent { App() }

        rule.onNodeWithText("Parkování").performClick()
        rule.onNodeWithText("Zadejte název nebo číslo zóny").performClick()
        rule.onNodeWithText("nám. T.G.M. [PB1]").performClick()

        //      \item Ověření, že systém zobrazil vybranou parkovací zónu.

        rule.onNodeWithText("Zaplatit").assertExists()
    }

    @Test
    fun nav_to_events_open_calendar_press_cancel_check_buttons_state() {

        loadKoinModules(dbDriverFactoryModule)

        rule.setContent { App() }

        rule.onNodeWithText("Události").performClick()
        rule.onNodeWithText("Dnes").assertIsSelected()

        rule.onNodeWithContentDescription("calendar icon").performClick()
        rule.onNodeWithText("Zrušit").performClick()

        rule.onNodeWithText("Dnes").assertIsSelected()
        rule.onNodeWithText("Zítra").assertIsNotSelected()
        rule.onNodeWithContentDescription("calendar icon").assertIsNotSelected()
    }


    @Test
    fun nav_to_all_tabs() {
        loadKoinModules(dbDriverFactoryModule)

        rule.setContent { App() }

        rule.onNodeWithText("Události").performClick()
        rule.onNodeWithText("Dnes").assertExists()

        rule.onNodeWithText("Parkování").performClick()
        rule.onNodeWithText("Zadejte název nebo číslo zóny").assertExists()

        rule.onNodeWithText("Více").performClick()
        rule.onNodeWithText("Facebook").assertExists()

    }

    @Test
    fun nav_to_defect_and_back_button() {
        loadKoinModules(dbDriverFactoryModule)

        rule.setContent { App() }

        rule.onNodeWithText("Závady").performClick()
        rule.onNodeWithContentDescription("Arrow Back").performClick()
        rule.onNodeWithText("Aktuality").assertExists()
    }

    @Test
    fun nav_to_defect_send_defect_return_back() {
        loadKoinModules(dbDriverFactoryModule)

        rule.setContent { App() }

        rule.onNodeWithText("Závady").performClick()
        rule.onNodeWithText("Odeslat závadu").performClick()
        rule.onNodeWithText("Aktuality").assertExists()

    }

    /*    @Test
        fun nav_to_event_screen_click_event_go_back() {

            val textEventScreen = "Dnes"
            val titleString = "Jamaron, Čt 14. 3. 2024"
            val textString = "Dlouholetý opoziční politik Hacker získává po volbách jedno z ministerstev a s nejlepší vůlí se snaží vymést odsud přebujelou administrativu. Stálý tajemník ministerstva, Sir Appleby, absolutně neúnavný profesionál, má však také svůj trvalý záměr: koncentrovat moc v rukou státní správy a vyšachovat ministra ze hry. Vychází z předpokladu, že ani šéf, ani veřejnost by nikdy neměli vědět víc, než je třeba. Jak může nevyhlášený souboj těch dvou dopadnout?Divadelní verze kultovního britského televizního seriálu. Verze o desítky let mladší, a proto ostřejší, sofistikovanější, současná. Ač v britském prostředí, situace, postavy i vztahy mezi politiky jsou natolik typické, že českému divákovi snadno připomenou domácí realitu…"

            rule.setContent { App() }

            rule.onRoot().printToLog("TAG")

            rule.onNodeWithText("Události").performClick()
            rule.onNodeWithText(textEventScreen).assertExists()

            rule.onNodeWithText(titleString).performClick()
            rule.onNodeWithText(textString).assertExists()

            rule.onNodeWithContentDescription("Arrow Back").performClick()

            rule.onNodeWithText(textEventScreen).assertExists()

        }*/

}
