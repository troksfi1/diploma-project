import SwiftUI

@main
struct iOSApp: App {
    init() {
        KoinHelperKt.doInitKoin()
    }

    /*startKoin {
            modules(module {
                single<SpaceXApi> { SpaceXApi() }
                single<SpaceXSDK> {
                    SpaceXSDK(
                        databaseDriverFactory = IOSDatabaseDriverFactory(), api = get()
                    )
                }
            })
        }*/

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}