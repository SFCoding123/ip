package duke.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Storage.
 */
class Storage {
    /**
     * The Writer.
     */
    private FileWriter writer;
    /**
     * The Reader.
     */
    private Scanner reader;
    /**
     * The Records.
     */
    private ArrayList<String> records = new ArrayList<>();
    /**
     * The Path.
     */
    private String path;

    private File file;

    /**
     * Instantiates a new Storage.
     *
     * @param des the des
     */
    Storage(String des) {
        try {
            this.file = new File(des);
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
                path = file.getPath();
            } else {
                path = des;
            }
            writer = new FileWriter(path, true);
            reader = new Scanner(file);
        } catch (IOException e) {
            System.out.println("Invalid file Path\n");
        }
    }

    /**
     * Gets records.
     *
     * @param index the index
     * @return the records
     */
    public String getRecords(int index) {
        return records.get(index);
    }

    /**
     * Is there records boolean.
     *
     * @return the boolean
     */
    public boolean isThereRecords() {
        if (records == null) {
            return false;
        } else {
            return records.isEmpty();
        }
    }

    /**
     * Record size int.
     *
     * @return the int
     */
    public int recordSize() {
        return records.size();
    }

    /**
     * Write inputs into memory.
     *
     * @param input the input
     */
    void write(String input) {
        records.add(input);
    }

    /**
     * Mark at.
     * mark index in memory
     *
     * @param index the index
     */
    void markAt(int index) {
        assert index >= 0 : "wrong index";
        String str = records.get(index).replace("false", "true");
        records.set(index, str);
    }

    /**
     * Unmark at.
     * unmark index in memory
     *
     * @param index the index
     */
    void unmarkAt(int index) {
        assert index >= 0 : "wrong index";
        String str = records.get(index).replace("true", "false");
        records.set(index, str);
    }

    /**
     * Detele at.
     * delete record at index
     *
     * @param index the index
     */
    void deteleAt(int index) {
        assert index >= 0 : "wrong index";
        records.remove(index);
    }


    /**
     * Read.
     * Read Txt file
     */
    void read() {
        try {
            File userData = new File("Data");
            if (!userData.exists()) {
                userData.mkdir();
            }
            File dukeTxt = new File(userData, "duke.txt");
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                records.add(data);
            }
            reader.close();
        } catch (Exception e) {
            //exception throwed
        }
    }


    /**
     * Write all.
     * write into Txt file
     */
    void writeAll() {
        try {
            clearFile();
            for (String record : records) {
                writer.write(record);
                writer.write(System.lineSeparator());
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println("Write Error");
        }
    }

    /**
     * Clear file.
     *
     * @throws IOException the io exception
     */
    void clearFile() throws IOException {
        FileWriter f1 = new FileWriter("./Data/duke.txt", false);
        PrintWriter p1 = new PrintWriter(f1, false);
        p1.flush();
        p1.close();
        p1.close();
    }


}
