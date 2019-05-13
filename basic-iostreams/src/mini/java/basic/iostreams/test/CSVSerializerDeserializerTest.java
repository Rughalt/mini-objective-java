package mini.java.basic.iostreams.test;

import org.junit.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.List;

public class CSVSerializerDeserializerTest {
    @org.junit.Test
    public void createDummyFile() throws IOException {
        if (!Files.exists(Paths.get("test.csv"))) Files.write(Paths.get("test.csv"),"Place data here".getBytes());
    }

    @org.junit.Test
    public void readDataLength() throws IOException {
        if (!Files.exists(Paths.get("test.csv"))) throw new RuntimeException("Place test.csv file in project root directory");

        CSVDataset csvDataset = new CSVDataset("test.csv", true);
        int dataLength = csvDataset.dataLength();
        CSVDataset csvDatasetNoHeader = new CSVDataset("test.csv", false);
        int dataLengthNoHeader = csvDatasetNoHeader.dataLength();

        // Checks and assertions
        Assert.assertEquals(10, dataLength);
        Assert.assertEquals(11, dataLengthNoHeader);
    }

    @org.junit.Test(expected = IOException.class)
    public void readNonExsitingFile() throws IOException {
        CSVDataset csvDataset = new CSVDataset("nonexisting.csv", true);

    }

    @org.junit.Test
    public void readDataValues() throws IOException {
        if (!Files.exists(Paths.get("test.csv"))) throw new RuntimeException("Place test.csv file in project root directory");

        CSVDataset csvDataset = new CSVDataset("test.csv", true);
        int dataLength = csvDataset.dataLength();
        CSVDataset csvDatasetNoHeader = new CSVDataset("test.csv", false);
        int dataLengthNoHeader = csvDatasetNoHeader.dataLength();

        // Checks and assertions
        int randomLine = (int) (Math.random() * 8);
        List<String> randomTestLineData = List.of(String.format("A%s", randomLine + 1), String.format("B%s", randomLine + 1), String.format("C%s", randomLine + 1), String.format("D%s", randomLine + 1));
        Assert.assertEquals(randomTestLineData, csvDataset.getValues(randomLine));
        Assert.assertEquals(List.of("Kolumna 1","Kolumna 2","Kolumna 3","Kolumna 4"), csvDataset.getHeader());
        Assert.assertEquals(String.format("A%s", randomLine + 1), csvDataset.getValue("Kolumna 1", randomLine));
    }

    @org.junit.Test
    public void saveDataValues() throws IOException {
        if (!Files.exists(Paths.get("test.csv"))) throw new RuntimeException("Place test.csv file in project root directory");
        if (Files.exists(Paths.get("test_saved.csv"))) Files.delete(Paths.get("test_saved.csv"));

        CSVDataset csvDataset = new CSVDataset("test.csv", true);
        boolean columnAdded = csvDataset.addColumn("Kolumna 5", "T1","T2","T3","T4","T5","T6","T7","T8","T9","T10");
        csvDataset.saveToFile("test_saved.csv");
        CSVDataset csvDatasetSaved = new CSVDataset("test_saved.csv", true);

        // Checks and assertions
        int randomLine = (int) (Math.random() * 8);
        List<String> randomTestLineData = List.of(String.format("A%s", randomLine + 1), String.format("B%s", randomLine + 1), String.format("C%s", randomLine + 1), String.format("D%s", randomLine + 1), String.format("T%s", randomLine + 1));
        Assert.assertEquals(randomTestLineData, csvDataset.getValues(randomLine));
        Assert.assertEquals(String.format("T%s", randomLine + 1), csvDataset.getValue("Kolumna 5", randomLine));
        Assert.assertTrue(columnAdded);
    }


    @org.junit.Test
    public void serializeDeserializeJavaSerializableTest() throws IOException, ClassNotFoundException {
        if (Files.exists(Paths.get("java_serialized.bin"))) Files.delete(Paths.get("java_serialized.bin"));

        CSVDataset csvDataset = new CSVDataset("test.csv", true);
        csvDataset.serializeJava("java_serialized.bin");
        CSVDataset deserializedCsvDataset = CSVDataset.deserializeJava("java_serialized.bin");

        // Checks and assertions
        int randomLine = (int) (Math.random() * 8);
        List<String> randomTestLineData = List.of(String.format("A%s", randomLine + 1), String.format("B%s", randomLine + 1), String.format("C%s", randomLine + 1), String.format("D%s", randomLine + 1));
        Assert.assertEquals(csvDataset, deserializedCsvDataset);
        Assert.assertTrue(csvDataset.isBoundToFile());
        Assert.assertFalse(deserializedCsvDataset.isBoundToFile());
        Assert.assertEquals(randomTestLineData, csvDataset.getValues(randomLine));
        Assert.assertEquals(String.format("A%s", randomLine + 1), csvDataset.getValue("Kolumna 1", randomLine));

    }

    @org.junit.Test
    public void serializeDeserializeJsonTest() throws IOException {
        if (Files.exists(Paths.get("json_serialized.json"))) Files.delete(Paths.get("json_serialized.json"));

        CSVDataset csvDataset = new CSVDataset("test.csv", true);
        csvDataset.serializeJson("json_serialized.json");
        CSVDataset deserializedCsvDataset = CSVDataset.deserializeJson("json_serialized.json");

        // Checks and assertions
        int randomLine = (int) (Math.random() * 8);
        List<String> randomTestLineData = List.of(String.format("A%s", randomLine + 1), String.format("B%s", randomLine + 1), String.format("C%s", randomLine + 1), String.format("D%s", randomLine + 1));
        Assert.assertTrue(csvDataset.isBoundToFile());
        Assert.assertFalse(deserializedCsvDataset.isBoundToFile());
        Assert.assertEquals(csvDataset, deserializedCsvDataset);
        Assert.assertEquals(randomTestLineData, csvDataset.getValues(randomLine));
        Assert.assertEquals(String.format("A%s", randomLine + 1), csvDataset.getValue("Kolumna 1", randomLine));
    }
}
