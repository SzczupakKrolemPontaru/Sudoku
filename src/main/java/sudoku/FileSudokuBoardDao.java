package sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.exceptions.DaoException;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    String fileName;
    private static Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws DaoException {
        SudokuBoard board = null;

        try (FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            board = (SudokuBoard) objectInputStream.readObject();
        } catch (IOException  | ClassNotFoundException e) {
            close();
            logger.info(BundleManager
                            .getInstance()
                            .getBundle()
                            .getString("Exception"),
                    new DaoException(BundleManager
                            .getInstance()
                            .getBundle()
                            .getString("DaoException")));
            throw new DaoException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("DaoException"));
        }
        return board;
    }

    @Override
    public void write(SudokuBoard board) throws DaoException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
             objectOutputStream.writeObject(board);
        } catch (IOException e) {
            close();
            logger.info(BundleManager
                            .getInstance()
                            .getBundle()
                            .getString("Exception"),
                    new DaoException(BundleManager
                            .getInstance()
                            .getBundle()
                            .getString("DaoException")));
            throw new DaoException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("DaoException"));
        }
    }

    @Override
    public void close() {
        logger.info("Closed");
    }
}
