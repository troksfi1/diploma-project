package cz.cvut.fit.nidip.troksfil.data.remote.rss

import cz.cvut.fit.nidip.troksfil.data.remote.rss.dto.EventsXmlDto
import cz.cvut.fit.nidip.troksfil.data.remote.rss.dto.NewsXmlDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.core.XmlVersion
import nl.adaptivity.xmlutil.serialization.XML

class RssFeed { //todo divide to event ana news?
    private val client = HttpClient()

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
