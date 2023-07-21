package com.mcstarrysky.veronica.choose

import com.mcstarrysky.veronica.lib.listener.MiraiListener
import net.mamoe.mirai.event.EventHandler
import net.mamoe.mirai.event.SimpleListenerHost
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.at
import net.mamoe.mirai.message.data.content
import taboolib.common.util.random

/**
 * Veronica
 * com.mcstarrysky.veronica.choose.ChooseListener
 *
 * @author Mical
 * @since 2023/7/21 20:29
 */
@MiraiListener
object ChooseListener : SimpleListenerHost() {

    @EventHandler
    suspend fun GroupMessageEvent.onMessage() {
        if (!message.content.startsWith("@${bot.id} ")) return
        val content = message.content.removePrefix("@${bot.id} ")
        if (!content.contains("还是")) return
        if (content == "还是") return
        if (content.startsWith("还是") || content.endsWith("还是")) return
        val select = if (random(100) <= 50) "前者" else "后者"
        group.sendMessage("${sender.at().getDisplay(group)} 选$select!")
    }
}