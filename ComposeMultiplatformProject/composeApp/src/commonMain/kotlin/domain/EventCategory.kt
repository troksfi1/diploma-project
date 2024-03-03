package domain

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
    TOP_EVENTS("TOP Akce"),
}
