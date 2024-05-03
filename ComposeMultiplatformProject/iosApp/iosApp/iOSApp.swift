import SwiftUI

@main
struct iOSApp: App {
    init() {
        startKoin {
           modules(appModule())
        }
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}