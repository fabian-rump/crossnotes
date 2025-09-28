package de.fabianrump.crossnotes.domain.repositories.usefulinfo

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
        "Die wirklich wichtigen Dinge stehen selten auf der To-Do-Liste – sie sind der Grund, warum du eine hast.",
        "Jeder abgehakte Punkt auf deiner To-Do-Liste ist ein Schritt näher an der Verwirklichung deiner Ziele.",
        "Die To-Do-Liste ist dein persönlicher Vertrag mit dem Erfolg.",
        "Eine leere To-Do-Liste am Ende des Tages ist das schönste Gefühl der Produktivität.",
        "Unterschätze niemals die Macht einer gut sortierten To-Do-Liste – sie ist der Architekt deines Tages.",
        "Deine To-Do-Liste spiegelt nicht nur, was du tun musst, sondern auch, wer du sein willst.",
        "Beginne mit dem Wichtigsten auf deiner To-Do-Liste, nicht mit dem Einfachsten.",
        "Die To-Do-Liste ist ein Werkzeug, um Klarheit im Kopf und Ordnung im Handeln zu schaffen.",
        "Betrachte deine To-Do-Liste nicht als eine Liste von Pflichten, sondern als eine Liste von Möglichkeiten.",
        "Die effektivste To-Do-Liste ist die, die du tatsächlich benutzt.",
        "Hinter jedem 'Erledigt' verbirgt sich die Disziplin, Ablenkungen widerstanden zu haben.",
        "Meine To-Do-Liste und ich haben eine On-Off-Beziehung. Meistens ist sie 'on' und ich bin 'off'.",
        "Die Hälfte meiner To-Do-Liste besteht darin, herauszufinden, was auf die andere Hälfte gehört.",
        "Ich bin nicht sicher, ob meine To-Do-Liste länger wird oder meine Tage kürzer.",
        "Meine To-Do-Liste hat heute Morgen beschlossen, sich selbst zu erweitern. Ungefragt.",
        "Ich habe eine To-Do-Liste für meine To-Do-Listen. Es ist ein Teufelskreis.",
        "Das Universum dehnt sich aus. Genau wie meine To-Do-Liste, wenn ich nicht hinsehe.",
        "Manchmal ist das Schwierigste an einer To-Do-Liste, sie überhaupt anzufangen.",
        "Der Moment, wenn du etwas erledigst, das nicht auf deiner To-Do-Liste stand, es aber trotzdem hinzufügst, nur um es abzuhaken.",
        "Meine To-Do-Liste lacht mich manchmal aus. Ich schwöre.",
        "Kaffee: der offizielle Sponsor meiner To-Do-Liste.",
        "Ich folge meiner To-Do-Liste. Meistens aus sicherer Entfernung, um sie nicht zu erschrecken.",
        "Prokrastination ist die Kunst, mit der gestrigen To-Do-Liste von morgen Schritt zu halten."
    )
}
