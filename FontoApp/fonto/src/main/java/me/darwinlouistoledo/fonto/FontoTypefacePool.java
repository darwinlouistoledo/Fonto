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

import android.graphics.Typeface;

import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;

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
