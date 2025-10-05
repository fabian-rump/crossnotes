# 📒 CrossNotes  

> Eine **Kotlin Multiplatform (KMP)** & **Compose Multiplatform (CMP)** Showcase-App zum Demonstrieren moderner Architektur, Bibliotheken und Best Practices.  

CrossNotes ist eine plattformübergreifende **Notiz- und ToDo-App**, die auf **Android und iOS** läuft – mit gemeinsamem Code für Logik, Datenhaltung und Architektur.  

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
    Repo --> DB[("Persistence Room")]
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

| Bereich         | Libraries / Tools                    |
|-----------------|--------------------------------------|
| **UI**          | Compose Multiplatform                |
| **Navigation**  | Compose Navigation (Jetpack)         |
| **Persistenz**  | Room (KMP-kompatibel)                |
| **Netzwerk**    | Ktor Client + kotlinx.serialization   |
| **Settings**    | Jetpack DataStore (Multiplatform)    |
| **State Mgmt**  | MVI mit StateFlow / Coroutines       |
| **DI**          | Koin                                 |
| **Logging**     | Napier                               |
| **Testing**     | Kotest, Turbine (für Flows)    |

---

## 📱 Screenshots (Demo)
<img src="screenshots/home.png" alt="Home" width="250"/>
<img src="screenshots/add_todo.png" alt="Home" width="250"/>
<img src="screenshots/past_todos.png" alt="Home" width="250"/>
<img src="screenshots/date_picker.png" alt="Home" width="250"/>
<img src="screenshots/about.png" alt="Home" width="250"/>
<img src="screenshots/language_region.png" alt="Home" width="250"/>
<img src="screenshots/safety_security.png" alt="Home" width="250"/>
<img src="screenshots/settings.png" alt="Home" width="250"/>

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
Zudem muss eine .gradle.properties erstellt werden mit dem Inhalt
apiKey="YOUR_HOLIDAY_API_KEY"
von "holidays.abstractapi.com"

# Desktop App starten
Um die iOS App starten zu können muss unter Product -> Scheme -> Edit Scheme als Umgebungsvariable
API_KEY=YOUR_HOLIDAY_API_KEY gesetzt werden.
./gradlew :desktopApp:run
```

---

## 📂 Projektstruktur  

```plaintext
crossnotes/
 ├── androidApp/        # Android spezifisch
 ├── iosApp/            # iOS spezifisch
 └── shared/            # Shared KMP Code
      ├── ui/           # Compose UI
      ├── presentation/ # Store, State, Intent, Reducer, Executor, Label
      ├── domain/       # Use Cases
      └── data/         # Repositories, API, DB
```

---

## 🧑‍💻 Contributing  

Pull Requests, Issues und Feature-Ideen sind herzlich willkommen!  
Bitte beachte den [Contribution Guide](CONTRIBUTING.md).  

---

## 📜 License  

MIT License © 2025 [Fabian Rump]  
