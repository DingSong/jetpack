package com.kvds.jectpack.model

@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class NewsType {
    companion object {
        const val TOP: String = "top"                   // 头条
        const val NATIONAL: String = "guonei"           // 国内
        const val INTERNATIONAL: String = "guoji"       // 国际
        const val ENTERTAINMENT: String = "yule"        // 娱乐
        const val SPORT: String = "tiyu"                // 体育
        const val MILITARY: String = "junshi"           // 军事
        const val TECHNOLOGY: String = "keji"           // 科技
        const val FINANCE: String = "caijing"           // 财经
        const val FASHION: String = "shishang"          // 时尚
        const val GAME: String = "youxi"                // 游戏
        const val CAR: String = "qiche"                 // 汽车
        const val HEALTH: String = "jiankang"           // 健康
    }
}