## dotsindicator
This library provides a simple implementation for ViewPager widget  dots indicators.


[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/jgodinez/dotsindicator.svg)](https://jitpack.io/#jgodinez/dotsindicator)

![Alt text](/assets/demo.gif?raw=true "dotsindicator")

## Dependency

**Step 1.** Add the JitPack repository to your root build.gradle file
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
   
**Step 2.** Add the dependency
```gradle
dependencies {
    implementation 'com.github.jgodinez:dotsindicator:1.0.0'
}
```

---

## Usage

In your xml:

```xml
    <com.jgodinez.dotsindicator.DotsIndicator
        android:id="@+id/defaultIndicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
```

Then, in your code attach the view pager to the dots indicator component:

```kotlin
// Set the adapter
viewPager.adapter = Adapter
// Attach the view pager
dotsIndicator.attachViewPager(viewPager)
```

---

## Customize

You can custom the dots indicator component by below way:

XML attributes:

* `app:dot_drawable_selected` // selected drawable for current one
* `app:dot_drawable_unselected` // unselected drawable for each dot
* `app:dot_height` // height for each dot
* `app:dot_width` // width for each dot
* `app:dot_margin` // spacing between each dot
* `app:dot_orientation` // orientation of the component; `horizontal` is default
