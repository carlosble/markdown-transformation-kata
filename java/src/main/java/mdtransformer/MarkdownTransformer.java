package mdtransformer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MarkdownTransformer {
    public void turnLinksIntoFootnotes(String sourceFile, String destinationFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile));
        writer.write("some link [^anchor1]\n");
        writer.write("[^anchor1]: http://test");
        writer.close();
    }
}
