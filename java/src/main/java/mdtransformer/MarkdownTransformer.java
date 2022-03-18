package mdtransformer;

import java.io.IOException;
import java.util.List;

public class MarkdownTransformer {
    private final TextFileHandler textFileHandler;
    private final Transformations transformations;

    public MarkdownTransformer(TextFileHandler textFileHandler, Transformations transformations) {
        this.textFileHandler = textFileHandler;
        this.transformations = transformations;
    }

    public void turnLinksIntoFootnotes(String sourceFile, String destinationFile) throws IOException {
        List<String> lines = textFileHandler.readLines();
        textFileHandler.writeLines(transformations.linkToFootNote(lines.get(0)));

        //[some link](url)
        //"some link [^anchor1]", "[^anchor1]: url"
        //List<MarkdownLink> link = MarkdownLink.from(lines.get(0));

        //textFileHandler.writeLines(link.get(0).lines());
    }
}
