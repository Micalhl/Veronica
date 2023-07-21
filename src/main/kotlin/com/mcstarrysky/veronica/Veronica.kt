package com.mcstarrysky.veronica

import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import taboolib.common.TabooLibCommon
import taboolib.common.platform.function.info
import taboolib.platform.AppIO

object Veronica : KotlinPlugin(JvmPluginDescription.loadFromResource()) {

    override fun PluginComponentStorage.onLoad() {
        // 设置数据目录
        AppIO.nativeDataFolder = configFolder
        // 初始化 TabooLib
        TabooLibCommon.testSetup()
    }

    override fun onEnable() {
        info("Successfully running Veronica!")
    }

    override fun onDisable() {
        // 卸载 TabooLib
        TabooLibCommon.testCancel()
    }
}