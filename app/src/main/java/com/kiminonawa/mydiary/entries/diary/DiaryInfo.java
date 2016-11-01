package com.kiminonawa.mydiary.entries.diary;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.shared.ViewTools;

/**
 * Created by daxia on 2016/10/31.
 */

public class DiaryInfo {

    public final static int WEATHER_SIZE = 6;
    public final static int WEATHER_SUNNY = 0;
    public final static int WEATHER_CLOUD = 1;
    public final static int WEATHER_WINDY = 2;
    public final static int WEATHER_RAINY = 3;
    public final static int WEATHER_SNOWY = 4;
    public final static int WEATHER_FOGGY = 5;


    public final static int MOOD_SIZE = 3;
    public final static int MOOD_HAPPY = 0;
    public final static int MOOD_SOSO = 1;
    public final static int MOOD_UNHAPPY = 2;


    /**
     * Weather
     */
    public static Drawable getWeathetrDrawable(Context context, int weather) {
        Drawable drawable = null;
        switch (weather) {
            default:
                drawable = ViewTools.getDrawable(context, R.drawable.ic_weather_sunny);
                break;
            case WEATHER_CLOUD:
                drawable = ViewTools.getDrawable(context, R.drawable.ic_weather_cloud);
                break;
            case WEATHER_WINDY:
                drawable = ViewTools.getDrawable(context, R.drawable.ic_weather_windy);
                break;
            case WEATHER_RAINY:
                drawable = ViewTools.getDrawable(context, R.drawable.ic_weather_rainy);
                break;
            case WEATHER_SNOWY:
                drawable = ViewTools.getDrawable(context, R.drawable.ic_weather_snowy);
                break;
            case WEATHER_FOGGY:
                drawable = ViewTools.getDrawable(context, R.drawable.ic_weather_foggy);
                break;
        }
        return drawable;
    }


    public static Integer[] getWeatherArray() {
        return new Integer[]{R.drawable.ic_weather_sunny, R.drawable.ic_weather_cloud,
                R.drawable.ic_weather_windy, R.drawable.ic_weather_rainy, R.drawable.ic_weather_snowy,
                R.drawable.ic_weather_foggy};
    }


    /**
     * Mood
     */
    public static Drawable getMoodDrawable(Context context, int mood) {
        Drawable drawable = null;
        switch (mood) {
            default:
                drawable = ViewTools.getDrawable(context, R.drawable.ic_mood_happy);
                break;
            case MOOD_SOSO:
                drawable = ViewTools.getDrawable(context, R.drawable.ic_mood_soso);
                break;
            case MOOD_UNHAPPY:
                drawable = ViewTools.getDrawable(context, R.drawable.ic_mood_unhappy);
                break;
        }
        return drawable;
    }

    public static Integer[] getMoodArray() {
        return new Integer[]{R.drawable.ic_mood_happy, R.drawable.ic_mood_soso,
                R.drawable.ic_mood_unhappy};
    }


}
