package VM;

public class Memory {
    private final Word[][] vmMemory = new Word[Constants.BLOCK_NUMBER][Constants.BLOCK_LENGTH];

    Memory() {
        for (int i = 0;i<Constants.BLOCK_NUMBER; i++)
        {
            for (int j = 0;j<Constants.BLOCK_LENGTH; j++)
            {
                try {
                    vmMemory[i][j] = new Word(i*256 + j);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Word getWord(int virtualAddress) throws Exception
    {
        if(virtualAddress>Constants.WORD_NUMBER || virtualAddress<0)
        {
            throw new Exception("Not existing address");
        }

        int block = virtualAddress/Constants.BLOCK_NUMBER;
        int word = virtualAddress % Constants.BLOCK_NUMBER;
        return vmMemory[block][word];
    }



}
