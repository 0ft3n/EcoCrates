package com.willfp.ecocrates.crate.placed

import com.willfp.ecocrates.EcoCratesPlugin
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.ChunkUnloadEvent

object HologramBugFixer : Listener {
    @EventHandler
    fun handle(event: ChunkUnloadEvent) {
        val chunk = event.chunk
        for (crate in PlacedCrates.values()) {
            try {
                if (crate.chunkKey == chunk.key) {
                    EcoCratesPlugin.instance.scheduler.runLaterGlobally(2) {
                        crate.handleChunkUnload()
                    }
                }
            } catch (_: UninitializedPropertyAccessException) {}
        }
    }
}
