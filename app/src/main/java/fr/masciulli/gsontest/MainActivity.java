package fr.masciulli.gsontest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try (InputStream inputStream = getAssets().open("list.json")) {
            String json = inputStreamToString(inputStream);
            Log.d(TAG, "Input json : " + json);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Animal.class, new AnimalDeserializer())
                    .create();

            Type collectionType = new TypeToken<List<Animal>>(){}.getType();
            List<Animal> animals = gson.fromJson(json, collectionType);

            for (Animal animal : animals) {
                Log.d(TAG, animal.toString());
            }
        } catch (IOException e) {
            Log.e(TAG, "Couldn't open asset file", e);
        }
    }

    private String inputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line).append('\n');
        }
        return total.toString();
    }
}
