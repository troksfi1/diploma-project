import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cz.cvut.fit.nidip.troksfil.presentation.screens.more.MoreScreen
import org.junit.Rule
import org.junit.Test

class MoreScreenTest {
    //just for testing purpose

    @get:Rule
    val rule = createComposeRule()

    @Test
    @OptIn(ExperimentalTestApi::class)
    fun myTest() = runComposeUiTest {
        setContent {
            Navigator(MoreScreen())
        }

        onNodeWithText("Závady").performClick()

        onNodeWithContentDescription("Arrow Back").performClick()

        onNodeWithText("Závady").assertExists()

        onRoot().printToLog("TAG")
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun myfTest() = runComposeUiTest {
        setContent {
            Navigator(MoreScreen())
        }

        onNodeWithText("Závady").performClick()

        onNodeWithTag("defectTextField").assertExists()
        onNodeWithText("Záznam o závadě").assertExists()

        onNodeWithText("Odeslat závadu").performClick()

        onNodeWithText("Závady").assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun check_properties_check_return() = runComposeUiTest {
        setContent {
            Navigator(MoreScreen())
        }

        onNodeWithText("Závady").isDisplayed()
        onNodeWithText("Závady").assertWidthIsAtLeast(100.dp)
    }
}