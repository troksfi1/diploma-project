package data.repository

import domain.EventCategory
import domain.model.Event
import domain.repository.EventsDataSource
import kotlinx.datetime.LocalDateTime

class FakeEventsDataSourceImpl : EventsDataSource {

    override fun getAllEvents(): List<Event> {
        return listOf(
            Event(
                1,
                EventCategory.MUSIC,    //todo EventCategory misto Stringu?
                "Q-klub",
                "Jamaron",
                LocalDateTime.parse("2024-03-03T20:00"),
                "Stálice Junioru, sázka na toho nejlepšího koně, záruka kvalitního večera. To je koncert příbramské kapely Jazz Faces - jejich repertoár je jak barevná paleta, kde se prolíná jazz, pop, soul a funk, a vytváří tak jedinečný hudební zážitek. Na čele této muzikantské pohody stojí charismatický frontman Jakub Ročňák.",
                "drawable/img_Jamaron.jpg"
            ),
            Event(
                2,
                EventCategory.THEATRE,
                "Divadlo Příbram - Velká scéna",
                "Jistě, pane ministře",
                LocalDateTime(2024, 3, 3, 20, 0),
                "Dlouholetý opoziční politik Hacker získává po volbách jedno z ministerstev a s nejlepší vůlí se snaží vymést odsud přebujelou administrativu. Stálý tajemník ministerstva, Sir Appleby, absolutně neúnavný profesionál, má však také svůj trvalý záměr: koncentrovat moc v rukou státní správy a vyšachovat ministra ze hry. Vychází z předpokladu, že ani šéf, ani veřejnost by nikdy neměli vědět víc, než je třeba. Jak může nevyhlášený souboj těch dvou dopadnout?Divadelní verze kultovního britského televizního seriálu. Verze o desítky let mladší, a proto ostřejší, sofistikovanější, současná. Ač v britském prostředí, situace, postavy i vztahy mezi politiky jsou natolik typické, že českému divákovi snadno připomenou domácí realitu…",
                "drawable/img_theatreEvent.png"
            ),
            Event(
                3,
                EventCategory.CINEMA,
                "Divadlo Příbram - Kino",
                "Bob Marley: One Love",
                LocalDateTime(2024, 3, 4, 20, 0),
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                "drawable/img_cinemaEvent.png"
            )
        )
    }
}