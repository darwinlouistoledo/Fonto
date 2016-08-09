package me.darwinlouistoledo.fonto;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  by darwinlouistoledo on 8/4/16.
 */
class FontoBuilder implements FontoInterface {
    private List<WeakReference<FontoTypeface>> fontoTypeFaces;
    private Context context;
    private FontoTypefacePool fontoTypefacePool;

    public FontoBuilder(Context context) {
        this.context=context;
        fontoTypeFaces = new ArrayList<>();
        fontoTypefacePool = new FontoTypefacePool();
    }

    public void createFonts() {
        for (WeakReference<FontoTypeface> wr: fontoTypeFaces){
            FontoTypeface ft = wr.get();
            try{
                Typeface typeface = Typeface.createFromAsset(this.context.getAssets(), ft.getFontFile());
                ft.setTypeface(typeface);
                this.fontoTypefacePool.addToPool(ft);
            }catch (Exception e){
                Logger.writeE(e.getMessage());
            }
        }
    }

    public void addFontoTypeface(String name, String file_path) {
        WeakReference<FontoTypeface> wrFontoTypeface = new WeakReference<>(new FontoTypeface(name, file_path));
        fontoTypeFaces.add(wrFontoTypeface);
    }

    @Override
    public Typeface getTypeface(String name) {
        try {
            return this.fontoTypefacePool.getTypeface(name);
        } catch (NullPointerException npe){
            Logger.writeE(npe.getMessage());
            return null;
        }
    }

    public void apllyToView(View view, String fontName){
        if (view instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for(int i = 0; i < count; i++) {
                View v = viewGroup.getChildAt(i);
                if(v instanceof TextView){
                    ((TextView)v).setTypeface(getTypeface(fontName));
                }else if (v instanceof Button){
                    ((Button)v).setTypeface(getTypeface(fontName));
                }else if(v instanceof ViewGroup){
                    apllyToView(v, fontName);
                }
            }
        } else {
            if(view instanceof TextView){
                ((TextView)view).setTypeface(getTypeface(fontName));
            }else if (view instanceof Button){
                ((Button)view).setTypeface(getTypeface(fontName));
            }
        }
    }

    public void clearFonts(){
        this.fontoTypefacePool.clearPool();
    }
}
