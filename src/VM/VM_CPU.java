package VM;

import static VM.Constants.FLAGS;

public class VM_CPU {
    private final Word DS = new Word(Constants.DATA_SEGMENT);
    private final Word CS = new Word(Constants.CODE_SEGMENT);
    private final Word SS = new Word(Constants.STACK_SEGMENT);

    private final Word IC = new Word(0);

    private final Word SP = new Word(0);
    private final Memory memory;
    private byte SR = 0;

    public VM_CPU(Memory memory) throws Exception {
        this.memory = memory;
    }

    public Word getSP() throws Exception {
        return new Word(SS.getNumber() + SP.getNumber());
    }

    public void setSP(Word word) {
        SP.setWord(word);
    }

    public void increaseSP() throws Exception {
        SP.setWord(SP.add(1));
    }

    public void decreaseSP() throws Exception {
        SP.setWord(SP.add(-1));
    }

    public Word getIC() {
        return IC;
    }

    public void setIC(Word newIC) {
        IC.setWord(newIC);
    }

    public void increaseIC() throws Exception {
        IC.setWord(IC.add(1));
    }

    public void setDS(Word virtualAddress, Word value) throws Exception {
        memory.setWord(value, DS.getNumber() + virtualAddress.getNumber());
    }

    public Word getDS(Word virtualAddress) throws Exception {
        return new Word(DS.getNumber() + virtualAddress.getNumber());
    }

    public void setCS(Word virtualAddress, String value) throws Exception {
        memory.setCommands(CS.getNumber() + virtualAddress.getNumber(), value);
    }

    public Word getCS(Word virtualAddress) throws Exception {
        return new Word(CS.getNumber() + virtualAddress.getNumber());
    }

    public byte getSR() {
        return SR;
    }

    public void setSR(FLAGS flag, int value) {
        if (value == 1) {
            SR |= (1 << flag.getValue());
        } else if (value == 0) {
            SR &= ~(1 << flag.getValue());
        }
    }
}
