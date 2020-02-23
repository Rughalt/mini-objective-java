package mini.java.basic.iostreams.test;

import java.io.*;
import java.util.*;

/**
 * This class represents simple dataset bound to a CSV file.
 * Your task is to implement methods so tests in CSVSerializerDeserializerTest class pass.
 *
 * You can implement methods in any way you want. Data should be read in such a way that you can retrieve:
 * - header row
 * - data row
 * - data cell (column/row)
 *
 * Hints:
 * - If you look in previous labs code, you should find a lot of inspiration
 * - To serialize data to json you can use Gson library, as it was shown in examples.
 *   To add library to project, use Project Project Structure -> Global Libraries -> + From Maven
 *   and search for com.google.gson package.
 */
public class CSVDataset {
    /**
     * Creates new CSVDataset instance bound to a file.
     * @param filename name of CSV file
     * @param hasHeader true if CSV file has header. If true, column names are read from header,
     *                  if false, they are generated as Column N, where N is column index.
     * @throws IOException When file could nod be found or read properly
     */
    public CSVDataset(String filename, boolean hasHeader) throws IOException {
        throw new RuntimeException("Not implemented! Implement this method!");
    }


    /**
     * Deserializes object from java binary serialization file. Deserialized object should not be bound to CSV file.
     * @param filename serialized filename
     * @return CSVDataset deserialized object
     * @throws IOException on file read errors
     * @throws ClassNotFoundException if class is not found
     */
    public static CSVDataset deserializeJava(String filename) {
        throw new RuntimeException("Not implemented! Implement this method!");
    }

    /**
     * Deserialized object from json representation. Deserialized object should not be bound to CSV file.
     * @param filename serialized filename
     * @return CSVDataset deserialized object
     * @throws FileNotFoundException when file is not found
     */
    public static CSVDataset deserializeJson(String filename) {
        throw new RuntimeException("Not implemented! Implement this method!");
    }

    /**
     * Gets read data size
     * @return read data size (excluding header row if existing)
     */
    public int dataLength() {
        throw new RuntimeException("Not implemented! Implement this method!");
    }


    /**
     * Serialized object to binary java serialization file
     * @param filename serialized filename
     * @throws IOException on filesystem error
     */
    public void serializeJava(String filename) {
        throw new RuntimeException("Not implemented! Implement this method!");
    }

    /**
     * Serialized object to binary java serialization file
     * @param filename serialized filename
     * @throws IOException on filesystem error
     */
    public void serializeJson(String filename) {
        throw new RuntimeException("Not implemented! Implement this method!");
    }

    /**
     * Returns list of values from given row
     * @param row row number to read data from
     * @return values from given row
     */
    public List<String> getValues(int row) {
        throw new RuntimeException("Not implemented! Implement this method!");
    }

    /**
     * Returns value for given column and row
     * @param row row number to read data from
     * @param column column name to read data from
     * @return value
     */
    public String getValue(String column, int row) {
        throw new RuntimeException("Not implemented! Implement this method!");
    }

    /**
     * Adds new column with data. Data is added to as many rows as it can be
     * (if there are ex. 3 elements in elements array, data is added to first 3 rows)
     * @param columnName new column name
     * @param elements elements to add
     * @return true if added
     */
    public boolean addColumn(String columnName, String... elements) {
        throw new RuntimeException("Not implemented! Implement this method!");
    }

    /**
     * Saves CSV data to file. If original data had headers, they should be saved as well.
     * @param filename name of new CSV file
     * @throws FileNotFoundException if file could not be found
     */
    public void saveToFile(String filename) throws FileNotFoundException {
        throw new RuntimeException("Not implemented! Implement this method!");
    }

    /**
     * Returns if insance is bound to a file - that means, it was directly created
     * from a file, not by deserialization.
     * @return true if instance was created from a file, false if not
     */
    public boolean isBoundToFile() {
        throw new RuntimeException("Not implemented! Implement this method!");
    }

    /**
     * Returns headers if existing, null if not
     * @return read headers if file read with headers, null otherwise
     */
    public List<String> getHeader() {
        throw new RuntimeException("Not implemented! Implement this method!");
    }
}
