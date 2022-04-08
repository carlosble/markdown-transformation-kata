package cmd;

import mdtransformer.MarkdownTransformer;

import java.io.IOException;
import java.io.PrintStream;

public class MarkdownCmdInterpreter {
    private final MarkdownTransformer markdownTransformer;

    public MarkdownCmdInterpreter(MarkdownTransformer markdownTransformer) {
        this.markdownTransformer = markdownTransformer;
    }

    public void execute(String[] cmdArguments, PrintStream out) {

        try {
            markdownTransformer.turnLinksIntoFootnotes(cmdArguments[2], cmdArguments[3]);
        } catch (IOException e) {
            out.println("Fatal error: " + e.getMessage());
        }

        out.println("Execution:" + String.join(",", cmdArguments));
    }
}
