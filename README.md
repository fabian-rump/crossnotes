# 📒 CrossNotes  

> Eine **Kotlin Multiplatform (KMP)** & **Compose Multiplatform (CMP)** Showcase-App zum Demonstrieren moderner Architektur, Bibliotheken und Best Practices.  

CrossNotes ist eine plattformübergreifende **Notiz- und ToDo-App**, die auf **Android, iOS und Desktop** läuft – mit gemeinsamem Code für Logik, Datenhaltung und Architektur.  

---

## 🚀 Features  

- ✍️ **ToDos erstellen, abhaken und Einsehen der Historie**  
- 💾 **Persistenz** mit **Room (KMP)** und **SQLDelight**  
- 🔄 **Offline-First** Architektur mit Sync zur API (Ktor Client)  
- ⚙️ **Settings** (Dark Mode, Todo Historie, User Preferences) via **DataStore**  
- 🧭 **Navigation** mit **Compose Navigation**  
- 🏗️ **MVI / StateFlow Architektur** für reaktiven UI-State  
- 🔌 **Dependency Injection** mit **Koin**  
- 📊 **Tests** mit **Kotest & Turbine**  
- 📝 **Logging** mit **Napier**  

---

## 🏛️ Architektur  

CrossNotes folgt einer **Clean Architecture** mit klar getrennten Layern:  

```mermaid
flowchart TD
    UI["UI Layer (Compose Multiplatform)"] --> Presenter["Presentation Layer (MVI: Store, State, Intent, Reducer, Executor, Label)"]
    Presenter --> Domain["Domain Layer (Use Cases)"]
    Domain --> Repo["Data Layer (Repositories)"]
    Repo --> DB[("Persistence\nRoom")]
    Repo --> API[("Remote API Ktor + Serialization")]
    Repo --> DS[("Settings DataStore")]
    DI[("Dependency Injection Koin")] -.-> Presenter
    DI -.-> Domain
    DI -.-> Repo
    Log[("Logging Napier")] -.-> Presenter
    Log -.-> Domain
    Log -.-> Repo
```

---

## 🛠️ Tech Stack

| Bereich         | Libraries / Tools                      |
|-----------------|----------------------------------------|
| **UI**          | Compose Multiplatform                  |
| **Navigation**  | Compose Navigation (Jetpack)           |
| **Persistenz**  | Room (KMP-kompatibel)                  |
| **Netzwerk**    | Ktor Client + kotlinx.serialization     |
| **Settings**    | Jetpack DataStore (Multiplatform)      |
| **State Mgmt**  | MVI mit StateFlow / Coroutines         |
| **DI**          | Koin                                   |
| **Logging**     | Napier                                 |
| **Testing**     | Kotest, Turbine (für Flows), MockK    |

---

## 📱 Screenshots (Demo)  

👉 tbd  

---

## 🔧 Setup & Installation  

### Voraussetzungen  
- [Kotlin 2.x](https://kotlinlang.org)  
- [Android Studio](https://developer.android.com/studio) mit KMP-Support  
- Xcode (für iOS Build)  

### Starten  
```bash
# Repo klonen
git clone https://github.com/fabian-rump/crossnotes.git
cd crossnotes

# Android App starten
./gradlew :androidApp:installDebug

# Desktop App starten
./gradlew :desktopApp:run
```

---

## 📂 Projektstruktur  

```plaintext
crossnotes/
 ├── androidApp/      # Android spezifisch
 ├── iosApp/          # iOS spezifisch
 ├── desktopApp/      # Desktop spezifisch
 └── shared/          # Shared KMP Code
      ├── ui/         # Compose UI
      ├── presentation/ # ViewModels / State
      ├── domain/     # Use Cases
      └── data/       # Repositories, API, DB
```

---

## 🧑‍💻 Contributing  

Pull Requests, Issues und Feature-Ideen sind herzlich willkommen!  
Bitte beachte den [Contribution Guide](CONTRIBUTING.md).  

---

## 📜 License  

MIT License © 2025 [Fabian Rump]  
