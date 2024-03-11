package cz.cvut.fit.nidip.troksfil.data.repository

import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.model.News
import cz.cvut.fit.nidip.troksfil.domain.repository.EventsRepository
import cz.cvut.fit.nidip.troksfil.domain.repository.NewsRepository
import kotlinx.datetime.LocalDateTime

class FakeRepositoryImpl : EventsRepository, NewsRepository {

    override suspend fun getAllEvents(): List<Event> {
        return listOf(
            Event(
                1,
                listOf(EventCategory.MUSIC, EventCategory.TOP_EVENTS),
                "Q-klub",
                "Jamaron",
                LocalDateTime.parse("2024-03-03T20:00"),
                LocalDateTime.parse("2024-03-03T22:00"),
                "Stálice Junioru, sázka na toho nejlepšího koně, záruka kvalitního večera. To je koncert příbramské kapely Jazz Faces - jejich repertoár je jak barevná paleta, kde se prolíná jazz, pop, soul a funk, a vytváří tak jedinečný hudební zážitek. Na čele této muzikantské pohody stojí charismatický frontman Jakub Ročňák.",
                "https://static.goout.cloud/musicbarcz/2023/06/56d212ce-jamaron-1920x1080.jpg",
                "https://static.goout.cloud/musicbarcz/2023/06/56d212ce-jamaron-1920x1080.jpg"
            ),
            Event(
                2,
                listOf(EventCategory.THEATRE, EventCategory.FOR_KIDS),
                "Divadlo Příbram - Velká scéna",
                "Jistě, pane ministře",
                LocalDateTime(2024, 3, 3, 20, 0),
                LocalDateTime(2024, 3, 3, 22, 0),
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

    override suspend fun getAllNews(): List<News> {
        return listOf(
            News(
                1,
                "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                LocalDateTime(2024, 3, 4, 20, 0),
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                "Petr",
                "drawable/img_news_1.jpg"
            ),
            News(
                2,
                "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                LocalDateTime(2024, 3, 4, 20, 0),
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                "Honza",
                "drawable/img_news_2.jpg"
            ),
            News(
                3,
                "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                LocalDateTime(2024, 3, 4, 20, 0),
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                "Petr",
                "drawable/img_news_1.jpg"
            ),
            News(
                4,
                "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                LocalDateTime(2024, 3, 4, 20, 0),
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                "Petr",
                "drawable/img_news_1.jpg"
            ),
            News(
                5,
                "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                LocalDateTime(2024, 3, 4, 20, 0),
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                "Petr",
                "drawable/img_news_1.jpg"
            )
        )
    }

    override fun removeAllNews() {
        TODO("Not yet implemented")
    }
}