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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

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
