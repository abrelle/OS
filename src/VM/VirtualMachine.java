package VM;

public class VirtualMachine {
    private CPU cpu = null;
    private Memory memory = null;
    private Commands interpretator = null;

    VirtualMachine() throws Exception {
        memory = new Memory();
        cpu = new CPU(memory);
        interpretator = new Commands(cpu,memory);
    }
}
