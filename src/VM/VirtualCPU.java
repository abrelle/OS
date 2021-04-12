package VM;

import static VM.Constants.FLAGS;

import RM.RealCPU;

public class VirtualCPU {
    private final Word DS = new Word(Constants.DATA_SEGMENT);
    private final Word CS = new Word(Constants.CODE_SEGMENT);
    private final Word SS = new Word(Constants.STACK_SEGMENT);

    private final ByteWord TI = new ByteWord(Constants.INTERRUPTION.NONE);
    private final ByteWord PI = new ByteWord(Constants.INTERRUPTION.NONE);
    private final ByteWord SI = new ByteWord(Constants.INTERRUPTION.NONE);

    private final Word IC = new Word(0);

    private final Word SP = new Word(0);

    private byte SR = 0;

    private final RealCPU realCPU;

    VirtualCPU(RealCPU realCPU) throws Exception {
        this.realCPU = realCPU;
    }

    public Word getSP() throws Exception {
        return SP;
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

    public Constants.INTERRUPTION getSI() { return (Constants.INTERRUPTION) SI.getValue(); }
    public void setSI(Constants.INTERRUPTION flag) { SI.setValue(flag); }


    public void setDS(Word virtualAddress, Word value) throws Exception {
        //System.out.println("setDS ---->" + virtualAddress);
        //System.out.println("DS value ---->" + value);
        realCPU.setDS(getDS(virtualAddress),value);
        //memory.setWord(value,DS.getNumber() + virtualAddress.getNumber());
    }
    public Word getDS(Word virtualAddress) throws Exception {
        return new Word(DS.getNumber() + virtualAddress.getNumber());
    }
    public Word getDSValue(Word virtualAddress) throws Exception {
        //System.out.println("getDS ---->" + virtualAddress);
        //System.out.println("DS value ---->" + realCPU.getDSValue(getDS(virtualAddress)));
        //return memory.getWord(getDS(virtualAddress));
        return realCPU.getDSValue(getDS(virtualAddress));
    }

    public void setCS(Word virtualAddress, Word value) throws Exception {
        realCPU.setCS(getCS(virtualAddress),value);
        //memory.setWord(value,DS.getNumber() + virtualAddress.getNumber());
    }
    public Word getCS(Word virtualAddress) throws Exception {
        return new Word(CS.getNumber() + virtualAddress.getNumber());
    }
    public Word getCSValue(Word virtualAddress) throws Exception {
        //return memory.getWord(getDS(virtualAddress));
        return realCPU.getCSValue(getCS(virtualAddress));
    }

    public void setSSValue(Word value) throws Exception {
        realCPU.setSS(getSS(getSP()),value);
        //memory.setWord(value,DS.getNumber() + virtualAddress.getNumber());
    }
    public Word getSS(Word virtualAddress) throws Exception {
        return new Word(SS.getNumber() + virtualAddress.getNumber());
    }
    public Word getSSValue(Word virtualAddress) throws Exception {
        //return memory.getWord(getDS(virtualAddress));
        return realCPU.getSSValue(getSS(virtualAddress));
    }
    public Word getSSValue() throws Exception {
        //return memory.getWord(getDS(virtualAddress));
        return realCPU.getSSValue(getSS(getSP()));
    }

    public Word getSSValue(int n) throws Exception {
        return realCPU.getSSValue(getSS(getSP().add(-1*(n+1))));
        //return memory.getWord();
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