import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinModulesKt.doInitKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView().ignoresSafeArea()
        }
    }
}
