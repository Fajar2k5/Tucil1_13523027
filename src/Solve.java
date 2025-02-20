import java.util.List;

public class Solve {

    List<Block> blocks;
    Board board;
    Board solution;
    int tries;

    public Solve(List<Block> blocks, Board board) {
        this.blocks = blocks;
        this.board = board;
        this.tries = 0;
    }

    public boolean solve() {
        return solve(0);
    }

    public boolean solve(int idx) {
        if (idx == blocks.size() && board.cekFull()) {
            solution = new Board(board.board.length, board.board[0].length);
            for (int i = 0; i < board.board.length; i++) {
                System.arraycopy(board.board[i], 0, solution.board[i], 0, board.board[0].length);
            }
            return true;
        }
        for (int k = 0; k < blocks.get(idx).variasi.size(); k++) {
            for (int i = 0; i < board.board.length; i++) {
                for (int j = 0; j < board.board[0].length; j++) {
                    if (board.cekFitBlock(blocks.get(idx), i, j)) {
                        board.putBlock(blocks.get(idx), i, j);
                        if (solve(idx + 1)) {
                            return true;
                        }
                        tries++;
                        board.delBlockByLetter(blocks.get(idx).symbol);
                    }
                    tries++;
                }
            }
            blocks.get(idx).nextVar();
        }
        return false;
    }

    public void printSolution() {
        solution.printBoard();
        System.out.println("\nTries: " + tries);
    }

    public void clearSolution() {
        solution.clearBoard();
        tries = 0;
    }

    public void clearBoard() {
        board.clearBoard();
        tries = 0;
    }

}
