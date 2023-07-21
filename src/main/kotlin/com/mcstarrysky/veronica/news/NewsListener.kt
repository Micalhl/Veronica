package com.mcstarrysky.veronica.news

import com.mcstarrysky.veronica.lib.listener.MiraiListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.event.EventHandler
import net.mamoe.mirai.event.SimpleListenerHost
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content
import java.net.HttpURLConnection
import java.net.URL

/**
 * Veronica
 * com.mcstarrysky.veronica.news.NewsListener
 *
 * @author Mical
 * @since 2023/7/21 20:41
 */
@MiraiListener
object NewsListener : SimpleListenerHost() {

    @EventHandler
    suspend fun GroupMessageEvent.onMessage() {
        if (message.content == ".getNews" || message.content == ".news" || message.content == ".新闻") {
            val http = withContext(Dispatchers.IO) {
                URL("https://api.03c3.cn/zb").openConnection()
            } as HttpURLConnection
            group.sendImage(http.inputStream)
        }
    }
}