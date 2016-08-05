Fonto
===================

Fonto is a simple library that helps you to apply custom fonts to your views that has text in your **Android** app.

----------


Gradle configuration
-------------------------------
First, you need to add this gradle configuration in your `build.gradle` file.

```gradle
repositories {
    maven {
        url 'https://dl.bintray.com/darwinlouistoledo/maven/'
    }
}

...

dependencies {
    ...
    compile 'me.darwinlouistoledo:fonto:0.2.0'
    ...
}

```

Usage
---------
First, you need to add the fonts you want to add and the create. You can do it on your `Application` class. See code snippet below.

```java
@Override
public void onCreate() {
        super.onCreate();

        Fonto.with(this)
                .addFontoTypeface(new FontoTypeface(FontNames.FONT_LATO, "fonts/Lato-Regular.ttf"))
                .addFontoTypeface(new FontoTypeface(FontNames.FONT_MONTSERRAT, "fonts/Montserrat-Regular.ttf"))
                .create();
}
```

After creating the font, you can now use the fonts. You can use the font by its name then set the typeface of the view with `setTypeface(Typeface)` method.

```java
TextView textView = (TextView) findViewById(R.id.textView);
textView.setTypeface(Fonto.with(this).useFont(FontNames.FONT_LATO));
```
You can also apply the font to the `View` or `ViewGroup`. If you're applying to a `ViewGroup`, it will look all the views that has the instance of `TextView` and `Button` inside that `ViewGroup` to set the typeface.

```java
LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layoutContainer);
Fonto.with(this).applyFontToView(linearLayout, FontNames.FONT_LATO);
```

Remarks
------------
Any suggestions are welcome to improve the implementation. =)


License
------------
```
MIT License

Copyright (c) 2016 Darwin Louis Toledo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
