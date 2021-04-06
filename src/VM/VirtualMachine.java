package VM;

public class VirtualMachine {
    private CPU cpu = null;
    private Memory memory = null;

    VirtualMachine() throws Exception {
        memory = new Memory();
        cpu = new CPU(memory);
    }
}
