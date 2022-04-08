package infrastructure;

import cmd.MarkdownCmdInterpreter;
import mdtransformer.MarkdownTransformer;
import mdtransformer.TextFileHandler;

public class Factory {
    public static MarkdownTransformer MarkdownTransformer() {
        return new MarkdownTransformer(new TextFileHandler());
    }

    public static MarkdownCmdInterpreter MardownCmdInterpreter() {
        return new MarkdownCmdInterpreter(MarkdownTransformer());
    }
}
