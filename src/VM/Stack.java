package VM;

public class Stack {
    private final Memory memory;
    private final CPU cpu;

    Stack(Memory memory, CPU cpu) {
        this.memory = memory;
        this.cpu = cpu;
    }

    public void Push() throws Exception {
        // set word memory
        cpu.increaseSP();
    }

    public void Pop() throws Exception {
        cpu.decreaseSP();
        // set
    }

    // func to get nth element !!!!!
    // ...
}
