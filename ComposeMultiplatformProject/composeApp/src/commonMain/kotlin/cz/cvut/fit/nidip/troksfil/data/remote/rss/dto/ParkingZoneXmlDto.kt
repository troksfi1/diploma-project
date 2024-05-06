package cz.cvut.fit.nidip.troksfil.data.remote.rss.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement

@Serializable
data class Document(
    @XmlElement(true)
    val Placemark: List<Placemark>,
)

@Serializable
data class Placemark(
    @XmlElement(true)
    val name: String,
    @XmlElement(true)
    val description: String,
    @XmlElement(true)
    val styleUrl: String,
    @XmlElement(true)
    val Polygon: Polygon,
)

@Serializable
data class Polygon(
    @XmlElement(true)
    val coordinates: String,
)