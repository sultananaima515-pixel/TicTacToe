import java.awt.*;
import javax.swing.*;

public class TicTacToe {

    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    public TicTacToe() {

        // ================= APP ICON =================
        Image icon = new ImageIcon("icon.png").getImage();
        frame.setIconImage(icon);
        // ============================================

        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top text label
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 48));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("X's Turn");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // Board panel
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        // Create board buttons
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {

                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(e -> {
                    if (gameOver) return;
                    if (!tile.getText().equals("")) return;

                    tile.setText(currentPlayer);
                    turns++;
                    checkWinner();

                    if (!gameOver) {
                        currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                        textLabel.setText(currentPlayer + "'s Turn");
                    }
                });
            }
        }

        frame.setVisible(true);
    }

    // ================= GAME LOGIC =================

    void checkWinner() {

        // Rows
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText().equals("")) continue;

            if (board[r][0].getText().equals(board[r][1].getText()) &&
                board[r][1].getText().equals(board[r][2].getText())) {

                highlightWinner(board[r][0], board[r][1], board[r][2]);
                return;
            }
        }

        // Columns
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().equals("")) continue;

            if (board[0][c].getText().equals(board[1][c].getText()) &&
                board[1][c].getText().equals(board[2][c].getText())) {

                highlightWinner(board[0][c], board[1][c], board[2][c]);
                return;
            }
        }

        // Diagonal (top-left → bottom-right)
        if (!board[0][0].getText().equals("") &&
            board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText())) {

            highlightWinner(board[0][0], board[1][1], board[2][2]);
            return;
        }

        // Diagonal (top-right → bottom-left)
        if (!board[0][2].getText().equals("") &&
            board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText())) {

            highlightWinner(board[0][2], board[1][1], board[2][0]);
            return;
        }

        // Tie
        if (turns == 9) {
            setTie();
        }
    }

    // ================= UI HELPERS =================

    void highlightWinner(JButton a, JButton b, JButton c) {
        String winner = a.getText();

        a.setBackground(Color.gray);
        b.setBackground(Color.gray);
        c.setBackground(Color.gray);

        a.setForeground(Color.green);
        b.setForeground(Color.green);
        c.setForeground(Color.green);

        textLabel.setText(winner + " is the Winner!");
        gameOver = true;
    }

    void setTie() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setForeground(Color.orange);
            }
        }
        textLabel.setText("It's a Tie!");
        gameOver = true;
    }

    // ================= MAIN =================

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}
