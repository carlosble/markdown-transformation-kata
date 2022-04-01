package mdtransformer;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class TextFileHandler {
    // Idea for kata: implement a reactive version of this class (rxJava, Webflux...)

    public List<String> readLines(String sourceFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();
        return lines;
    }

    public void appendLineToTextFile(String line, String destinationFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile, true));
        writer.append(line);
        writer.append("\n");
        writer.close();
    }
}
