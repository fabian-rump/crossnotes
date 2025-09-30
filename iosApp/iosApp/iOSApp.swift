import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        LoggingSetup_iosKt.setupLogging()
        KoinModulesKt.doInitKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView().ignoresSafeArea()
        }
    }
}
