package com.mcstarrysky.veronica.lib.listener

import com.mcstarrysky.veronica.Veronica
import net.mamoe.mirai.event.SimpleListenerHost
import net.mamoe.mirai.event.globalEventChannel
import taboolib.common.LifeCycle
import taboolib.common.inject.ClassVisitor
import taboolib.common.platform.Awake
import java.util.function.Supplier

/**
 * Veronica
 * com.mcstarrysky.veronica.lib.listener.ListenerRegister
 *
 * @author Mical
 * @since 2023/7/21 17:28
 */
@Awake
class ListenerRegister : ClassVisitor(3) {

    override fun visitEnd(clazz: Class<*>, instance: Supplier<*>?) {
        if (!clazz.isAnnotationPresent(MiraiListener::class.java)) return
        if (!SimpleListenerHost::class.java.isAssignableFrom(clazz)) return
        Veronica.globalEventChannel().registerListenerHost(instance?.get() as? SimpleListenerHost ?: return)
    }

    override fun getLifeCycle(): LifeCycle {
        return LifeCycle.ENABLE
    }
}