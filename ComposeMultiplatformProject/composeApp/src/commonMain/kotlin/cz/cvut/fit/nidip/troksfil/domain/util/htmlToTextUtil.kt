package cz.cvut.fit.nidip.troksfil.domain.util

import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlHandler
import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlParser

fun htmlToTextUtil(html: String): String {
    val html = html.trimIndent()

    var string = ""
    val handler = KsoupHtmlHandler
        .Builder()
        .onText { text ->
            string += text
        }
        .build()

    val ksoupHtmlParser = KsoupHtmlParser(
        handler = handler,
    )

    ksoupHtmlParser.write(html)
    ksoupHtmlParser.end()

    return string.trimIndent()
}