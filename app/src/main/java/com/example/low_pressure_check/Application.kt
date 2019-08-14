package com.example.low_pressure_check

import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.startKoin

class Application : android.app.Application()  {

    override fun onCreate() {
        super.onCreate()

        // DIの設定
        startKoin(this, KoinModule.modules)

        // ThreeTenABPの初期化
        AndroidThreeTen.init(this)
    }
}
