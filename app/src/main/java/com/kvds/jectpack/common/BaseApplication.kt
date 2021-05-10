package com.kvds.jectpack.common

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
 #                                                   #
 #                       _oo0oo_                     #
 #                      o8888888o                    #
 #                      88" . "88                    #
 #                      (| -_- |)                    #
 #                      0\  =  /0                    #
 #                    ___/`---'\___                  #
 #                  .' \\|     |# '.                 #
 #                 / \\|||  :  |||# \                #
 #                / _||||| -:- |||||- \              #
 #               |   | \\\  -  #/ |   |              #
 #               | \_|  ''\---/''  |_/ |             #
 #               \  .-\__  '-'  ___/-. /             #
 #             ___'. .'  /--.--\  `. .'___           #
 #          ."" '<  `.___\_<|>_/___.' >' "".         #
 #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 #                       `=---='                     #
 #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 #                                                   #
 #               佛祖保佑        永无BUG               #
 #                                                   #
 */

@HiltAndroidApp
class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}