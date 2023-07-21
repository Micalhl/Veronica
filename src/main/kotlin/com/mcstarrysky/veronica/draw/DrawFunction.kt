package com.mcstarrysky.veronica.draw

import net.mamoe.mirai.contact.Friend
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.at
import taboolib.common.util.random

/**
 * Veronica
 * com.mcstarrysky.veronica.draw.DrawFunction
 *
 * @author Mical
 * @since 2023/7/21 16:23
 */
suspend fun Member.draw() {
    val random = DrawConfig.cache.computeIfAbsent(id) { random(1, DrawConfig.draws.size) }
    val draw = DrawConfig.draws.keys().toList()[random - 1]
    group.sendMessage("""
        ${at().getDisplay(group)}
        第[${random}签]:
        $draw
    """.trimIndent())
}

suspend fun Friend.draw() {
    val random = DrawConfig.cache.computeIfAbsent(id) { random(1, DrawConfig.draws.size) }
    val draw = DrawConfig.draws.keys().toList()[random - 1]
    sendMessage("""
        第[${random}签]:
        $draw
    """.trimIndent())
}

suspend fun Member.divination() {
    val random = DrawConfig.cache.computeIfAbsent(id) { random(1, DrawConfig.draws.size) }
    val divination = DrawConfig.draws[DrawConfig.draws.keys().toList()[random - 1]] ?: return
    group.sendMessage("""
        ${at().getDisplay(group)}的解签:
        $divination
    """.trimIndent())
}

suspend fun Friend.divination() {
    val random = DrawConfig.cache.computeIfAbsent(id) { random(1, DrawConfig.draws.size) }
    val divination = DrawConfig.draws[DrawConfig.draws.keys().toList()[random - 1]] ?: return
    sendMessage("""
        你的解签:
        $divination
    """.trimIndent())
}