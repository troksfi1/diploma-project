package presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageButton(name: String, path: String, size: Int = 90) {

    Column {
        /*Icon()*/
        Text(name)

        //todo delete image if vector icon will be available
        Image(
            modifier = Modifier
                .size(size.dp)
                .padding(6.dp),
            painter = painterResource(path),
            contentDescription = path,
        )



    }
}