package cz.cvut.fit.nidip.troksfil

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform