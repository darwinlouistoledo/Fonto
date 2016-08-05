package me.darwinlouistoledo.fontoapp;

import android.app.Application;

import me.darwinlouistoledo.fonto.Fonto;
import me.darwinlouistoledo.fonto.model.FontoTypeface;

/**
 * Created by darwinlouistoledo on 8/5/16.
 */
public class FontoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fonto.with(this)
                .addFontoTypeface(new FontoTypeface(FontNames.FONT_LATO, "fonts/Lato-Regular.ttf"))
                .addFontoTypeface(new FontoTypeface(FontNames.FONT_MONTSERRAT, "fonts/Montserrat-Regular.ttf"))
                .create();
    }
}
