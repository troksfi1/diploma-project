package cz.cvut.fit.nidip.troksfil.data.repository

import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.model.News
import cz.cvut.fit.nidip.troksfil.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDateTime

class FakeRepositoryImpl : Repository {

    private val _events: MutableStateFlow<List<Event>> = MutableStateFlow(emptyList())
    var events = _events.asStateFlow()

    private val _news: MutableStateFlow<List<News>> = MutableStateFlow(emptyList())
    var news = _news.asStateFlow()
    override suspend fun insertAllEvents(events: List<Event>) {
        TODO("Not yet implemented")
    }

    override suspend fun insertEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllEvents(): StateFlow<List<Event>> {
        _events.update {
            listOf(
                Event(
                    1,
                    listOf(EventCategory.MUSIC, EventCategory.ALL_DAY),
                    "Q-klub",
                    "Jamaron",
                    LocalDateTime.parse("2024-03-14T20:00"),
                    LocalDateTime.parse("2024-03-14T22:00"),
                    "Stálice Junioru, sázka na toho nejlepšího koně, záruka kvalitního večera. To je koncert příbramské kapely Jazz Faces - jejich repertoár je jak barevná paleta, kde se prolíná jazz, pop, soul a funk, a vytváří tak jedinečný hudební zážitek. Na čele této muzikantské pohody stojí charismatický frontman Jakub Ročňák.",
                    "https://static.goout.cloud/musicbarcz/2023/06/56d212ce-jamaron-1920x1080.jpg",
                    "https://static.goout.cloud/musicbarcz/2023/06/56d212ce-jamaron-1920x1080.jpg"
                ),
                Event(
                    2,
                    listOf(EventCategory.THEATRE, EventCategory.FOR_KIDS),
                    "Divadlo Příbram - Velká scéna",
                    "Jistě, pane ministře",
                    LocalDateTime(2024, 3, 14, 20, 0),
                    LocalDateTime(2024, 3, 15, 22, 0),
                    "Dlouholetý opoziční politik Hacker získává po volbách jedno z ministerstev a s nejlepší vůlí se snaží vymést odsud přebujelou administrativu. Stálý tajemník ministerstva, Sir Appleby, absolutně neúnavný profesionál, má však také svůj trvalý záměr: koncentrovat moc v rukou státní správy a vyšachovat ministra ze hry. Vychází z předpokladu, že ani šéf, ani veřejnost by nikdy neměli vědět víc, než je třeba. Jak může nevyhlášený souboj těch dvou dopadnout?Divadelní verze kultovního britského televizního seriálu. Verze o desítky let mladší, a proto ostřejší, sofistikovanější, současná. Ač v britském prostředí, situace, postavy i vztahy mezi politiky jsou natolik typické, že českému divákovi snadno připomenou domácí realitu…",
                    "https://www.divadlokalich.cz/images/c/511-max-1200x1200.jpg",
                    "https://www.divadlokalich.cz/images/c/511-max-1200x1200.jpg"
                ),
                Event(
                    3,
                    listOf(EventCategory.CINEMA),
                    "Divadlo Příbram - Kino",
                    "Bob Marley: One Love",
                    LocalDateTime(2024, 3, 4, 20, 0),
                    LocalDateTime(2024, 3, 4, 22, 0),
                    "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                    "https://image.pmgstatic.com/cache/resized/w936/files/images/film/photos/168/013/168013408_l4fnf3.jpg",
                    "https://image.pmgstatic.com/cache/resized/w936/files/images/film/photos/168/013/168013408_l4fnf3.jpg"
                )
            )

        }
        return events
    }

    override suspend fun getNewestEvents(): StateFlow<List<Event>> {
        _events.update {
            listOf(
                Event(
                    1,
                    listOf(EventCategory.MUSIC, EventCategory.ALL_DAY),
                    "Q-klub",
                    "Jamaron",
                    LocalDateTime.parse("2024-03-14T20:00"),
                    LocalDateTime.parse("2024-03-14T22:00"),
                    "Stálice Junioru, sázka na toho nejlepšího koně, záruka kvalitního večera. To je koncert příbramské kapely Jazz Faces - jejich repertoár je jak barevná paleta, kde se prolíná jazz, pop, soul a funk, a vytváří tak jedinečný hudební zážitek. Na čele této muzikantské pohody stojí charismatický frontman Jakub Ročňák.",
                    "https://static.goout.cloud/musicbarcz/2023/06/56d212ce-jamaron-1920x1080.jpg",
                    "https://static.goout.cloud/musicbarcz/2023/06/56d212ce-jamaron-1920x1080.jpg"
                ),
                Event(
                    2,
                    listOf(EventCategory.THEATRE, EventCategory.FOR_KIDS),
                    "Divadlo Příbram - Velká scéna",
                    "Jistě, pane ministře",
                    LocalDateTime(2024, 3, 14, 20, 0),
                    LocalDateTime(2024, 3, 15, 22, 0),
                    "Dlouholetý opoziční politik Hacker získává po volbách jedno z ministerstev a s nejlepší vůlí se snaží vymést odsud přebujelou administrativu. Stálý tajemník ministerstva, Sir Appleby, absolutně neúnavný profesionál, má však také svůj trvalý záměr: koncentrovat moc v rukou státní správy a vyšachovat ministra ze hry. Vychází z předpokladu, že ani šéf, ani veřejnost by nikdy neměli vědět víc, než je třeba. Jak může nevyhlášený souboj těch dvou dopadnout?Divadelní verze kultovního britského televizního seriálu. Verze o desítky let mladší, a proto ostřejší, sofistikovanější, současná. Ač v britském prostředí, situace, postavy i vztahy mezi politiky jsou natolik typické, že českému divákovi snadno připomenou domácí realitu…",
                    "https://www.divadlokalich.cz/images/c/511-max-1200x1200.jpg",
                    "https://www.divadlokalich.cz/images/c/511-max-1200x1200.jpg"
                ),
                Event(
                    3,
                    listOf(EventCategory.CINEMA),
                    "Divadlo Příbram - Kino",
                    "Bob Marley: One Love",
                    LocalDateTime(2024, 3, 4, 20, 0),
                    LocalDateTime(2024, 3, 4, 22, 0),
                    "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                    "https://image.pmgstatic.com/cache/resized/w936/files/images/film/photos/168/013/168013408_l4fnf3.jpg",
                    "https://image.pmgstatic.com/cache/resized/w936/files/images/film/photos/168/013/168013408_l4fnf3.jpg"
                )
            )

        }
        return events
    }

    override suspend fun deleteAllEvents() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllNews(): StateFlow<List<News>> {
        _news.update {
            listOf(
                News(
                    1,
                    "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                    LocalDateTime(2024, 3, 4, 20, 0),
                    "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                    "https://pribram.eu/galerie_clanky/104990_med.jpg",
                    "https://pribram.eu/galerie_clanky/104990_med.jpg"
                ),
                News(
                    2,
                    "Lávka ve Špitálské je dokončena",
                    LocalDateTime(2024, 3, 4, 20, 0),
                    "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                    "https://pribram.eu/galerie_clanky/104990_med.jpg",
                    "https://pribram.eu/galerie_clanky/104990_med.jpg"
                ),
                News(
                    3,
                    "Lávka ve Špitálské",
                    LocalDateTime(2024, 3, 4, 20, 0),
                    "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                    "https://pribram.eu/galerie_clanky/104990_med.jpg",
                    "https://pribram.eu/galerie_clanky/104990_med.jpg"
                )
            )
        }
        return news
    }

    override suspend fun insertAllNews(news: List<News>) {
        TODO("Not yet implemented")
    }

    override suspend fun insertNews(news: News) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllNews() {
        TODO("Not yet implemented")
    }
}