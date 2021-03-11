## tools 我的快速开发库


``` kotlin
    api 'androidx.appcompat:appcompat:1.2.0'
    api 'androidx.constraintlayout:constraintlayout:2.0.4'

    //fragmentation
    api 'com.github.br3ant:SFragmentation:1.7.7'

    //kotlin
    api "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    api "androidx.core:core-ktx:1.3.2"
    api "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
    api "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
    api "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
    api "androidx.lifecycle:lifecycle-reactivestreams-ktx:2.2.0"
    api 'androidx.fragment:fragment-ktx:1.2.5'

    //room
    def room_version = '2.2.6'
    api "androidx.room:room-runtime:$room_version"
    api "androidx.room:room-rxjava2:$room_version"
    api "androidx.room:room-ktx:$room_version"
    kapt "androidx.room:room-compiler:$room_version"

    //rxhttp
    api 'com.ljx.rxhttp:rxhttp:2.5.3'
    kapt 'com.ljx.rxhttp:rxhttp-compiler:2.5.3' //生成RxHttp类，非kotlin项目，请使用annotationProcessor代替kapt
    api 'com.ljx.rxlife:rxlife-coroutine:2.0.0' //管理协程生命周期，页面销毁，关闭请求
    api 'com.ljx.rxlife2:rxlife-rxjava:2.0.0'

    //rx
    api 'io.reactivex.rxjava2:rxjava:2.2.14'
    api 'io.reactivex.rxjava2:rxandroid:2.1.1'

    //okhttp
    api 'com.squareup.okhttp3:okhttp:4.9.0'

    api 'com.github.liangjingkanji:BRV:1.3.13'

    //gson
    api 'com.google.code.gson:gson:2.8.6'

    //状态栏
    api 'com.gyf.immersionbar:immersionbar:3.0.0'
    // 状态栏 kotlin扩展（可选）
    api 'com.gyf.immersionbar:immersionbar-ktx:3.0.0'

    //阴影
    api 'com.github.lihangleo2:ShadowLayout:3.1.3'

    //binding
    api 'com.hi-dhl:binding:1.0.9'

    //util
    api 'com.blankj:utilcodex:1.29.0'
```

