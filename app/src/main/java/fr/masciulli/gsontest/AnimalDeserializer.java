package fr.masciulli.gsontest;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class AnimalDeserializer implements JsonDeserializer<Animal> {
    private static final String TYPE = "type";
    private static final String TYPE_DOG = "dog";
    private static final String TYPE_BIRD = "bird";

    private Gson gson = new Gson();

    @Override
    public Animal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        String type = object.get(TYPE).getAsString();

        switch (type) {
            case TYPE_DOG:
                return gson.fromJson(object, Dog.class);
            case TYPE_BIRD:
                return gson.fromJson(object, Bird.class);
            default:
                throw new IllegalArgumentException("Unknown type : " + type);
        }
    }
}
