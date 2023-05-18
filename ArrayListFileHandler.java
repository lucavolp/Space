import java.io.*;
import java.util.ArrayList;

public class ArrayListFileHandler {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Elemento 1");
        arrayList.add("Elemento 2");
        arrayList.add("Elemento 3");
        // Aggiungi altri elementi se necessario

        String nomeFile = "nome_del_file.dat";

        ArrayListFileHandler fileHandler = new ArrayListFileHandler();
        fileHandler.saveArrayListToFile(arrayList, nomeFile);
        ArrayList<?> loadedArrayList = fileHandler.loadArrayListFromFile(nomeFile);

        if (loadedArrayList != null) {
            for (Object element : loadedArrayList) {
                System.out.println(element);
            }
        } else {
            System.out.println("Impossibile caricare l'ArrayList dal file: " + nomeFile);
        }
    }

    public void saveArrayListToFile(ArrayList<?> arrayList, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(arrayList);
            System.out.println("ArrayList salvato correttamente nel file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<?> loadArrayListFromFile(String fileName) {
        ArrayList<?> arrayList = null;
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            arrayList = (ArrayList<?>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}

