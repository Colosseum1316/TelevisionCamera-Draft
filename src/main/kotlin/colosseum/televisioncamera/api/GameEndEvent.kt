package colosseum.televisioncamera.api

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class GameEndEvent : Event() {
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        val handlerList: HandlerList = HandlerList()
    }
}
