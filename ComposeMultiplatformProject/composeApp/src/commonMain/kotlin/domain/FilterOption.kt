package domain

enum class FilterOption(val filterName: String) {
    TODAY("Dnes"),
    TOMORROW("Zítra"),
    SELECTED_DATE(""),
}