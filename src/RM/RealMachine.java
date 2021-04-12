package RM;

import VM.VirtualMachine;

import java.util.ArrayList;

public class RealMachine {

//    static void print(Object e){
//        System.out.println(e);
//    }

    private ExternalMemory externalMemory;
    private InternalMemory internalMemory;
    private ArrayList<VirtualMachine> virtualMachines;
    private RealCPU realCPU;

    RealMachine(){
        externalMemory = new ExternalMemory();
        internalMemory = new InternalMemory();
        virtualMachines = new ArrayList<VirtualMachine>(1);
        try {
            realCPU = new RealCPU(internalMemory,externalMemory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        createVirtualMachine("prog.txt");

    }

    //TODO
    private int findFreeInternalMemoryBlocks() {
        return 0;
    }

    //TODO
    private int findFreeExternalMemoryBlocks() {
        return 500;
    }

    private VirtualMachine createVirtualMachine(String fileName) {

        int internalBlockBegin = findFreeInternalMemoryBlocks();
        int externalBlockBegin = findFreeExternalMemoryBlocks();

        try {
            realCPU.createMemoryTable(internalBlockBegin, externalBlockBegin);
            System.out.println("Nuo" + " " + realCPU.getPTRValue(0) + " iki " + realCPU.getPTRValue(255));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new VirtualMachine(fileName, realCPU, internalBlockBegin);
    }

}
