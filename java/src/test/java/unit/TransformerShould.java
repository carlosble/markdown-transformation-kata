package unit;

import mdtransformer.Footnote;
import mdtransformer.MarkdownTransformer;
import mdtransformer.TextFileHandler;
import mdtransformer.Transformations;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.ArrayList;
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
        when(textFileHandler.readLines()).thenReturn(List.of("[some link](url)"));
        Transformations transformations = new Transformations();
        MarkdownTransformer transformer = new MarkdownTransformer(textFileHandler, transformations);

        transformer.turnLinksIntoFootnotes("sourceFilePath", "destinationFilePath");

        verify(textFileHandler).writeLineWithEndingBreak("some link [^anchor1]");
        verify(textFileHandler).writeLineWithEndingBreak("[^anchor1]: url");
    }

    @Test
    public void transform_text_line_into_footnote(){
        Transformations transformations = new Transformations();
        List<Footnote> footNote = transformations.linkToFootNote("[some link](url)");

        assertThat(footNote.get(0)).isEqualTo(new Footnote("some link [^anchor1]", "[^anchor1]: url"));
    }

    @Test
    public void transform_text_line_with_several_links_into_footnotes(){
        Transformations transformations = new Transformations();
        List<Footnote> footnotes = transformations.linkToFootNote("[some link](url)  \"[other link](url2)\"");

        assertThat(footnotes.get(0)).isEqualTo(
                new Footnote("some link [^anchor1]", "[^anchor1]: url"));
        assertThat(footnotes.get(1)).isEqualTo(
                new Footnote("other link [^anchor2]", "[^anchor2]: url2"));
    }
}
