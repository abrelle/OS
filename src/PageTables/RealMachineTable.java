package PageTables;

import java.util.ArrayList;
import java.util.Random;

import static VM.Constants.RM_PAGE_TABLE_ENTRIES;

public class RealMachineTable {

    int PTR = -1;
    ArrayList<Integer> virtualMachineBlock = new ArrayList<>();
    ArrayList<Integer> realMachineBlock = new ArrayList<>();
    ArrayList<Boolean> isPageUsed = new ArrayList<>();
    ArrayList<Boolean> memoryType = new ArrayList<>();

    public RealMachineTable() {
        for (int i = 0; i < RM_PAGE_TABLE_ENTRIES; i++) {
            virtualMachineBlock.add(-1);
            realMachineBlock.add(i);
            isPageUsed.add(false);
            memoryType.add(false);
        }
    }

    public VirtualMachineTable giveRMBlocks() {
        //todo kelioms virtualioms masinoms
        VirtualMachineTable virtualMachineTable = new VirtualMachineTable();
        for (int i = 0; i < virtualMachineTable.getSize(); i++) {
            Random random = new Random();
            PTR = random.nextInt(RM_PAGE_TABLE_ENTRIES);
            while (virtualMachineBlock.contains(PTR)) {
                PTR = random.nextInt(RM_PAGE_TABLE_ENTRIES);
            }
            virtualMachineTable.setRMBlock(i, PTR);
            virtualMachineBlock.set(i, PTR);
            isPageUsed.set(i, true);
        }
        return virtualMachineTable;
    }

    public void printPageTable() {
        System.out.println("PAGE TABLE:");
        System.out.println("VAB  " + "RAB  " + "PN  " + "SWP  ");
        for (int i = 0; i < RM_PAGE_TABLE_ENTRIES; i++) {
            System.out.println(realMachineBlock.get(i) + "  " + virtualMachineBlock.get(i) + "  "
                    + isPageUsed.get(i) + "  " + memoryType.get(i) + "  ");
        }
    }

    public void setRMBlock(int index, int element) {
        if ((index >= 0 && index < RM_PAGE_TABLE_ENTRIES) && (element >= 0 && element < RM_PAGE_TABLE_ENTRIES)) {
            if (!isPageUsed.get(index)) {
                realMachineBlock.set(index, element);
            } else {
                System.out.println("Page is used ");
            }
        } else {
            System.out.println("Incorrect data ");
        }
    }

    public void setAllBlock(int index, int setVirtual, int setReal, boolean setMemory) {
        if (!isPageUsed.get(index)) {
            if ((index >= 0 && index < RM_PAGE_TABLE_ENTRIES) && (setVirtual >= 0 && setVirtual < RM_PAGE_TABLE_ENTRIES)
                    && (setReal >= 0 && setReal < RM_PAGE_TABLE_ENTRIES)) {
                virtualMachineBlock.set(index, setVirtual);
                realMachineBlock.set(index, setReal);
                memoryType.set(index, setMemory);
            } else {
                System.out.println("Incorrect data ");
            }
        } else {
            System.out.println("Page is used ");
        }
    }

    public void switchIsPageUsed(int index) {
        if (isPageUsed.get(index)) {
            isPageUsed.set(index, false);
        } else if (!isPageUsed.get(index)) {
            isPageUsed.set(index, true);
        }
    }

    public void switchMemoryType(int index) {
        if (memoryType.get(index)) {
            memoryType.set(index, false);
        } else if (!memoryType.get(index)) {
            memoryType.set(index, true);
        }
    }

    public void clearBlock(int index) {
        realMachineBlock.set(index, -1);
        isPageUsed.set(index, false);
        memoryType.set(index, false);

    }

    public void clearAllBlocks() {
        for (int i = 0; i < RM_PAGE_TABLE_ENTRIES; i++) {
            realMachineBlock.set(i, -1);
            isPageUsed.set(i, false);
            memoryType.set(i, false);
        }
    }
}
