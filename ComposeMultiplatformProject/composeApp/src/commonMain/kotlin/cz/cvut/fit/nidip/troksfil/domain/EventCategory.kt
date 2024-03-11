package cz.cvut.fit.nidip.troksfil.domain

enum class EventCategory(val categoryName: String) {
    ALL("Vše"),
    THEATRE("Divadlo"),
    EXHIBITIONS("Výstavy"),
    FESTIVALS("Festivaly"),
    CINEMA("Kino"),
    MUSIC("Hudba"),
    LECTURE("Přednášky"),
    SPORT("Sport"),
    FOR_KIDS("Pro děti"),
    OTHER("Ostatní"),
    ALL_DAY("celodenní akce"),
    TOP_EVENTS("TOP Akce"),
}
