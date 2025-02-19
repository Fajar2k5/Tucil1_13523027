import java.util.ArrayList;
import java.util.List;

public class Block {

    char[][] block;
    char symbol;

    List<char[][]> variasi;
    int varIndex;

    public Block(String blockStr) {
        String[] lines = blockStr.split("\n");
        // split the strings by newline to list of string
        block = new char[lines.length][lines[0].length()];
        // create 2D array of char with the same size as String[] lines
        for (String line : lines) {
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) != ' ') {
                    symbol = line.charAt(j);
                    // set symbol of block
                    break;
                }
            }
        }
        for (int i = 0; i < lines.length; i++) {
            block[i] = lines[i].toCharArray();
            // convert each string of lines to array of char and put it to block[i]
        }

        variasi = new ArrayList<>();
        makeVariasi();
        varIndex = 0;
    }

    public void makeVariasi() {
        variasi.add(block);
        char[][] newBlock = block;
        for (int i = 0; i < 3; i++) {
            newBlock = rotate(newBlock);
            variasi.add(newBlock);
        }
        newBlock = mirror(block);
        variasi.add(newBlock);
        for (int i = 0; i < 3; i++) {
            newBlock = rotate(newBlock);
            variasi.add(newBlock);
        }
    }

    public char[][] currentVar() {
        return variasi.get(varIndex);
    }

    public void nextVar() {
        varIndex = (varIndex + 1) % variasi.size();
    }

    public void printBlock() {
        for (char[] chars : this.currentVar()) {
            for (char bChar : chars) {
                System.out.print(bChar);
            }
            System.out.println();
        }
    }

    public char[][] rotate(char[][] block) {
        char[][] newBlock = new char[block[0].length][block.length];
        // panjangnya sama lebarnya ditukar
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                newBlock[j][block.length - 1 - i] = block[i][j];
                // rotate 90 degree clockwise
            }
        }
        return newBlock;
    }

    public char[][] mirror(char[][] block) {
        char[][] newBlock = new char[block.length][block[0].length];
        // panjang dan lebar tetap
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                newBlock[i][block[0].length - 1 - j] = block[i][j];
                // flip horizontally
            }
        }
        return newBlock;
    }
}
