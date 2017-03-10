package com.quinn.mylibrary.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/3/10.
 */

public class TintDrawableUtil {

    /**
     * @param drawable
     * @param res
     * @return
     */
    public static Drawable tintDrawable(Drawable drawable, ColorStateList res) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, res);
        return wrappedDrawable;
    }

    /**
     * 直接获得到 EditText 的 mCursorDrawableRes，然后通过这个 id 获取到对应的 Drawable，调用我们的着色函数 tintDrawable，然后设置进去
     *
     * @param editText
     * @param color
     */
    public static void tintCursorDrawable(EditText editText, int color) {
        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field mEditor = TextView.class.getDeclaredField("mEditor");
            mEditor.setAccessible(true);
            Object editor = mEditor.get(editText);
            Class<?> aClass = editor.getClass();
            Field mCursorDrawable = aClass.getDeclaredField("mCursorDrawable");
            mCursorDrawable.setAccessible(true);

            if (mCursorDrawableRes <= 0) {
                return;
            }

            Drawable cursorDrawable = editText.getContext().getResources().getDrawable(mCursorDrawableRes);
            if (cursorDrawable == null) {
                return;
            }

            Drawable tintDrawable = tintDrawable(cursorDrawable, ColorStateList.valueOf(color));
            Drawable[] drawables = {tintDrawable, tintDrawable};
            mCursorDrawable.set(editor, drawables);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
