package httpserver.serializers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import tasks.Epic;

import java.io.IOException;

public class EpicSerializer extends TypeAdapter<Epic> {

    @Override
    public void write(JsonWriter jsonWriter, Epic epic) throws IOException {
        jsonWriter.value(epic.toString());
    }

    @Override
    public Epic read(JsonReader jsonReader) throws IOException {
        return Epic.formString(jsonReader.nextString());
    }
}
