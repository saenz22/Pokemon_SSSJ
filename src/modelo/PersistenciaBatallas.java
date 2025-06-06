package modelo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PersistenciaBatallas {
    public static void guardar(ArrayList<Batalla> batallas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("batallas.ser"))) {
            oos.writeObject(batallas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Batalla> cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("batallas.ser"))) {
            return (ArrayList<Batalla>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}