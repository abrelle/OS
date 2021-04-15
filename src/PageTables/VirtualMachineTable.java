package PageTables;

import java.util.ArrayList;

import static VM.Constants.FF_VALUE;
import static VM.Constants.VM_PAGE_TABLE_ENTRIES;

public class VirtualMachineTable {

    ArrayList<Integer> virtualMachineBlock = new ArrayList<>();
    ArrayList<Integer> realMachineBlock = new ArrayList<>();

    public VirtualMachineTable() {
        for (int i = 0; i < VM_PAGE_TABLE_ENTRIES; i++) {
            virtualMachineBlock.add(i);
            realMachineBlock.add(-1);
        }
    }

    public void setRMBlock(int index, int value) {
        if (value >= 0 && value <= FF_VALUE) {
            this.realMachineBlock.set(index, value);
        }
    }

    public int getSize() {
        return VM_PAGE_TABLE_ENTRIES;
    }

    public void clearRMBlock(int index) {
        this.realMachineBlock.set(index, -1);
    }

    public ArrayList<Integer> getVirtualMachineBlock() {
        return virtualMachineBlock;
    }

    public ArrayList<Integer> getRealMachineBlock() {
        return realMachineBlock;
    }
}
