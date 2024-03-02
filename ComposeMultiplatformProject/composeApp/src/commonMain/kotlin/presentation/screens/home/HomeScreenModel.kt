package presentation.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import domain.model.News
import domain.repository.NewsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.xml.xml
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.serialization.XML

class HomeScreenModel(
    private val newsRepository: NewsRepository
) : ScreenModel {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    //private val uriHandler = LocalUriHandler.current

    fun onEvent(event: HomeEvent) {
        when (event) {

            HomeEvent.OnButtonYouTubeClicked -> {
                //uriHandler.openUri("https://www.youtube.com/@mestopribram1671")
                _state.update { state ->
                    state.copy(

                    )
                }
            }

            else -> Unit
        }

    }

    val client = HttpClient {
        install(ContentNegotiation) {
            xml(
                contentType = ContentType.Application.Xml,
                format = XML { //ContentType.Application.Rss  // XML = DefaultXml
                    xmlDeclMode = XmlDeclMode.Auto
                })
        }
    }

    suspend fun getNews(): News {
        return client.get("https://pribram.eu/xmldata/aktuality/").body()
    }
}