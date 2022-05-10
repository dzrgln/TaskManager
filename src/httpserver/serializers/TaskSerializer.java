package httpserver.serializers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import tasks.Task;

import java.io.IOException;

public class TaskSerializer extends TypeAdapter<Task> {
    @Override
    public void write(JsonWriter jsonWriter, Task task) throws IOException {
        jsonWriter.value(task.toString());
    }

    @Override
    public Task read(JsonReader jsonReader) throws IOException {
        return Task.formString(jsonReader.nextString());
    }
}
