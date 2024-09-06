package com.willfp.ecocrates.crate.placed

import com.willfp.eco.core.scheduling.UnifiedTask
import com.willfp.ecocrates.EcoCratesPlugin
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.ChunkLoadEvent
import org.bukkit.event.world.ChunkUnloadEvent

object HologramBugFixer : Listener {
    @EventHandler
    fun handle(event: ChunkUnloadEvent) {
        val chunk = event.chunk
        EcoCratesPlugin.instance.scheduler.runLaterGlobally(2) {
            for (crate in PlacedCrates.values()) {
                try {
                    if (crate.chunkKey == chunk.key) {
                        crate.handleChunkUnload()
                    }
                } catch (_: UninitializedPropertyAccessException) {}
            }
        }
    }
}
