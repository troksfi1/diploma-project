package cz.cvut.fit.nidip.troksfil.data.remote.rss.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName("rss")
@Serializable
data class EventsXmlDto(
    @XmlElement(true)
    val rss: EventChannel,
)

@XmlSerialName("channel")
@Serializable
data class EventChannel(
    @XmlElement(true)
    val channel: List<EventItem>,
)

@XmlSerialName("item")
@Serializable
data class EventItem(
    @XmlElement(true)
    val guid: String,
    @XmlElement(true)
    val title: String,
    @XmlElement(true)
    val link: String,
    @XmlElement(true)
    val tag: String,
    @XmlElement(true)
    val date: EventDate,
    @XmlElement(true)
    val text: String,
    @XmlElement(true)
    val thumbnail: String,
    @XmlElement(true)
    val gallery: NewsGallery,
)

@XmlSerialName("date")
@Serializable
data class EventDate(
    @XmlElement(true)
    @XmlSerialName("start_date")
    val startDate: String,
    @XmlElement(true)
    @XmlSerialName("end_date")
    val endDate: String,
    @XmlElement(true)
    @XmlSerialName("start_time")
    val startTime: String,
    @XmlElement(true)
    @XmlSerialName("end_time")
    val endTime: String
)

@XmlSerialName("gallery")
@Serializable
data class EventGallery(
    @XmlElement(true)
    val image: NewsImage,
)

@XmlSerialName("image")
@Serializable
data class EventImage(
    @XmlElement(true)
    val url: String,
    @XmlElement(true)
    val description: String,
)