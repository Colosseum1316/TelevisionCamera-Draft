package colosseum.televisioncamera.api

import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

interface TelevisionLiveLink {
    fun getActiveDisguise(player: Player): EntityType?

    fun isSpectator(player: Player): Boolean

    fun getTPS(): Double

    fun getPing(player: Player): Int

    fun isUsingItem(player: Player): Boolean

    fun allocateNewEntityID(): Int

    fun isUsingElytra(player: Player): Boolean

    fun getLevitationAmplifier(player: Player): Int

    fun canDamage(player: Player, entity: Entity): Boolean

    fun nmsGetCubes(world: World, aabb: AABB, player: Player): Set<AABB>
}
