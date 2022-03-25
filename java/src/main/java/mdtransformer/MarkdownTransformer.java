package mdtransformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarkdownTransformer {
    private final TextFileHandler textFileHandler;
    private final Transformations transformations;

    public MarkdownTransformer(TextFileHandler textFileHandler, Transformations transformations) {
        this.textFileHandler = textFileHandler;
        this.transformations = transformations;
    }

    public void turnLinksIntoFootnotes(String sourceFile, String destinationFile) throws IOException {
        List<Footnote> footnotes = getFootnotesFromTextLines();
        int anchorId = 1;
        writeLinkTextLinesFrom(footnotes, anchorId);
        writeLinkAnchorLinesFrom(footnotes, anchorId);
    }

    private List<Footnote> getFootnotesFromTextLines() {
        List<Footnote> footnotes = new ArrayList<>();
        for (var line: textFileHandler.readLines()) {
            footnotes.addAll(transformations.linkToFootNote(line));
        }
        return footnotes;
    }

    private void writeLinkAnchorLinesFrom(List<Footnote> footnotes, int anchorId) {
        for (var footnote: footnotes) {
            textFileHandler.writeLineWithEndingBreak(footnote.anchor(anchorId));
            anchorId++;
        }
    }

    private void writeLinkTextLinesFrom(List<Footnote> footnotes, int anchorId) {
        for (var footnote: footnotes) {
            textFileHandler.writeLineWithEndingBreak(footnote.textInPage(anchorId));
            anchorId++;
        }
    }
}
