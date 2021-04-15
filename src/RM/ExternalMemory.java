package RM;

import Memory.Memory;

import static VM.Constants.BLOCK_LENGTH;
import static VM.Constants.EXTERNAL_MEMORY_BLOCK_NUMBER;

public class ExternalMemory extends Memory {

    private int WORD_NUMBER = EXTERNAL_MEMORY_BLOCK_NUMBER * BLOCK_LENGTH;

    ExternalMemory() {
        super(EXTERNAL_MEMORY_BLOCK_NUMBER, BLOCK_LENGTH);
    }
}
