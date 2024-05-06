package cz.cvut.fit.nidip.troksfil.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cz.cvut.fit.nidip.troksfil.domain.ImageButtonType
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageButton(imageButtonType: ImageButtonType, imageSize: Int = 40) {
    val navigator: Navigator = LocalNavigator.currentOrThrow
    val uriHandler = LocalUriHandler.current

    Column {
        Surface(
            modifier = Modifier.sizeIn(maxWidth = (imageSize * 2).dp)
                .clickable {
                    if (imageButtonType.uri == "") {
                        navigator.push(imageButtonType.screen)
                    } else {
                        uriHandler.openUri(imageButtonType.uri)
                    }
                },
            color = MaterialTheme.colorScheme.primaryContainer

        ) {
            Column(
                modifier = Modifier.padding(10.dp).aspectRatio(1f)
            ) {
                Image(
                    modifier = Modifier
                        .size(imageSize.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(DrawableResource("drawable/${imageButtonType.imageButtonPath}")),
                    contentDescription = imageButtonType.imageButtonPath,
                )
                Text(
                    imageButtonType.imageButtonName,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}