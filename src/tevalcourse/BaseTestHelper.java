package tevalcourse;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class BaseTestHelper {
    protected static final String SEPARATOR = "----------------------------------";

    protected final static String ROOT_FILE_DIR = System.getProperty("user.dir")
            + System.getProperty("file.separator")
            + "resources";

    protected static List<String> readFile(String fileName) throws IOException {
        try (InputStreamReader inputStreamReader =
                     new InputStreamReader(new FileInputStream(new File(ROOT_FILE_DIR, fileName)));
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            return reader.lines().collect(Collectors.toList());
        }
    }

    protected static void writeFile(String fileName, List<String> lines) throws IOException {
        try (OutputStreamWriter outputStreamWriter =
                     new OutputStreamWriter(new FileOutputStream(new File(ROOT_FILE_DIR, fileName)));
             BufferedWriter writer = new BufferedWriter(outputStreamWriter)) {
            lines.forEach(line -> {
                try {
                    writer.write(line + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
