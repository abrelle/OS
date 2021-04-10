package RM;

import VM.ByteWord;
import VM.Constants;
import VM.Memory;
import VM.Word;

public class RM_CPU {
    private final Word IC = new Word(0); // Rodo virtualios mašinos einamąją instrukciją
    private final Word PTR = new Word(0); // Puslapių lentelės bazinis registras
    private final Word SP = new Word(0); // Steko rodyklė


    private final Word[] RX = new Word[2];
    private final Word RH = new Word(0);
    private final Word RL = new Word(0);

    private final ByteWord MODE = new ByteWord(0); // Procesoriaus darbo režimas

    // Pertraukimų registrai
    private final ByteWord TI = new ByteWord(0);
    private final ByteWord PI = new ByteWord(0);
    private final ByteWord SI = new ByteWord(0);
    // Segmentai
    private final Word DS = new Word(Constants.DATA_SEGMENT);
    private final Word CS = new Word(Constants.CODE_SEGMENT);
    private final Word SS = new Word(Constants.STACK_SEGMENT);

    private byte SR = 0;

    private final Memory memory;

    public RM_CPU(Memory memory) throws Exception {
        this.memory = memory;
        RX[0] = RH;
        RX[1] = RL;
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

    public void setIC(Word word) {
        IC.setWord(word);
    }

    public void increaseIC() throws Exception {
        IC.setWord(IC.add(1));
    }

    public Word getRL() {
        return RL;
    }

    public void setRL(Word word) {
        RL.setWord(word);
    }

    public Word getRH() {
        return RH;
    }

    public void setRH(Word word) {
        RH.setWord(word);
    }

    public void setDS(Word virtualAddress, Word value) throws Exception {
        memory.setWord(value, DS.getNumber() + virtualAddress.getNumber());
    }

    public Word getDS(Word virtualAddress) throws Exception {
        return new Word(DS.getNumber() + virtualAddress.getNumber());
    }

    public void setCS(Word virtualAddress, Word value) throws Exception {
        memory.setWord(value, CS.getNumber() + virtualAddress.getNumber());
    }

    public Word getCS(Word virtualAddress) throws Exception {
        return new Word(CS.getNumber() + virtualAddress.getNumber());
    }

    public Constants.INTERRUPTION getSI() {
        return (Constants.INTERRUPTION) SI.getValue();
    }

    public void setSI(Constants.INTERRUPTION flag) {
        SI.setValue(flag);
    }

    public byte getSR() {
        return SR;
    }

    public void setSR(byte value){
        this.SR = value;
    }

}
