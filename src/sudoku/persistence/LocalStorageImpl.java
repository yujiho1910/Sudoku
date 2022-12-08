package sudoku.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sudoku.problemDomain.IStorage;
import sudoku.problemDomain.SudokuGame;

public class LocalStorageImpl implements IStorage {

    private static File GAME_DATA = new File(System.getProperty("user.home"), "gamedata.txt");

    @Override
    public void updateGameData(SudokuGame game) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(GAME_DATA);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(game);
            out.close();
        } catch (IOException e) {
            throw new IOException("Unable to access Game Data");
        }
    }

    @Override
    public SudokuGame getGameData() throws IOException {
            FileInputStream fis = new FileInputStream(GAME_DATA);
            ObjectInputStream in = new ObjectInputStream(fis);
        try {
            SudokuGame gameState = (SudokuGame) in.readObject();
            in.close();
            return gameState;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            in.close();
            throw new IOException("File Not Found");
        }
    }
}
