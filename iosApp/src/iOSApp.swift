import SwiftUI
import KotlinModules

@main
struct iOSApp: App {
    init() {
        KoinHelperKt.start()
    }

    var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
