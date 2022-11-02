# android-aspect-ratio-layout

## Using

1. Import the library into your project.

```groovy
dependencies {
    implementation "com.aureusapps.android:aspect-ratio-layout:1.0.0"
}
```

2. Use the `AspectRatioLayout` in your layout XML.

```xml

<com.aureusapps.android.aspectratiolayout.AspectRatioLayout android:layout_width="match_parent"
    android:layout_height="wrap_content" app:layout_constraintTop_toTopOf="parent">

    <LinearLayout android:layout_width="match_parent" android:layout_height="match_parent"
        android:background="@android:color/holo_red_light" />

    <LinearLayout android:layout_width="match_parent" android:layout_height="match_parent"
        android:layout_marginEnd="100dp" android:background="@android:color/holo_blue_light"
        app:layout_aspectRatio="1.6" />

</com.aureusapps.android.aspectratiolayout.AspectRatioLayout>
```

## Appreciate my work!

If you find this library useful, please consider buying me a coffee.

<a href="https://www.buymeacoffee.com/udarawanasinghe" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>