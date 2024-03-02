package data.repository

import domain.model.News
import domain.repository.NewsRepository

class FakeNewsRepositoryImpl : NewsRepository {
    override fun removeAllNews() {
        TODO("Not yet implemented")
    }

    override fun getAllNews(): List<News> {
        return listOf(
            News(
                1,
                "st 14.1.2024 20:00",
                "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                "Petr",
                "drawable/img_news_1.jpg"
            ),
            News(
                2,
                "st 14.1.2024 20:00",
                "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                "Honza",
                "drawable/img_news_2.jpg"
            ),
            News(
                3,
                "st 14.1.2024 20:00",
                "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                "Petr",
                "drawable/img_news_1.jpg"
            ),
            News(
                4,
                "st 14.1.2024 20:00",
                "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                "Petr",
                "drawable/img_news_1.jpg"
            )
        )
    }
}