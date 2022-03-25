package unit;

import mdtransformer.Footnote;
import mdtransformer.MarkdownTransformer;
import mdtransformer.TextFileHandler;
import mdtransformer.Transformations;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

/*
    Leer fichero, aplicar transformaciones, escribir
    Un fichero con un solo enlace
    Un fichero con varios enlaces en una misma linea
    Un fichero con varias lineas y varios enlaces
    El ancla de la nota al pie la coloco lo ultimo del fichero
 */

public class TransformerShould {
    @Test
    public void read_lines_from_file_and_store_transformations_in_file() throws IOException {
        TextFileHandler textFileHandler = mock(TextFileHandler.class);
        when(textFileHandler.readLines()).thenReturn(
                List.of("[some link](url)     [second link](url2)",
                        "[third link](url3)   [fourth link](url4)"));
        Transformations transformations = new Transformations();
        MarkdownTransformer transformer = new MarkdownTransformer(textFileHandler, transformations);

        transformer.turnLinksIntoFootnotes("sourceFilePath", "destinationFilePath");

        InOrder inOrder = inOrder(textFileHandler);
        inOrder.verify(textFileHandler).writeLineWithEndingBreak("some link [^anchor1]");
        inOrder.verify(textFileHandler).writeLineWithEndingBreak("second link [^anchor2]");
        inOrder.verify(textFileHandler).writeLineWithEndingBreak("third link [^anchor3]");
        inOrder.verify(textFileHandler).writeLineWithEndingBreak("fourth link [^anchor4]");
        inOrder.verify(textFileHandler).writeLineWithEndingBreak("[^anchor1]: url");
        inOrder.verify(textFileHandler).writeLineWithEndingBreak("[^anchor2]: url2");
        inOrder.verify(textFileHandler).writeLineWithEndingBreak("[^anchor3]: url3");
        inOrder.verify(textFileHandler).writeLineWithEndingBreak("[^anchor4]: url4");
    }
}
