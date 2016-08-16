/**
 * MIT License
 *
 * Copyright (c) 2016 Darwin Louis Toledo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package me.darwinlouistoledo.fonto;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;

/**
 * Fonto is a Singleton class that loads custom fonts
 * and put it on a pool of typefaces.
 */
public class Fonto {
    private static Fonto sInstance;

    private Context context;
    private FontoBuilder fontoBuilder;

    /**
     * This will initiate the class to access and create fonts.
     * This will also need to call when using the font(s) created.
     *
     * @param context The context of the Application that will use it.
     * @return Instance of Fonto Class
     */
    public static Fonto with(Context context){
        if (sInstance==null)
            sInstance = new Fonto(context.getApplicationContext());

        return sInstance;
    }

    /**
     * Private constructor to make sure that the class will be initiated
     * as Single instance
     *
     * @param context The context of the Application that will use it.
     */
    private Fonto(Context context) {
        this.context= context;
        this.fontoBuilder = new FontoBuilder(this.context);
    }

    /**
     * Method to add the desired font face you want to add and use.
     *
     * @param font_name Name of the font you want to add
     * @param font_file_path Path of the font from Assets folder
     * @return Instance of the Fonto class
     */
    public Fonto addTypeface(String font_name, String font_file_path){
        this.fontoBuilder.addFontoTypeface(font_name, font_file_path);
        return this;
    }

    /**
     * Method to call lastly after the {@link #addTypeface(String, String ) addTypeface} method
     * to finally create the fonts added.
     */
    public void create(){
        this.fontoBuilder.createFonts();
    }

    /**
     * Method the use the font added by its Name.
     *
     * @param name Name of the font added
     * @return {@link Typeface Typeface} object. It could be null if you haven't added the
     *          font in creation. Check the logs with tag FONTO.
     */
    public Typeface useFont(String name){
        return this.fontoBuilder.getTypeface(name);
    }

    /**
     * You can apply the font that you created to a {@link View View} or views inside the
     * {@link android.view.ViewGroup ViewGroup} that has texts.
     *
     * @param view View to apply the font. It could be a {@link android.widget.TextView TextView},
     *              {@link android.widget.Button Button} or {@link android.view.ViewGroup ViewGroup}
     * @param fontName Name of the font you want to use
     */
    public void applyFontToView(View view, String fontName){
        this.fontoBuilder.apllyToView(view, fontName);
    }

    /**
     * Will clear all the fonts added to free-up memory.
     */
    public void clear(){
        this.fontoBuilder.clearFonts();
    }
}
