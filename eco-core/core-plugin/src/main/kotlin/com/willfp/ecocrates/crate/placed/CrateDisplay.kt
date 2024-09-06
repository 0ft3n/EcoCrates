package com.willfp.ecocrates.crate.placed

import com.willfp.eco.core.EcoPlugin

class CrateDisplay(
    private val plugin: EcoPlugin
) {
    private var tick = 0

    fun start() {
        plugin.scheduler.runTimerGlobally(1, 1) { tick() }
        plugin.scheduler.runTimerAsync(1, 1) { tickAsync() }
    }

    private fun tick() {
        for (crate in PlacedCrates.values()) {
            crate.tickingTask?.cancel()
            crate.tickingTask = plugin.scheduler.runTimer(crate.location, 1, 1) {
                crate.tick(tick)
            }
        }

        tick++
    }

    private fun tickAsync() {
        for (crate in PlacedCrates.values()) {
            crate.tickAsync(tick)
        }
    }
}