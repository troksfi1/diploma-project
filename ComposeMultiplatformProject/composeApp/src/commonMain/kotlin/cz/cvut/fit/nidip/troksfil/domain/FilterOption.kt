package cz.cvut.fit.nidip.troksfil.domain

enum class FilterOption(val filterName: String) {
    TODAY("Dnes"),
    TOMORROW("Zítra"),
    SELECTED_DATE(""),
}