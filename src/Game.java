import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

public class Game {

    int boardW;
    int boardH;
    int blockCount;
    String mode;
    List<Block> blocks = new ArrayList<>();
    Board board;
    Solve solve;

    public Game() {
        boardW = 0;
        boardH = 0;
        blockCount = 0;
        mode = "";
    }

    public void loadFromTxt(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextInt()) {
                boardW = scanner.nextInt();
            }
            if (scanner.hasNextInt()) {
                boardH = scanner.nextInt();
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

            char symbol = ' ';
            List<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                for (char c : line.toCharArray()) {
                    if (c != ' ' && c != symbol) {
                        if (symbol != ' ') {
                            lines = addPadding(lines);
                            Block block = new Block(String.join("\n", lines));
                            blocks.add(block);
                            lines = new ArrayList<>();
                        }
                        symbol = c;
//                        lines.add(line);
                        break;
                    }
                }
                lines.add(line);
            }
            lines = addPadding(lines);
            Block block = new Block(String.join("\n", lines));
            blocks.add(block);

            scanner.close();

            board = new Board(boardH, boardW);

            System.out.println("Board width: " + boardW);
            System.out.println("Board height: " + boardH);
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
        if (solved) {
            solve.printSolution();
        } else {
            System.out.println("No solution found");
        }
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Apakah anda ingin menyimpan solusi? (ya/tidak)");
            String save = scanner.nextLine();
            if (save.equals("ya")) {
                saveSolusi();
                break;
            } else if (save.equals("tidak")) {
                System.out.println("Solusi tidak disimpan");
                break;
            } else {
                System.out.println("Input tidak valid");
            }
        }
        scanner.close();
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

}
