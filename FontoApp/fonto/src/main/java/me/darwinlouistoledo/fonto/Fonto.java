package me.darwinlouistoledo.fonto;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;

/**
 * Fonto is a Singleton class that loads custom fonts
 * and put it on a pool of typefaces.
 *
 * @author by darwinlouistoledo on 8/4/16.
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
