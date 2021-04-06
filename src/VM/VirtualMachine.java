package VM;

import java.util.ArrayList;

public class VirtualMachine {
    private CPU cpu = null;
    private Memory memory = null;
    private Commands interpreter = null;

    VirtualMachine()
    {
        try {
            memory = new Memory();
            cpu = new CPU(memory);
            interpreter = new Commands(cpu,memory);
            uploadCode();
            doYourMagic();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doYourMagic()
    {
        int i = 0;
        while (i<10)
        {
            try {
                String command = memory.getWord(cpu.getCS(cpu.getIC())).getASCIIFormat();


                interpreter.execute(command);
                cpu.increaseIC();
                i++;
                if(command.contains("HALT"))
                {
                    return;
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void uploadCode()
    {
        try {
            Interpreter interpreter =  new Interpreter("prog.txt");
            interpreter.read();
            interpreter.interpreter();
            ArrayList<String> dataSegment = interpreter.getDataSegment();
            for (int i = 0; i<dataSegment.size();i++)
            {
                cpu.setDS(new Word(i), new Word(dataSegment.get(i),Word.WORD_TYPE.NUMERIC));
            }
            ArrayList<String>codeSegment = interpreter.getCodeSegment();
            for (int i = 0; i<codeSegment.size();i++)
            {
                cpu.setCS(new Word(i), new Word(codeSegment.get(i), Word.WORD_TYPE.SYMBOLIC));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
