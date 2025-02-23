import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Game {

    int boardW;
    int boardH;
    int blockCount;
    String mode;
    List<Block> blocks = new ArrayList<>();
    Board board;
    Solve solve;
    Scanner scan;

    public Game(Scanner scan) {
        boardW = 0;
        boardH = 0;
        blockCount = 0;
        mode = "";
        this.scan = scan;
    }

    public void loadFromTxt(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextInt()) {
                boardH = scanner.nextInt();
            }
            if (scanner.hasNextInt()) {
                boardW = scanner.nextInt();
            }
            if (scanner.hasNextInt()) {
                blockCount = scanner.nextInt();
            }
            scanner.nextLine();
            if (scanner.hasNextLine()) {
                mode = scanner.nextLine();
            }
            if (boardW <= 0 || boardH <= 0 || blockCount <= 0 || !(mode.equals("DEFAULT") || mode.equals("CUSTOM") || mode.equals("PYRAMID"))) {
                scanner.close();
                throw new Exception("Invalid input");
            }

            if (mode.equals("CUSTOM")) {
                char[][] cusBoard = new char[boardH][boardW];
                for (int i = 0; i < boardH; i++) {
                    String line = scanner.nextLine();
                    for (int j = 0; j < boardW; j++) {
                        if (line.charAt(j) == '.') {
                            cusBoard[i][j] = ' ';
                        } else if (line.charAt(j) == 'X') {
                            cusBoard[i][j] = '.';
                        }
                    }
                }
                board = new Board(cusBoard);
                board.setCustom();
            } else {
                board = new Board(boardH, boardW);
            }

            char symbol = ' ';
            List<String> lines = new ArrayList<>();
            int piecesCount = 0;
            while (scanner.hasNextLine() && piecesCount < blockCount) {
                String line = scanner.nextLine().stripTrailing();
                if (line.isEmpty()) {
                    continue;
                }
                for (char c : line.toCharArray()) {
                    if (c != ' ' && c != symbol) {
                        if (symbol != ' ') {
                            lines = addPadding(lines);
                            Block block = new Block(String.join("\n", lines));
                            blocks.add(block);
                            piecesCount++;
                            lines = new ArrayList<>();
                        }
                        symbol = c;
//                        lines.add(line);
                        break;
                    }
                }
                lines.add(line);
            }
            if (piecesCount < blockCount) {
                lines = addPadding(lines);
                Block block = new Block(String.join("\n", lines));
                blocks.add(block);
            }

            scanner.close();

            System.out.println("Board height: " + boardH);
            System.out.println("Board width: " + boardW);
            System.out.println("Mode: " + mode);
            System.out.println("Block count: " + blockCount + "\n");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void solve() {
        solve = new Solve(blocks, board);
        boolean solved = solve.solve();
        solve.printSolution();
        if (solved) {

//        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Apakah anda ingin menyimpan solusi? (ya/tidak)");
            String save = scan.nextLine();
            if (save.equals("ya")) {
                System.out.println("Simpan dalam gambar atau teks? (ketik png/txt)");
                String type = scan.nextLine();
                if (type.equals("txt")) {
                    saveSolusi();
                    break;
                } else if (type.equals("png")) {
                    createImage(solve.solution, 50);
                    break;
                } else {
                    System.out.println("Input tidak valid");
                }
            } else if (save.equals("tidak")) {
                System.out.println("Solusi tidak disimpan");
                break;
            } else {
                System.out.println("Input tidak valid");
            }
        }
//        scanner.close();
        } else {
            System.out.println("No solution found");
        }
    }

    private List<String> addPadding(List<String> lines) {
        int maxLen = lines.stream().mapToInt(String::length).max().orElse(0);
        List<String> padded = new ArrayList<>();
        for (String line : lines) {
            padded.add(String.format("%-" + maxLen + "s", line));
        }
        return padded;
    }

    private void saveSolusi() {
        try {
            int count = 0;
            File file;
            do {
                count++;
                file = new File("solusi" + count + ".txt");
            } while (file.exists());

            FileWriter writer = new FileWriter(file);
            for (char[] chars : solve.solution.board) {
                for (char boardChar : chars) {
                    writer.write(boardChar);
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("Solution saved to " + file.getName());
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    public void runGame() {

        String path;
        File file;
        while (true) {
            System.out.println("Masukkan nama file test case: ");
            path = scan.nextLine();
            file = new File(path);
            if (file.exists()) {
                break;
            }
            System.out.println("File tidak ditemukan");
        }
        loadFromTxt(path);
        solve();
    }

    private static void createImage(Board board, int scale) {
        int rows = board.height;
        int cols = board.width;
        int width = cols * scale;
        int height = rows * scale;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                g2d.setColor(char2color(board.board[i][j]));
                g2d.fillRect(j * scale, i * scale, scale, scale);
            }
        }

        g2d.dispose();

        try {
            int count = 0;
            File file;
            do {
                count++;
                file = new File("img_solusi" + count + ".png");
            } while (file.exists());
            ImageIO.write(image, "png", file);
            System.out.println("Gambar disimpan: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Color char2color(char c) {
        return switch (c) {
            case 'A' -> new Color(255, 0, 0,255);
            case 'B' -> new Color(0, 255, 0, 255);
            case 'C' -> new Color(255, 255, 0, 255);
            case 'D' -> new Color(0, 0, 255, 255);
            case 'E' -> new Color(255, 0, 255, 255);
            case 'F' -> new Color(0, 255, 255, 255);
            case 'G' -> new Color(255, 165, 0, 255);
            case 'H' -> new Color(128, 0, 128, 255);
            case 'I' -> new Color(128, 128, 0, 255);
            case 'J' -> new Color(0, 128, 128, 255);
            case 'K' -> new Color(128, 0, 0, 255);
            case 'L' -> new Color(0, 128, 0, 255);
            case 'M' -> new Color(0, 0, 128, 255);
            case 'N' -> new Color(210, 105, 30, 255);
            case 'O' -> new Color(0, 191, 255, 255);
            case 'P' -> new Color(154, 205, 50, 255);
            case 'Q' -> new Color(255, 20, 147, 255);
            case 'R' -> new Color(123, 104, 238, 255);
            case 'S' -> new Color(72, 61, 139, 255);
            case 'T' -> new Color(255, 105, 180, 255);
            case 'U' -> new Color(64, 224, 208, 255);
            case 'V' -> new Color(46, 139, 87, 255);
            case 'W' -> new Color(255, 69, 0, 255);
            case 'X' -> new Color(0, 250, 154, 255);
            case 'Y' -> new Color(139, 69, 19, 255);
            case 'Z' -> new Color(75, 0, 130, 255);
            default -> new Color(0,0,0,0);
        };
    }

}
