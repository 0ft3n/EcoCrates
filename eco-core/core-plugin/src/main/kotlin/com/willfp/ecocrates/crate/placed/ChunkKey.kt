package com.willfp.ecocrates.crate.placed

import kotlin.math.floor
import org.bukkit.Chunk
import org.bukkit.Location

data class ChunkKey(
    val worldName: String,
    val x: Int,
    val z: Int
)

val Chunk.key: ChunkKey
    get() = ChunkKey(this.world.name, this.x, this.z)

val Location.chunkKey: ChunkKey
    get() = ChunkKey(world.name, floor(x).toInt() shr 4, floor(z).toInt() shr 4)
