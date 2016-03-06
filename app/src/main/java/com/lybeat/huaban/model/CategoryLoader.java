package com.lybeat.huaban.model;

import android.content.Context;

import com.lybeat.huaban.R;

import java.util.ArrayList;

/**
 * Author: lybeat
 * Date: 2016/3/6
 */
public class CategoryLoader {

    private Context context;

    public CategoryLoader(Context context) {
        this.context = context;
    }

    public ArrayList<Category> loadCategory() {
        ArrayList<Category> categories = new ArrayList<>();
        int resIds[] = new int[] {
                R.drawable.avatar,
                R.drawable.avatar,
                R.drawable.avatar,
                R.drawable.avatar,
                R.drawable.avatar,
                R.drawable.avatar,
                R.drawable.avatar,
                R.drawable.avatar,
                R.drawable.avatar,
                R.drawable.avatar,
                R.drawable.avatar,
        };
        String[] names = context.getResources().getStringArray(R.array.category_item);
        String[] urls = new String[] {
                "http://blog.huaban.com/favorite/food_drink/",
                "http://blog.huaban.com/favorite/travel_places/",
                "http://blog.huaban.com/favorite/diy_crafts/",
                "http://blog.huaban.com/favorite/pets/",
                "http://blog.huaban.com/favorite/desire/",
                "http://blog.huaban.com/favorite/geek/",
                "http://blog.huaban.com/favorite/anime/",
                "http://blog.huaban.com/favorite/games/",
                "http://blog.huaban.com/favorite/architecture/",
                "http://blog.huaban.com/favorite/film_music_books/",
                "http://blog.huaban.com/favorite/funny/",
        };
        for (int i=0; i<resIds.length; i++) {
            Category category = new Category(resIds[i], names[i], urls[i]);
            categories.add(category);
        }

        return categories;
    }
}
