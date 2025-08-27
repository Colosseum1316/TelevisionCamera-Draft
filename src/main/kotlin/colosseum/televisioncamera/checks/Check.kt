package colosseum.televisioncamera.checks

import colosseum.televisioncamera.TelevisionCamera
import org.bukkit.entity.Player

abstract class Check {
    val displayName: String

    constructor() : this("Unknown")

    constructor(displayName: String) {
        this.displayName = displayName
    }

    protected fun getPlugin() : TelevisionCamera {
        return TelevisionCamera.getInstance()
    }

    protected fun getCheckManager() : CheckManager {
        return getPlugin().checkManager
    }

    fun name(): String {
        return displayName
    }

    fun getName(): String {
        return displayName
    }

    // TODO: TO BE IMPLEMENTED
    fun getViolationLevel(player: Player): Int {
        return 0
    }
}
