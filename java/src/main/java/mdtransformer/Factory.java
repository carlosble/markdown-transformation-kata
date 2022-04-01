package mdtransformer;

public class Factory {
    public static MarkdownTransformer MarkdownTransformer() {
        return new MarkdownTransformer(new TextFileHandler());
    }
}
