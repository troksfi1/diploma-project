package cz.cvut.fit.nidip.troksfil.data.remote.rss.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName("rss")
@Serializable
data class NewsXmlDto(
    @XmlElement(true)
    val rss: NewsChannel,
)

@XmlSerialName("channel")
@Serializable
data class NewsChannel(
    @XmlElement(true)
    val channel: List<NewsItem>,
)

@XmlSerialName("item")
@Serializable
data class NewsItem(
    @XmlElement(true)
    val guid: String,
    @XmlElement(true)
    val title: String,
    @XmlElement(true)
    val pubDate: String,
    @XmlElement(true)
    val link: String,
    @XmlElement(true)
    val text: String,
    @XmlElement(true)
    val thumbnail: String?,
    @XmlElement(true)
    val thumbnail_med: String?,
    @XmlElement(true)
    val gallery: NewsGallery?,
)

@XmlSerialName("gallery")
@Serializable
data class NewsGallery(
    @XmlElement(true)
    val image: NewsImage,
)

@XmlSerialName("image")
@Serializable
data class NewsImage(
    @XmlElement(true)
    val url: String,
    @XmlElement(true)
    val description: String,
)