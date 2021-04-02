package VM;

public class CPU {
    //pridet ic, ptr
    // ...
    private final Word SP = new Word(0);

    private final ByteWord MODE = new ByteWord(0);
    private final ByteWord C = new ByteWord(0);

    // pertraukimu reiksmes
    private final ByteWord TI = new ByteWord(0);
    private final ByteWord PI = new ByteWord(0);
    private final ByteWord SI = new ByteWord(0);

    // prideti segmentus
    // ...
    private final Word SS = new Word(Constants.STACK_SEGMENT);

    CPU() throws Exception {
    // add memory
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
}
