package database;

import java.io.*;

public class DBReadWriter {

    private final String FILE_PATH;

    public DBReadWriter(String path) {
        this.FILE_PATH = path;
    }

    public void write(Serializable object) throws IOException {
        OutputStream file = new FileOutputStream(FILE_PATH);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(object);
        output.close();
    }

    public Serializable read() throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(FILE_PATH);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        Serializable object = (Serializable) input.readObject();
        input.close();
        return object;
    }
}
