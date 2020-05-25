package mini.java.basic.collections.test.solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    /**
     * DataLoader loads data from file. Data is saved in csv files in format
     * table;field1;field2;field3... Data is filtered using @dataFileName, and if no
     * line is found, function should rise NoDataException. Loaded data is converted to Object[]
     * by splitting against the ";"
     * @param dataFileName data file name/path
     * @param dataset name of dataset to load from file
     * @return loaded data, in form of List of Object[]
     * @throws IOException Bubbled IOException
     * @throws NoDataException No data in file for dataset
     */
    public List<Object[]> getData(String dataFileName, String dataset) throws IOException, NoDataException {
        List<Object[]> dataSetObjects = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(dataFileName));
        String line = reader.readLine();
        while (line != null) {
            if (line.startsWith(dataset))
                dataSetObjects.add(line.split(";"));
            line = reader.readLine();
        }
        if (dataSetObjects.size() == 0)
            throw new NoDataException();
        reader.close();
        return dataSetObjects;
    }
}
