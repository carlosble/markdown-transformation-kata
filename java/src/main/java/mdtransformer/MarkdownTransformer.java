package mdtransformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownTransformer {
    private final TextFileHandler textFileHandler;

    public MarkdownTransformer(TextFileHandler textFileHandler) {
        this.textFileHandler = textFileHandler;
    }

    public void turnLinksIntoFootnotes(String sourceFile, String destinationFile) throws IOException {
        List<Footnote> footnotes = getFootnotesFromTextLines(sourceFile);
        writeLinkTextLinesFrom(footnotes, destinationFile);
        writeLinkAnchorLinesFrom(footnotes, destinationFile);
    }

    private List<Footnote> getFootnotesFromTextLines(String sourceFile) throws IOException {
        List<Footnote> footnotes = new ArrayList<>();
        for (var line: textFileHandler.readLines(sourceFile)) {
            footnotes.addAll(transformOfLinksIn(line));
        }
        return footnotes;
    }

    private void writeLinkAnchorLinesFrom(List<Footnote> footnotes, String destinationFile) throws IOException {
        for (var footnote: footnotes) {
            textFileHandler.appendLineToTextFile(footnote.anchor(), destinationFile);
        }
    }

    private void writeLinkTextLinesFrom(List<Footnote> footnotes, String destinationFile) throws IOException {
        for (var footnote: footnotes) {
            textFileHandler.appendLineToTextFile(footnote.textInPage(), destinationFile);
        }
    }

    private static List<Footnote> transformOfLinksIn(String line) {
        Pattern pattern = Pattern.compile("\\[(.+?)]\\((.+?)\\)");
        Matcher matcher = pattern.matcher(line);
        List<Footnote> footnotes = new ArrayList<>();
        while (matcher.find()) {
            String linkText = matcher.group(1);
            String linkUrl = matcher.group(2);
            footnotes.add(new Footnote(linkText, linkUrl));
        }
        return footnotes;
    }
}
