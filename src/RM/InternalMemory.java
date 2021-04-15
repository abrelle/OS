package RM;

import Memory.Memory;

import static VM.Constants.BLOCK_LENGTH;
import static VM.Constants.INTERNAL_MEMORY_BLOCK_NUMBER;

public class InternalMemory extends Memory {

    private int WORD_NUMBER = INTERNAL_MEMORY_BLOCK_NUMBER * BLOCK_LENGTH;// FFF

    InternalMemory() {
        super(INTERNAL_MEMORY_BLOCK_NUMBER, BLOCK_LENGTH);
    }

    public int findEmptyBlock() throws Exception {
        for (int i = 0; i < INTERNAL_MEMORY_BLOCK_NUMBER; i++) {
            if (checkIfBlockEmpty(i)) return i;
        }
        return -1;
    }
}
