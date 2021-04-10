package VM;

import static VM.Constants.COMMAND_LENGTH;

public class Command {
    private int[] content = new int[COMMAND_LENGTH];

    public Command(String content) throws Exception {
        this.createCommand(content);
    }

    private void createCommand(String word) throws Exception {
        if (word.length() != COMMAND_LENGTH) throw new Exception("Bad length for symbolic word");
        for (int i = 0; i < COMMAND_LENGTH; i++) {
            content[i] = word.charAt(i);
        }
    }
}
