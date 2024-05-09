@file:OptIn(ExperimentalTestApi::class)

package cz.cvut.fit.nidip.troksfil.presentation.screens.more

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import kotlin.test.Test

class MoreScreenTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun to_defect_screen_and_back() = runComposeUiTest {
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
    fun to_defect_screen_check_properties_check_return() = runComposeUiTest {
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
        //Asserts that the component was found and is part of the component tree.
        onNodeWithText("Závady").assertExists().printToLog("TAG")
        //Asserts that the current semantics node is displayed on screen.
        onNodeWithText("Závady").assertIsDisplayed()
        //Asserts that the layout of this node has height that is greater than 100 dp
        onNodeWithText("Závady").assertHeightIsAtLeast(100.dp)
    }
}

