package mini.java.basic.iostreams;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class SerializationTests {

    @org.junit.Test
    public void serializeDeserializeJavaSerializableTest() throws IOException, ClassNotFoundException {
        if (Files.exists(Paths.get("java_serialized.bin"))) Files.delete(Paths.get("java_serialized.bin"));
        SerializableObject object = new SerializableObject();
        try (FileOutputStream fileOut = new FileOutputStream("java_serialized.bin"); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(object);
        }

        ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("java_serialized.bin"));
        SerializableObject deserialized = (SerializableObject)objectIn.readObject();
        Assert.assertEquals(object, deserialized);
    }

    @org.junit.Test
    public void serializeDeserializeJsonTest() throws IOException {
        if (Files.exists(Paths.get("gson_serialized.json"))) Files.delete(Paths.get("gson_serialized.json"));
        Gson serializer = new Gson();
        SerializableObject object = new SerializableObject();
        FileWriter writer = new FileWriter("gson_serialized.json");
        serializer.toJson(object, writer);
        writer.close();

        Gson deserializer = new Gson();
        SerializableObject deserialized = deserializer.fromJson(new FileReader("gson_serialized.json"), SerializableObject.class);
        Assert.assertEquals(object, deserialized);
    }


    @org.junit.Test
    public void serializeDeserializeJavaSerializableTransientTest() throws IOException, ClassNotFoundException {
        if (Files.exists(Paths.get("java_serialized.bin"))) Files.delete(Paths.get("java_serialized.bin"));
        SerializableObjectWithTransients object = new SerializableObjectWithTransients();
        FileOutputStream fileOut = new FileOutputStream("java_serialized.bin");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(object);
        objectOut.close();

        ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("java_serialized.bin"));
        SerializableObjectWithTransients deserialized = (SerializableObjectWithTransients)objectIn.readObject();
        Assert.assertEquals(object.getIntegerField(), deserialized.getIntegerField());
        Assert.assertEquals(object.getStringField(), deserialized.getStringField());
        Assert.assertNull(deserialized.getStringList());
    }

    @org.junit.Test
    public void serializeDeserializeJsonTransientTest() throws IOException {
        if (Files.exists(Paths.get("gson_serialized.json"))) Files.delete(Paths.get("gson_serialized.json"));
        Gson serializer = new Gson();
        SerializableObjectWithTransients object = new SerializableObjectWithTransients();
        FileWriter writer = new FileWriter("gson_serialized.json");
        serializer.toJson(object, writer);
        writer.close();

        Gson deserializer = new Gson();
        SerializableObjectWithTransients deserialized = deserializer.fromJson(new FileReader("gson_serialized.json"), SerializableObjectWithTransients.class);
        Assert.assertEquals(object.getIntegerField(), deserialized.getIntegerField());
        Assert.assertEquals(object.getStringField(), deserialized.getStringField());
        Assert.assertNull(deserialized.getStringList());
    }

    @org.junit.Test
    public void serializeDeserializeJsonMapTest() throws IOException {
        if (Files.exists(Paths.get("gson_serialized.json"))) Files.delete(Paths.get("gson_serialized.json"));
        Gson serializer = new Gson();
        SerializableObject object = new SerializableObject();
        FileWriter writer = new FileWriter("gson_serialized.json");
        serializer.toJson(object, writer);
        writer.close();

        Gson deserializer = new Gson();
        Map<Object,Object> deserialized = deserializer.fromJson(new FileReader("gson_serialized.json"), LinkedHashMap.class);

    }
}
