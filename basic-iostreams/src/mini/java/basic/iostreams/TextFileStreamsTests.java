package mini.java.basic.iostreams;


import mini.java.basic.interfaces.test.StringRandomizer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextFileStreamsTests {

    @org.junit.Test
    public void createTestTextFileTest() throws IOException {
        if (Files.exists(Paths.get("test.txt"))) Files.delete(Paths.get("test.txt"));
        File fout = new File("test.txt");
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        bw.write("Test");
        bw.newLine();
        bw.write("Abc");
        bw.close();
    }

    @org.junit.Test
    public void createTestTextFileTestPrintWriter() throws IOException {
        if (Files.exists(Paths.get("test.txt"))) Files.delete(Paths.get("test.txt"));
        File fout = new File("test.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        PrintWriter bw = new PrintWriter(new OutputStreamWriter(fos));
        bw.println("Test");
        bw.println("Abc 2");
        bw.close();
    }

    @org.junit.Test
    public void readTestTextFileTest() throws IOException {
        if (Files.exists(Paths.get("test.txt"))) Files.delete(Paths.get("test.txt"));
        File fout = new File("test.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        StringRandomizer stringRandomizer = new StringRandomizer(10);

        for (int i = 0; i < 100000; i++) {
            bw.write(stringRandomizer.next(1000));
            bw.newLine();
        }
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        while (br.read() != -1) {
        }
    }

    @org.junit.Test
    public void readTestTextFileUnbufferedTest() throws IOException {
        if (Files.exists(Paths.get("test.txt"))) Files.delete(Paths.get("test.txt"));
        File fout = new File("test.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        StringRandomizer stringRandomizer = new StringRandomizer(10);

        for (int i = 0; i < 100000; i++) {
            bw.write(stringRandomizer.next(1000));
            bw.newLine();
        }
        bw.close();

        FileReader fr = new FileReader("test.txt");
        while (fr.read() != -1) {
        }
    }
}
