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
                R.drawable.ic_category_food,
                R.drawable.ic_category_travel,
                R.drawable.ic_category_handcraft,
                R.drawable.ic_category_pet,
                R.drawable.ic_category_gift,
                R.drawable.ic_category_geek,
                R.drawable.ic_category_anime,
                R.drawable.ic_category_game,
                R.drawable.ic_category_building,
                R.drawable.ic_category_movie,
                R.drawable.ic_category_funny,
        };
        String[] names = context.getResources().getStringArray(R.array.category_item);
        String[] urls = new String[] {
                "http://huaban.com/favorite/food_drink/",
                "http://huaban.com/favorite/travel_places/",
                "http://huaban.com/favorite/diy_crafts/",
                "http://huaban.com/favorite/pets/",
                "http://huaban.com/favorite/desire/",
                "http://huaban.com/favorite/geek/",
                "http://huaban.com/favorite/anime/",
                "http://huaban.com/favorite/games/",
                "http://huaban.com/favorite/architecture/",
                "http://huaban.com/favorite/film_music_books/",
                "http://huaban.com/favorite/funny/",
        };
        for (int i=0; i<resIds.length; i++) {
            Category category = new Category(resIds[i], names[i], urls[i]);
            categories.add(category);
        }

        return categories;
    }
}
