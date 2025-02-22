import java.util.List;
import java.util.Stack;

public class Solve {

    List<Block> blocks;
    Board board;
    Board solution;
    long tries;
    long time;
    boolean solved = false;
    int bWidth;
    int bHeight;

    public Solve(List<Block> blocks, Board board) {
        this.blocks = blocks;
        this.board = board;
        this.tries = 0;
        this.time = 0;
        this.bWidth = board.board[0].length;
        this.bHeight = board.board.length;
    }

//    public boolean solve() {
//        long startTime = System.currentTimeMillis();
//        boolean result = solve(0);
//        long endTime = System.currentTimeMillis();
//        time = endTime - startTime;
//        return result;
//    }

    public boolean solve() {
        long startTime = System.currentTimeMillis();
        boolean result = solveNew();
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
        return result;
    }

    private boolean solveNew() {
        Stack<Step> stack = new Stack<>();
        stack.push(new Step(0, this.board));

        while (!stack.isEmpty()) {
            Step currentStep = stack.pop();
            int idx = currentStep.idx;
            Board currentBoard = currentStep.board;

            if (idx == blocks.size()) {
                if (currentBoard.cekFull()) {
                    solution = new Board(currentBoard);
                    solved = true;
                    return true;
                }
                continue;
            }

            Block piece = blocks.get(idx);
            for (int k = 0; k < 8; k++) {
                int pWidth = piece.currentVar()[0].length;
                int jarak = 0;
                for (int m = 0; m < pWidth; m++) {
                    if (piece.currentVar()[0][m] != ' ') {
                        jarak = m;
                        break;
                    }
                }
                int height = bHeight - piece.currentVar().length;
                int width = bWidth - pWidth;
                for (int i = 0; i <= height; i++) {
                    for (int j = 0; j <= width; j++) {
                        if (currentBoard.board[i][j + jarak] == '.' && currentBoard.cekFit(piece, i, j)) {
                            Board newBoard = new Board(currentBoard);
                            newBoard.putBlock(piece, i, j);
                            stack.push(new Step(idx + 1, newBoard));
                            tries++;
                        }
                        tries++;
                    }
                }
                piece.nextVar();
            }
        }
        return false;
    }

    private static class Step {
        int idx;
        Board board;

        Step(int idx, Board board) {
            this.idx = idx;
            this.board = board;
        }
    }

    // somehow rekursif kek gini ga dibolehin asisten padahal juga brute forcfe itungannya
    // karena nyoba semua kemungkinan, sampe milyaran juga dicoba
//    public boolean solve(int idx) {
//
//        if (idx == blocks.size()) {
//            if (board.cekFull()) {
//                solution = new Board(board.board.length, board.board[0].length);
//                for (int i = 0; i < board.board.length; i++) {
//                    System.arraycopy(board.board[i], 0, solution.board[i], 0, board.board[0].length);
//                }
//                solved = true;
//                return true;
//            }
//            else {
//                return false;
//            }
//        }
//        for (int k = 0; k < blocks.get(idx).variasi.size(); k++) {
//            Block piece = blocks.get(idx);
//            int pWidth = piece.currentVar()[0].length;
//            int jarak = 0;
//            for (int m = 0; m < pWidth; m++) {
//                if (piece.currentVar()[0][m] != ' ') {
//                    jarak = m;
//                    break;
//                }
//            }
//            int height = bHeight - piece.currentVar().length;
//            int width = bWidth - pWidth;
//            for (int i = 0; i <= height; i++) {
//                for (int j = 0; j <= width; j++) {
//                    if (board.board[i][j + jarak] == '.' && board.cekFitBlock(piece, i, j)) {
//                        board.putBlock(piece, i, j);
//                        if (solve(idx + 1)) {
//                            return true;
//                        }
//                        tries++;
//                        board.delBlockByLetter(piece.symbol);
//                    }
//                    tries++;
//                }
//            }
//            piece.nextVar();
//        }
//        return false;
//    }

    public void printSolution() {
        if (solved) {
            solution.printBoard();
        }
        System.out.println("\nWaktu pencarian: " + time + " ms");
        System.out.println("\nBanyak kasus yang ditinjau: " + tries);
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
