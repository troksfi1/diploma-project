package cz.cvut.fit.nidip.troksfil.domain

import cafe.adriel.voyager.core.screen.Screen
import cz.cvut.fit.nidip.troksfil.presentation.screens.home.AuthorityScreen
import cz.cvut.fit.nidip.troksfil.presentation.screens.home.DefectScreen
import cz.cvut.fit.nidip.troksfil.presentation.screens.home.ServiceScreen

enum class ImageButtonType(
    val imageButtonName: String,
    val imageButtonPath: String,
    val screen: Screen = DefectScreen(),
    val uri: String = ""
) { //todo remove default screen
    DEFECTS("Závady", "img_defect.png", DefectScreen()),
    MUNICIPAL_AUTHORITY("Úřad", "img_municipal_authority.png", AuthorityScreen()),
    SERVICES("Služby", "img_service.png", ServiceScreen()),
    YOUTUBE("YouTube", "img_youtube.png", uri = "https://www.youtube.com/@mestopribram1671"),
    FACEBOOK(
        "Facebook",
        "img_facebook.png",
        uri = "https://www.facebook.com/mestskyuradpribram/?locale=cs_CZ"
    ),
    WEB("Web", "img_website.png", uri = "https://pribram.eu/"),
    WEBCAMS("Webkamery", "img_webcams.png", uri = "https://kamery.pribram.cz/")
}
