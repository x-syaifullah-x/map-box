# Android Map Box

implementation "com.github.x-syaifullah-x.android-map-box:map_box:0.0.1"

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(MapBoxModule.modules)
        }