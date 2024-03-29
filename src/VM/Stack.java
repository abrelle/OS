package VM;

import Memory.Memory;

public class Stack {
    private final Memory memory;
    private final VirtualCPU cpu;

    Stack(Memory memory, VirtualCPU cpu) {
        this.memory = memory;
        this.cpu = cpu;
    }

    public void push(Word word) throws Exception {
        memory.setWord(word,cpu.getSP());
        cpu.increaseSP();
    }

    public Word pop() throws Exception {
        cpu.decreaseSP();
        Word word = memory.getWord(cpu.getSP());
        return word;
    }

    public Word getNthElement(int n) throws Exception {
        Word SP = cpu.getSP();
        //Word SS = cpu.getSP();
        return memory.getWord(SP.add(n));
    }

    public Word getPreviousElement(int n) throws Exception {
        Word SP = cpu.getSP();
        return memory.getWord(SP.add(n));
    }
}
