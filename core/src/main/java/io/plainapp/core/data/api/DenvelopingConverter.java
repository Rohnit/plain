package io.plainapp.core.data.api;

import com.google.gson.Gson;

import androidx.annotation.NonNull;
import retrofit2.Converter;

public class DenvelopingConverter extends Converter.Factory {

    final Gson gson;

    public DenvelopingConverter(@NonNull Gson gson) {
        this.gson = gson;
    }



}
