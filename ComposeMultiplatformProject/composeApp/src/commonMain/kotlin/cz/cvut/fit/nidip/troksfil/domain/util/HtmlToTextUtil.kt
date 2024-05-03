package cz.cvut.fit.nidip.troksfil.domain.util

import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlHandler
import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlParser

fun HtmlToTextUtil(inputstring: String): String {

    // String to parse
    val html = inputstring.trimIndent()

    // String to store the extracted text
    var string = ""

    // Create a handler
    val handler = KsoupHtmlHandler
        .Builder()
        .onText { text ->
            string += text
        }
        .build()

    // Create a parser
    val ksoupHtmlParser = KsoupHtmlParser(
        handler = handler,
    )

    // Pass the HTML to the parser (It is going to parse the HTML and call the callbacks)
    ksoupHtmlParser.write(html)

    // Close the parser when you are done
    ksoupHtmlParser.end()

    return string.trimIndent()
}