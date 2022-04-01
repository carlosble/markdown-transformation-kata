package mdtransformer;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class TextFileHandler {
    public List<String> readLines(String sourceFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();
        return lines;
    }

    public void writeLineWithEndingBreak(String line, String destinationFile) throws IOException {
        // TODO: make this more efficient
        BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile, true));
        writer.append(line);
        writer.append("\n");
        writer.close();
    }
}
