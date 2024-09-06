package com.willfp.ecocrates.crate.placed

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.scheduling.UnifiedTask

class CrateDisplay(
    private val plugin: EcoPlugin
) {

    fun start(): Collection<UnifiedTask> =  listOf(
        plugin.scheduler.runTimerGlobally(1, 1) { ensureTickingSync() },
        plugin.scheduler.runTimerAsync(1, 1) { tickAsync() }
    )

    private fun ensureTickingSync() {
        for (crate in PlacedCrates.values()) {
            if (crate.tickingTask == null) {
                crate.tickingTask = plugin.scheduler.runTimer(crate.location, 1, 1) {
                    crate.tick()
                }
            }
        }
    }

    private fun tickAsync() {
        for (crate in PlacedCrates.values()) {
            crate.tickAsync()
        }
    }
}