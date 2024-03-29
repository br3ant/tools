## tools 我的快速开发库


``` kotlin
    //androidx
    api 'androidx.appcompat:appcompat:1.3.1'
    api 'com.google.android.material:material:1.4.0'
    api 'androidx.constraintlayout:constraintlayout:2.0.4'
    api 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
    api 'androidx.cardview:cardview:1.0.0'
    api "androidx.viewpager2:viewpager2:1.0.0"
    api 'androidx.recyclerview:recyclerview:1.2.0'
    api "androidx.core:core-ktx:1.6.0"
    api "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.30"
    api "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
    api "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"
    api "androidx.lifecycle:lifecycle-reactivestreams-ktx:2.3.1"
    api 'androidx.lifecycle:lifecycle-common-java8:2.3.1'
    api 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.1'
    api 'androidx.lifecycle:lifecycle-service:2.3.1'
    api 'androidx.activity:activity-ktx:1.3.1'
    api 'androidx.fragment:fragment-ktx:1.3.6'

    //fragmentation
    api 'com.github.br3ant:SFragmentation:1.8.5'

    //协程
    def coroutines_version = '1.5.1'
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    //room
    def room_version = '2.3.0'
    api "androidx.room:room-runtime:$room_version"
    api "androidx.room:room-rxjava2:$room_version"
    api "androidx.room:room-ktx:$room_version"
    kapt "androidx.room:room-compiler:$room_version"

    //rxhttp
    //http
    api 'com.github.liujingxing.rxhttp:rxhttp:2.6.4'
    api 'com.github.liujingxing.rxlife:rxlife-coroutine:2.2.0' //管理协程生命周期，页面销毁，关闭请求
    api 'com.github.liujingxing.rxlife:rxlife-rxjava2:2.2.0' //管理RxJava2生命周期，页面销毁，关闭请求
    kapt 'com.github.liujingxing.rxhttp:rxhttp-compiler:2.6.8'
    //生成RxHttp类，非kotlin项目，请使用annotationProcessor代替kapt


    //rx
    api 'io.reactivex.rxjava2:rxjava:2.2.14'
    api 'io.reactivex.rxjava2:rxandroid:2.1.1'

    //okhttp
    api 'com.squareup.okhttp3:okhttp:4.9.1'

    api 'com.github.liangjingkanji:BRV:1.3.22'

    //gson
    api 'com.google.code.gson:gson:2.8.7'

    //状态栏
    api 'com.gyf.immersionbar:immersionbar:3.0.0'
    // 状态栏 kotlin扩展（可选）
    api 'com.gyf.immersionbar:immersionbar-ktx:3.0.0'

    //阴影
    api 'com.github.lihangleo2:ShadowLayout:3.1.3'

    //binding
    api 'com.hi-dhl:binding:1.1.3'

    //util
    api 'com.blankj:utilcodex:1.30.0'
```

