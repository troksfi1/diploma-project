package presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageButton(name: String, imagePath: String, imageSize: Int = 40, onClick: () -> Unit) {

    Column {
        //todo delete image if vector icon will be available

        Surface(
            modifier = Modifier
                //.aspectRatio(1f)
                //.size((imageSize*2).dp)
                .clickable { onClick.invoke() },
            color = MaterialTheme.colorScheme.primaryContainer

        ) {
            Column(
                modifier = Modifier.padding(10.dp)//.aspectRatio(1f)
            ) {
                Image(
                    modifier = Modifier
                        .size(imageSize.dp)
                        //.padding((imageSize*0.06).dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(imagePath),
                    contentDescription = imagePath,
                )
                Text(
                    name,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }


    }
}