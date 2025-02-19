public class Block {

    char[][] block;

    public void Str2Block(String blockStr) {
        String[] lines = blockStr.split("\n");
        // split the strings by newline to list of string
        block = new char[lines.length][lines[0].length()];
        // create 2D array of char with the same size as String[] lines
        for (int i = 0; i < lines.length; i++) {
            block[i] = lines[i].toCharArray();
            // convert each string of lines to array of char and put it to block[i]
        }
    }

    public void printBlock() {
        for (char[] chars : block) {
            for (char bChar : chars) {
                System.out.print(bChar);
            }
            System.out.println();
        }
    }

    public void rotate() {
        char[][] newBlock = new char[block[0].length][block.length];
        // panjangnya sama lebarnya ditukar
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                newBlock[j][block.length - 1 - i] = block[i][j];
                // rotate 90 degree clockwise
            }
        }
        block = newBlock;
    }

    public void mirror() {
        char[][] newBlock = new char[block.length][block[0].length];
        // panjang dan lebar tetap
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                newBlock[i][block[0].length - 1 - j] = block[i][j];
                // flip horizontally
            }
        }
        block = newBlock;
    }
}
