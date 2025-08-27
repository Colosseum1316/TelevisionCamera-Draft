package colosseum.televisioncamera.api

import colosseum.televisioncamera.checks.Check
import colosseum.televisioncamera.checks.CheckManager
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

// TODO: Some methods and properties may be missing.
class PlayerViolationEvent(
    val checkClass: Class<out Check>,
    val player: Player,
    val violations: Int
) : Event() {
    fun getHackType(): String {
        return CheckManager.getCheckSimpleName(this.checkClass)
    }

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        val handlerList: HandlerList = HandlerList()
    }
}
