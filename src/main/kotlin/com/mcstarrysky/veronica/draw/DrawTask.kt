package com.mcstarrysky.veronica.draw

import taboolib.common.platform.Schedule
import taboolib.common.platform.function.submitAsync
import java.text.SimpleDateFormat
import java.util.*

/**
 * Veronica
 * com.mcstarrysky.veronica.draw.DrawTask
 *
 * @author Mical
 * @since 2023/7/21 16:27
 */
object DrawTask {

    private val date = listOf(
        "4:59:55",
        "4:59:56",
        "4:59:57",
        "4:59:58",
        "4:59:59",
        "5:00:00",
        "5:00:01",
        "5:00:02",
        "5:00:03",
        "5:00:04",
        "5:00:05",
    )
    private val format = SimpleDateFormat("HH:mm:ss")
    private var refreshed = false

    @Schedule(async = true, period = 1000L)
    fun refresh() {
        // 每天凌晨五点左右刷新用户缓存数据
        if (format.format(Date()) in date) {
            DrawConfig.cache.clear()
            refreshed = true
        }
        if (refreshed) {
            submitAsync(delay = 15000L) {
                refreshed = false
            }
        }
    }
}