public class Board {
    // Board untuk meletakkan block

    char[][] board;

    public Board(int row, int col) {
        board = new char[row][col];
        // create 2D array and fill with '.'
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = '.';
            }
        }
    }

    public void printBoard() {
        for (char[] chars : board) {
            for (char bChar : chars) {
                System.out.print(bChar);
            }
            System.out.println();
        }
    }

    public boolean cekFitBlock(Block block, int row, int col) {
        if (row + block.currentVar().length > board.length || col + block.currentVar()[0].length > board[0].length) {
            return false;
            // Kalau blocknya keluar dari board, return false
        }
        for (int i = 0; i < block.currentVar().length; i++) {
            for (int j = 0; j < block.currentVar()[0].length; j++) {
                if (block.currentVar()[i][j] != ' ' && board[row + i][col + j] != '.') {
                    return false;
                    // kalau coba diisi bagian block dan tidak kosong (isinya bukan '.') return false
                }
            }
        }
        return true;
    }

    public void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '.';
            }
        }
    }

    public void putBlock(Block block, int row, int col) {
        // fungsi putBlock untuk meletakkan block di board jika cekFitBlock bernilai true
        if (cekFitBlock(block, row, col)) {
            for (int i = 0; i < block.currentVar().length; i++) {
                for (int j = 0; j < block.currentVar()[0].length; j++) {
                    if (block.currentVar()[i][j] != ' ') {
                        board[row + i][col + j] = block.currentVar()[i][j];
                    }
                }
            }
        }
    }

    public void delBlockByLetter(char letter) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == letter) {
                    board[i][j] = '.';
                }
            }
        }
    }

    public boolean cekFull() {
        for (char[] chars : board) {
            for (int i = 0; i < board[0].length; i++) {
                if (chars[i] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

}
