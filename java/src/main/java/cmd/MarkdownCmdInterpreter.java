package cmd;

import mdtransformer.MarkdownTransformer;

import java.io.PrintStream;

public class MarkdownCmdInterpreter {
    private final MarkdownTransformer markdownTransformer;

    public MarkdownCmdInterpreter(MarkdownTransformer markdownTransformer) {
        this.markdownTransformer = markdownTransformer;
    }

    public void execute(String[] cmdArguments, PrintStream out) {
        out.println("Execution:" + String.join(",", cmdArguments));
    }
}
