package com.mcstarrysky.veronica.draw

import com.mcstarrysky.veronica.lib.listener.MiraiListener
import net.mamoe.mirai.event.EventHandler
import net.mamoe.mirai.event.SimpleListenerHost
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content

/**
 * Veronica
 * com.mcstarrysky.veronica.draw.DrawListener
 *
 * @author Mical
 * @since 2023/7/21 16:15
 */
@MiraiListener
object DrawListener : SimpleListenerHost() {

    @EventHandler
    suspend fun GroupMessageEvent.onMessage() {
        when (message.content) {
            ".抽签" -> sender.draw()
            ".解签" -> sender.divination()
        }
    }

    @EventHandler
    suspend fun FriendMessageEvent.onMessage() {
        when (message.content) {
            ".抽签" -> friend.draw()
            ".解签" -> friend.divination()
        }
    }
}