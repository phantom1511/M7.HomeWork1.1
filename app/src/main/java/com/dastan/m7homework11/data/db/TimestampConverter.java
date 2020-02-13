package com.dastan.m7homework11.data.db;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import java.util.Date;

public class TimestampConverter {
    @TypeConverter
    public static Date fromRaw(@Nullable Long timestamp) {
        if (timestamp == null) return null;

        return new Date(timestamp);
    }

    @TypeConverter
    public static Long toRaw(@Nullable Date date) {
        if (date == null) return null;

        return date.getTime();
    }
}
