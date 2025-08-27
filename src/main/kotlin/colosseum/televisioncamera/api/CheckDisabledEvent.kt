package colosseum.televisioncamera.api

import colosseum.televisioncamera.checks.Check
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class CheckDisabledEvent(val checkClass: Class<out Check>) : Event() {
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        val handlerList: HandlerList = HandlerList()
    }
}
