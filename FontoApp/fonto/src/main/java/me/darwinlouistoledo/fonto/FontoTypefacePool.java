package me.darwinlouistoledo.fonto;

import android.graphics.Typeface;

import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;

import me.darwinlouistoledo.fonto.model.FontoTypeface;

/**
 * Created by darwinlouistoledo on 8/5/16.
 */
class FontoTypefacePool implements FontoInterface{
    private LinkedHashMap<String, WeakReference<Typeface>> typefaceMap;

    public FontoTypefacePool() {
        this.typefaceMap = new LinkedHashMap<>();
    }

    public void addToPool(FontoTypeface fontoTypeface){
        if (!this.typefaceMap.containsKey(fontoTypeface.getName())){
            this.typefaceMap.put(fontoTypeface.getName(), new WeakReference<>(fontoTypeface.getTypeface()));
        } else {
            Logger.writeI("Typeface is already created and added.");
        }
    }

    @Override
    public Typeface getTypeface(String name){
        if(this.typefaceMap.containsKey(name))
            return this.typefaceMap.get(name).get();

        throw new NullPointerException("Typeface is NULL. Typeface with the name of "+name+" is not yet created or added. " +
                "This Font will not going to apply in your view.");
    }

    public void clearPool(){
        this.typefaceMap.clear();
    }
}
