package VM;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static VM.Constants.*;


public class Interpreter {
    private Scanner scanner = null;
    private ArrayList<String> dataSegment = new ArrayList<String>(100);
    private ArrayList<String> codeSegment = new ArrayList<String>(100);
    private ArrayList<String> code = new ArrayList<String>(100);

    Interpreter(String fileLocation) {
        try {
            File file = new File(fileLocation);
            scanner = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read() {
        while (scanner.hasNext()) {
            code.add(scanner.next());
        }
    }

    public void interpreter(String programName) throws Exception {
        ArrayList<String> program;

        if (code.contains(programName)
                && commandExists(code.indexOf(programName) - 1, PROGRAM_NAME)
                && commandExists(code.indexOf(programName) - 2, PROGRAM_BEGIN)) {
            program = getProgram(programName);
        } else {
            throw new Exception("No program");
        }

        System.out.println(program.toString());
        int indexOfDataSeg = 0;
        int indexOfCodeSeg = 0;

        if (program.contains(DATA_SEGMENT_NAME)) {
            indexOfDataSeg = program.indexOf(DATA_SEGMENT_NAME);
        }

        if (program.contains(CODE_SEGMENT_NAME)) {
            indexOfCodeSeg = program.indexOf(CODE_SEGMENT_NAME);
        } else {
            throw new Exception("NO CODSEG");
        }

        dataSegment.addAll(program.subList(indexOfDataSeg + 1, indexOfCodeSeg));
        codeSegment.addAll(program.subList(indexOfCodeSeg + 1, program.size()));
    }

    public boolean commandExists(int index, String name) {
        return code.get(index).equals(name);
    }

    public ArrayList<String> getProgram(String name) {
        int nameIndex = code.indexOf(name);
        ArrayList<String> programCode = new ArrayList<>(code.subList(nameIndex + 1, code.size()));
        int indexOfEnd = programCode.indexOf(PROGRAM_END);

        return new ArrayList<>(programCode.subList(0, indexOfEnd));
    }

    public ArrayList<String> getCodeSegment() {
        return codeSegment;
    }

    public ArrayList<String> getDataSegment() {
        return dataSegment;
    }
}
