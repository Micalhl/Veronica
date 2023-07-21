package com.mcstarrysky.veronica.draw

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.Schedule
import taboolib.common5.cint
import taboolib.common5.clong
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import java.util.concurrent.ConcurrentHashMap

/**
 * Veronica
 * com.mcstarrysky.veronica.draw.DrawConfig
 *
 * @author Mical
 * @since 2023/7/21 16:11
 */
object DrawConfig {

    val draws = ConcurrentHashMap<String, String>()
    val cache = ConcurrentHashMap<Long, Int>()

    @Config("draw.yml")
    lateinit var config: Configuration
        private set

    @Awake(LifeCycle.LOAD)
    fun initialize() {
        draws.putAll(config.getStringList("draws").associate { source ->
            val (draw, divination) = source.split('|', limit = 2)
            draw to divination
        })
        cache.putAll(config.getStringList("cache").associate { source ->
            val (user, draw) = source.split('|', limit = 2).map { it.clong }
            user to draw.cint
        })
    }

    @Awake(LifeCycle.DISABLE)
    @Schedule(async = true, period = 1000 * 60 * 60L)
    fun save() {
        config["cache"] = cache.map { "${it.key}|${it.value}" }
        config.saveToFile()
    }
}