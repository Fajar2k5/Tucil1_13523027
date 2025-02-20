public class Board {
    // Board untuk meletakkan block

    char[][] board;

    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String BLACK = "\033[0;30m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String WHITE_BOLD = "\033[1;37m";
    public static final String RED_UNDERLINED = "\033[4;31m";
    public static final String GREEN_UNDERLINED = "\033[4;32m";
    public static final String YELLOW_UNDERLINED = "\033[4;33m";
    public static final String BLUE_UNDERLINED = "\033[4;34m";
    public static final String PURPLE_UNDERLINED = "\033[4;35m";
    public static final String CYAN_UNDERLINED = "\033[4;36m";
    public static final String WHITE_UNDERLINED = "\033[4;37m";
    public static final String RED_BACKGROUND = "\033[41m";
    public static final String GREEN_BACKGROUND = "\033[42m";
    public static final String YELLOW_BACKGROUND = "\033[43m";
    public static final String BLUE_BACKGROUND = "\033[44m";
    public static final String PURPLE_BACKGROUND = "\033[45m";
    public static final String CYAN_BACKGROUND = "\033[46m";
    public static final String WHITE_BACKGROUND = "\033[47m";

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
                printColor(bChar);
            }
            System.out.println();
        }
    }

    public static void printColor(char bChar) {
        switch (bChar) {
            case 'A':
                System.out.print(RED + bChar + RESET);
                break;
            case 'B':
                System.out.print(GREEN + bChar + RESET);
                break;
            case 'C':
                System.out.print(YELLOW + bChar + RESET);
                break;
            case 'D':
                System.out.print(BLUE + bChar + RESET);
                break;
            case 'E':
                System.out.print(PURPLE + bChar + RESET);
                break;
            case 'F':
                System.out.print(CYAN + bChar + RESET);
                break;
            case 'G':
                System.out.print(PURPLE_BACKGROUND + BLACK + bChar + RESET);
                break;
            case 'H':
                System.out.print(RED_BOLD + bChar + RESET);
                break;
            case 'I':
                System.out.print(GREEN_BOLD + bChar + RESET);
                break;
            case 'J':
                System.out.print(YELLOW_BOLD + bChar + RESET);
                break;
            case 'K':
                System.out.print(BLUE_BOLD + bChar + RESET);
                break;
            case 'L':
                System.out.print(PURPLE_BOLD + bChar + RESET);
                break;
            case 'M':
                System.out.print(CYAN_BOLD + bChar + RESET);
                break;
            case 'N':
                System.out.print(WHITE_BOLD + bChar + RESET);
                break;
            case 'O':
                System.out.print(RED_UNDERLINED + bChar + RESET);
                break;
            case 'P':
                System.out.print(GREEN_UNDERLINED + bChar + RESET);
                break;
            case 'Q':
                System.out.print(YELLOW_UNDERLINED + bChar + RESET);
                break;
            case 'R':
                System.out.print(BLUE_UNDERLINED + bChar + RESET);
                break;
            case 'S':
                System.out.print(PURPLE_UNDERLINED + bChar + RESET);
                break;
            case 'T':
                System.out.print(CYAN_UNDERLINED + bChar + RESET);
                break;
            case 'U':
                System.out.print(WHITE_UNDERLINED + bChar + RESET);
                break;
            case 'V':
                System.out.print(RED_BACKGROUND + bChar + RESET);
                break;
            case 'W':
                System.out.print(GREEN_BACKGROUND + bChar + RESET);
                break;
            case 'X':
                System.out.print(YELLOW_UNDERLINED + bChar + RESET);
                break;
            case 'Y':
                System.out.print(BLUE_UNDERLINED + bChar + RESET);
                break;
            case 'Z':
                System.out.print(PURPLE_UNDERLINED + bChar + RESET);
                break;
            default:
                System.out.print(bChar);
                break;
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
