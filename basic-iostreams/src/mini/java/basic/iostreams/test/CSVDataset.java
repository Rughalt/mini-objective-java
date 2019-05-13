package mini.java.basic.iostreams.test;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
 * -
 */
public class CSVDataset implements Serializable {
    private final String filename;
    private final boolean hasHeader;
    private transient final File file;
    private final ArrayList<String> headerList = new ArrayList<>();
    private final ArrayList<Map<String, String>> data = new ArrayList<>();

    /**
     * Creates new CSVDataset instance bound to a file.
     * @param filename name of CSV file
     * @param hasHeader true if CSV file has header. If true, column names are read from header, if false, they are generated as Column <N>.
     * @throws IOException When file could nod be found or read properly
     */
    public CSVDataset(String filename, boolean hasHeader) throws IOException {
        this.filename = filename;
        this.hasHeader = hasHeader;
        this.file = new File(filename);
        loadData(hasHeader, new BufferedReader(new FileReader(this.file)));
    }

    private void loadData(boolean hasHeader, BufferedReader file) throws IOException {
        // Everything can be done here, so they have to only implement one funciton in "worst case"
        if (hasHeader) {
            headerList.addAll(Arrays.asList(file.readLine().split(",")));
        }
        String line;
        while ((line = file.readLine()) != null) {
            LinkedHashMap<String, String> lineMap = new LinkedHashMap<>();
            int index = 0;
            for (String element : line.split(",")) {
                lineMap.put(hasHeader ? headerList.get(index) : String.format("Column %s", 1), element.trim());
                index++;
            }
            data.add(lineMap);
        }
    }

    /**
     * Deserializes object from java binary serialization file. Deserialized object should not be bound to CSV file.
     * @param filename serialized filename
     * @return CSVDataset deserialized object
     * @throws IOException on file read errors
     * @throws ClassNotFoundException if class is not found
     */
    public static CSVDataset deserializeJava(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(filename));
        return (CSVDataset)objectIn.readObject();
    }

    /**
     * Deserialized object from json representation. Deserialized object should not be bound to CSV file.
     * @param filename serialized filename
     * @return CSVDataset deserialized object
     * @throws FileNotFoundException when file is not found
     */
    public static CSVDataset deserializeJson(String filename) throws FileNotFoundException {
        Gson deserializer = new Gson();
        return deserializer.fromJson(new FileReader(filename), CSVDataset.class);
    }

    /**
     * Gets read data size
     * @return read data size (excluding header row if existing)
     */
    public int dataLength() {
        return data.size();
    }


    /**
     * Serialized object to binary java serialization file
     * @param filename serialized filename
     * @throws IOException on filesystem error
     */
    public void serializeJava(String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(this);
        objectOut.close();
    }

    /**
     * Serialized object to binary java serialization file
     * @param filename serialized filename
     * @throws IOException on filesystem error
     */
    public void serializeJson(String filename) throws IOException {
        Gson serializer = new Gson();
        FileWriter writer = new FileWriter(filename);
        serializer.toJson(this, writer);
        writer.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CSVDataset that = (CSVDataset) o;
        return hasHeader == that.hasHeader &&
                Objects.equals(filename, that.filename) &&
                Objects.equals(headerList, that.headerList) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename, hasHeader, headerList, data);
    }

    /**
     * Returns list of values from given row
     * @param row row number to read data from
     * @return values from given row
     */
    public List<String> getValues(int row) {
        return new ArrayList<>(data.get(row).values());
    }

    /**
     * Returns value for given column and row
     * @param row row number to read data from
     * @param column column name to read data from
     * @return value
     */
    public String getValue(String column, int row) {
        return data.get(row).get(column);
    }

    /**
     * Adds new column with data. Data is added to as many rows as it can be
     * (if there are ex. 3 elements in elements array, data is added to first 3 rows)
     * @param columnName new column name
     * @param elements elements to add
     * @return true if added
     */
    public boolean addColumn(String columnName, String... elements) {
        this.headerList.add(columnName);
        for (int dataLine = 0; dataLine < elements.length; dataLine++) {
            data.get(dataLine).put(columnName, elements[dataLine]);
        }
        return true;
    }

    /**
     * Saves CSV data to file. If original data had headers, they should be saved as well.
     * @param filename name of new CSV file
     * @throws FileNotFoundException if file could not be found
     */
    public void saveToFile(String filename) throws FileNotFoundException {
        File fout = new File(filename);
        FileOutputStream fos = new FileOutputStream(fout);
        PrintWriter bw = new PrintWriter(new OutputStreamWriter(fos));
        if (hasHeader) {
            bw.println(String.join(",", headerList));
        }
        for (Map<String, String> dataElement : data) {
            bw.println(String.join(",", dataElement.values()));
        }
        bw.close();
    }

    /**
     * Returns if insance is bound to a file - that means, it was directly created
     * from a file, not by deserialization.
     * @return true if instance was created from a file, false if not
     */
    public boolean isBoundToFile() {
        return this.file != null;
    }

    /**
     * Returns headers if existing, null if not
     * @return read headers if file read with headers, null otherwise
     */
    public List<String> getHeader() {
        return hasHeader ? headerList : null;
    }
}
