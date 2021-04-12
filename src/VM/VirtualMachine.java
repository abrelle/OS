package VM;
import Memory.Memory;
import RM.ChannelDevice;
import RM.Parser;
import RM.RealCPU;

import java.util.ArrayList;

import static VM.Constants.BLOCK_LENGTH;
import static VM.Constants.BLOCK_NUMBER;

public class VirtualMachine {
    private VirtualCPU cpu = null;
    private ChannelDevice channelDevice;
    private Memory memory = null;
    private Interpreter interpreter;
    private RealCPU realCPU = null;

    private int currentDSBlock = 0;
    private int currentSSBlock = 0;
    private int currentCSBlock = 0;

    public VirtualMachine(String sourceCode, RealCPU realCPU, int internalBlockBegin) {
        try {
            memory = new Memory(BLOCK_NUMBER, BLOCK_LENGTH);
            this.realCPU = realCPU;
            internalBlockBegin = internalBlockBegin;
            realCPU.loadVirtualMachineMemory(internalBlockBegin, currentCSBlock, currentDSBlock, currentSSBlock);

            cpu = new VirtualCPU(realCPU);
            interpreter = new Interpreter(cpu,memory);
            Parser parser = new Parser(sourceCode);
            channelDevice = new ChannelDevice();

            uploadDataSegment(parser.getDataSegment());
            uploadCodeSegment(parser.getCodeSegment());

            justDoIt();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrentBlocks(int currentDSBlock, int currentSSBlock, int currentCSBlock) {
        this.currentDSBlock = currentDSBlock;
        this.currentSSBlock = currentSSBlock;
        this.currentCSBlock = currentCSBlock;
    }

    private void justDoIt() throws Exception {
        while (!cpu.getSI().equals(Constants.INTERRUPTION.HALT)) {
            String command = cpu.getCSValue(cpu.getIC()).getASCIIFormat();
            interpreter.execute(command);
            cpu.increaseIC();
        }
    }

    private void uploadDataSegment(ArrayList<String> dataSegment) throws Exception {
        int i = 0;
        for (String data : dataSegment) {
            cpu.setDS(new Word(i), new Word(data, Word.WORD_TYPE.NUMERIC));
            i++;
        }
    }

    private void uploadCodeSegment(ArrayList<String> codeSegment) throws Exception {
        int i = 0;
        for (String command : codeSegment) {
            cpu.setCS(new Word(i), new Word(command, Word.WORD_TYPE.SYMBOLIC));
            i++;
        }
    }
}
