package me.darwinlouistoledo.fonto;

import android.graphics.Typeface;

/**
 * Class Model that will contains the Name and File path of the FontFace.
 *
 * @author  by darwinlouistoledo on 8/4/16.
 */
class FontoTypeface {
    private String name;
    private String fontFile;
    private Typeface typeface;

    /**
     * Contructor that needs to provide the Name and Font File Path.
     *
     * @param name Name of the Font
     * @param fontFile File path of the font from the Assets folder.
     */
    public FontoTypeface(String name, String fontFile) {
        this.name = name;
        this.fontFile = fontFile;
    }

    public String getName() {
        return name;
    }

    public String getFontFile() {
        return fontFile;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }
}
