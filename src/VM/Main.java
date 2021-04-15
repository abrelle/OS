package VM;

import GUI.Frame;

public class Main {

    public static void main(String[] args) throws Exception {
        Interpreter interpreter = new Interpreter("hard.txt");
        interpreter.read();
        Frame.start(interpreter.getAllProgramNames());
    }
}
