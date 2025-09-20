package de.fabianrump.crossnotes.domain.repository

internal class UsefulInfoRepositoryImpl : UsefulInfoRepository {

    override fun load(): String = wiseSayings.random()

    private val wiseSayings = listOf(
        "Eine To-Do-Liste ist der Kompass, der dich durch den Tag navigiert, nicht die Kette, die dich fesselt.",
        "Die kürzeste To-Do-Liste ist oft die produktivste: Beginne.",
        "To-Do-Listen verwandeln Chaos in Möglichkeiten und Träume in Pläne.",
        "Was heute auf deiner To-Do-Liste steht, formt das Morgen, das du dir wünschst.",
        "Eine gut geführte To-Do-Liste ist weniger eine Last als vielmehr ein Versprechen an dich selbst.",
        "Meine To-Do-Liste hat auch eine To-Do-Liste.",
        "Die gefährlichste To-Do-Liste ist die, die nie geschrieben wird.",
        "To-Do-Listen sind wie Schneeflocken: Jede ist einzigartig und schmilzt dahin, wenn man sie ignoriert.",
        "Ich habe meine To-Do-Liste für heute erfolgreich auf 'morgen' verschoben.",
        "Die Kunst besteht nicht darin, eine lange To-Do-Liste zu haben, sondern die richtigen Dinge darauf.",
        "Eine To-Do-Liste ist ein Gespräch mit deiner zukünftigen, produktiveren Version.",
        "Hinter jeder erledigten Aufgabe auf der To-Do-Liste steckt ein kleiner Sieg über die Prokrastination.",
        "Die wirklich wichtigen Dinge stehen selten auf der To-Do-Liste – sie sind der Grund, warum du eine hast."
    )
}
