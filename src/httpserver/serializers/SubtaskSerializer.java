package httpserver.serializers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import tasks.SubTask;

import java.io.IOException;

public class SubtaskSerializer extends TypeAdapter<SubTask> {
    @Override
    public void write(JsonWriter jsonWriter, SubTask subTask) throws IOException {
        jsonWriter.value(subTask.toString());
    }

    @Override
    public SubTask read(JsonReader jsonReader) throws IOException {
        return SubTask.formString(jsonReader.nextString());
    }
}
