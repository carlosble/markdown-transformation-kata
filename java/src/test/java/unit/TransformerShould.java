package unit;

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
        SpyTextFileHandler textFileHandler = new SpyTextFileHandler();
        textFileHandler.stubLines = List.of("[some link](url)     [second link](url2)",
                                            "[third link](url3)   [fourth link](url4)");
        Transformations transformations = new Transformations();
        MarkdownTransformer transformer = new MarkdownTransformer(textFileHandler, transformations);

        transformer.turnLinksIntoFootnotes("sourceFilePath", "destinationFilePath");

        String buffer = textFileHandler.buffer.toString();
        //                                           id with at least five chars
        String beginningOfTextInPage = "^some link \\[\\^anchor_.{5,}] second link \\[\\^anchor_.{5,}].*";
        String endOfAnchors = ".*\\[\\^anchor_.{5,}]: url3 \\[\\^anchor_.{5,}]: url4 $";
        assertThat(buffer).matches(beginningOfTextInPage);
        assertThat(buffer).matches(endOfAnchors);
    }

    class SpyTextFileHandler extends TextFileHandler {
        public StringBuilder buffer = new StringBuilder();
        public List<String> stubLines;

        @Override
        public List<String> readLines() {
            return stubLines;
        }

        @Override
        public void writeLineWithEndingBreak(String line) {
            buffer.append(line);
            buffer.append(" ");
        }
    }
}
