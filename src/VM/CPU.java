package VM;

public class CPU {
    private final Word IC = new Word(0); // Rodo virtualios mašinos einamąją instrukciją
    private final Word PTR = new Word(0); // Puslapių lentelės bazinis registras
    private final Word SP = new Word(0); // Steko rodyklė

    private final ByteWord MODE = new ByteWord(0); // Procesoriaus darbo režimas
    private final ByteWord C = new ByteWord(0);

    // Pertraukimų registrai
    private final ByteWord TI = new ByteWord(0);
    private final ByteWord PI = new ByteWord(0);
    private final ByteWord SI = new ByteWord(0);
    // Segmentai
    private final Word DS = new Word(Constants.DATA_SEGMENT);
    private final Word CS = new Word(Constants.CODE_SEGMENT);
    private final Word SS = new Word(Constants.STACK_SEGMENT);

    private final Memory memory;

    CPU(Memory memory) throws Exception {
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

    public Word getIC() { return IC; }
    public void setIC(Word word) { IC.setWord(word); }
    public void increaseIC() throws Exception { IC.setWord(IC.add(1)); }

    public void setDS(Word virtualAddress, Word value) throws Exception {
        memory.setWord(value,DS.getNumber() + virtualAddress.getNumber());
    }
    public Word getDS(Word virtualAddress) throws Exception {
        return new Word(DS.getNumber() + virtualAddress.getNumber());
    }

    public void setCS(Word virtualAddress, Word value) throws Exception {
        memory.setWord(value,CS.getNumber() + virtualAddress.getNumber());
    }
    public Word getCS(Word virtualAddress) throws Exception {
        return new Word(CS.getNumber() + virtualAddress.getNumber());
    }

    public Constants.INTERRUPTION getSI() { return (Constants.INTERRUPTION) SI.getValue(); }
    public void setSI(Constants.INTERRUPTION flag) { SI.setValue(flag); }

    public Constants.C_VALUES getC() { return (Constants.C_VALUES) C.getValue(); }
    public void setC(Constants.C_VALUES flag) { C.setValue(flag); }
}
