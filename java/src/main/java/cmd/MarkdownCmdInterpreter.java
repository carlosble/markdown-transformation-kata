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
        if(cmdArguments.length == 2) {
            out.println("Error: link2note requires destination file argument");
            return;
        }
        try {
            markdownTransformer.turnLinksIntoFootnotes(cmdArguments[1], cmdArguments[2]);
        } catch (IOException e) {
            out.println("Fatal error: " + e.getMessage());
        }
    }
}
