package mdtransformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MarkdownTransformer {
    private final TextFileHandler textFileHandler;
    private final Transformations transformations;

    public MarkdownTransformer(TextFileHandler textFileHandler, Transformations transformations) {
        this.textFileHandler = textFileHandler;
        this.transformations = transformations;
    }

    public void turnLinksIntoFootnotes(String sourceFile, String destinationFile) throws IOException {
        List<String> lines = textFileHandler.readLines();
        List<Footnote> footnotes = new ArrayList<>();
        for (var line: lines) {
            footnotes.addAll(transformations.linkToFootNote(line));
        }
        int anchorCount = 1;
        for (var footnote: footnotes) {
            textFileHandler.writeLineWithEndingBreak(footnote.textInPage(anchorCount));
            anchorCount++;
        }
        anchorCount = 1;
        for (var footnote: footnotes) {
            textFileHandler.writeLineWithEndingBreak(footnote.anchor(anchorCount));
            anchorCount++;
        }
    }
}
