package cz.cvut.fit.nidip.troksfil.data.remote.rss

import cz.cvut.fit.nidip.troksfil.data.remote.rss.dto.EventsXmlDto
import cz.cvut.fit.nidip.troksfil.data.remote.rss.dto.NewsXmlDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.core.XmlVersion
import nl.adaptivity.xmlutil.serialization.XML

/*sealed interface Result {
    object Loading : Result
    data class Success(val lst: List<Int>) : Result
    data class Error(val err: Throwable) : Result
}*/

class RssFeed { //todo divide to event ana news?
    private val client = HttpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = 40000
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.d { message }
                }
            }
            level = LogLevel.INFO
        }
    }

    private val format = XML {
        xmlVersion = XmlVersion.XML10
        xmlDeclMode = XmlDeclMode.Charset
        indentString = "    "
    }

    suspend fun getEventsXml(): EventsXmlDto {
        val response: String = client.get("https://kalendar.pribram.eu/xmldata/akce/").body()
        return format.decodeFromString<EventsXmlDto>(response.cleanUpEventXml())
    }

    suspend fun getNewsXml(): NewsXmlDto {
        val response: String = client.get("https://pribram.eu/xmldata/aktuality/").body()
        return format.decodeFromString<NewsXmlDto>(response.cleanUpNewsXml())
    }
}

fun String.cleanUpEventXml(): String =
    replace("eternal:tag", "tag")
        .replace("eternal:date", "date")
        .replace("eternal:text", "text")
        .replace("eternal:thumbnail", "thumbnail")
        .replace("eternal:gallery", "gallery")
        .replace("&", "ampersand")

fun String.cleanUpNewsXml(): String =
    replace("eternal:text", "text")
        .replace("eternal:thumbnail", "thumbnail")
        .replace("eternal:thumbnail_med", "thumbnail_med")
        .replace("eternal:gallery", "gallery")
