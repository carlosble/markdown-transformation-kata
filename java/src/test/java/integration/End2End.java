package integration;

import cmd.Main;
import org.junit.jupiter.api.Test;

public class End2End {
    @Test
    public void end2end(){
        Main.main(new String[]{"-t", "link2footnote", "sourceFile", "destinationFile"});
    }
    // no le pasamos nada
}
