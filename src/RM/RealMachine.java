package RM;

import VM.VirtualMachine;

import java.util.ArrayList;

public class RealMachine {
        static void print(Object e){
        System.out.println(e);
    }

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
        //TODO:new virtualMachine(fileName, realCPU, internalBlockBegin);
        return new VirtualMachine();
    }

    public ExternalMemory getExternalMemory() {
        return externalMemory;
    }

    public void setExternalMemory(ExternalMemory externalMemory) {
        this.externalMemory = externalMemory;
    }

    public InternalMemory getInternalMemory() {
        return internalMemory;
    }

    public void setInternalMemory(InternalMemory internalMemory) {
        this.internalMemory = internalMemory;
    }

    public ArrayList<VirtualMachine> getVirtualMachines() {
        return virtualMachines;
    }

    public void setVirtualMachines(ArrayList<VirtualMachine> virtualMachines) {
        this.virtualMachines = virtualMachines;
    }

    public RealCPU getRealCPU() {
        return realCPU;
    }

    public void setRealCPU(RealCPU realCPU) {
        this.realCPU = realCPU;
    }
}
