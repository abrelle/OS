package VM;

public class Commands {
    private final CPU cpu;
    private final Memory memory;
    private final Stack stack;

    Commands(CPU cpu, Memory memory)
    {
        this.cpu = cpu;
        this.memory = memory;
        stack = new Stack(memory, cpu);
    }

    public void execute(String command) throws Exception {
        if(command.contains("GT")) {
            GET(new Word(command.substring(2), Word.WORD_TYPE.NUMERIC));
        }else if(command.contains("HALT")){
            HALT();
        }
        // ...
    }

    // komandos
    private void GET(Word virtualAddress) {
        System.out.println("GET()");
    }
    private void HALT() throws Exception {
        System.out.println("HALT()");
        cpu.setSI(Constants.INTERRUPTION.HALT);
    }
    // ...

}
