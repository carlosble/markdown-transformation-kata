package unit;

import cmd.MarkdownCmdInterpreter;
import mdtransformer.MarkdownTransformer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class MarkdownCmdInterpreterTest {

    @Test
    public void should_call_markdown_transformer_the_right_arguments() throws IOException {
        MarkdownTransformer markdownTransformerMock = mock(MarkdownTransformer.class);
        PrintStream printStreamMock = mock(PrintStream.class);
        MarkdownCmdInterpreter markdownCmdInterpreter = new MarkdownCmdInterpreter(markdownTransformerMock);

        markdownCmdInterpreter.execute(new String[]{"link2note", "source", "output"}, printStreamMock);

        verify(markdownTransformerMock).turnLinksIntoFootnotes("source", "output");
    }

    @Test
    public void should_print_an_error_when_an_exception_occurs() throws IOException {
        MarkdownTransformer markdownTransformerMock = mock(MarkdownTransformer.class);
        PrintStream printStreamMock = mock(PrintStream.class);
        MarkdownCmdInterpreter markdownCmdInterpreter = new MarkdownCmdInterpreter(markdownTransformerMock);
        doThrow(new IOException("fooo"))
                .when(markdownTransformerMock)
                .turnLinksIntoFootnotes("source","output");

        markdownCmdInterpreter.execute(new String[]{"link2note", "source", "output"}, printStreamMock);

        verify(printStreamMock).println("Fatal error: fooo");
    }

    /*
    ["link2note","sourcefile"] -> print: El comando necesita el fichero destino
    ["linkToNote","sourcefile"] -> print: el comando no existe, los comandos disponibles son....
    ["link2note"] -> print: El comando necesita el fichero fuente y destino
    [ ] -> print: No se han especificado argumentos, los comandos son ...
*/
}
