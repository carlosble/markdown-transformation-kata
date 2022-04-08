package cmd;

import infrastructure.Factory;

public class Main {
    public static void main(String[] args) {
        Factory.MardownCmdInterpreter().execute(args, System.out);
    }
}
