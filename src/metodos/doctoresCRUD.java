package metodos;

import entidades.Doctor;
import java.io.*;
import java.util.ArrayList;

public class doctoresCRUD {

    public static void agregarDoctor (Doctor a){
        ArrayList<String> doctores = new ArrayList<String>();

        try {
            FileInputStream leer =
                    new FileInputStream("h:\\Sistema_HealthPlus\\doctores.txt");
            ObjectInputStream lectorObjetos = new ObjectInputStream(leer);
            Object o = lectorObjetos.readObject();
            ArrayList<Doctor> lista = (ArrayList<Doctor>) o;

            lista.add(a);
            lectorObjetos.close();
            leer.close();

            FileOutputStream escribir =
                    new FileOutputStream("h:\\Sistema_HealthPlus\\doctores.txt");
            ObjectOutputStream escritorObjetos = new ObjectOutputStream(escribir);
            escritorObjetos.writeObject(lista);
            escritorObjetos.flush();

            escritorObjetos.close();
            escribir.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo.");
        } catch (IOException e) {
            System.out.println("Error IO: "+e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("No es un objeto valido que se pueda leer.");
        }
    }

    public Doctor getInfoDoctorPorNombre(String id) {
        try (FileInputStream leer = new FileInputStream("h:\\Sistema_HealthPlus\\doctores.txt");
             ObjectInputStream miStream2 = new ObjectInputStream(leer)) {
            Object miLista = miStream2.readObject();
            ArrayList<Doctor> listaDoctores = (ArrayList<Doctor>) miLista;

            for (Doctor k : listaDoctores) {
                System.out.println("Comparando con el #ID: " + k.getID()); // Debugging each patient ID
                if (id.equals(k.getID())) {
                    return k;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Clase no encontrada: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error IO: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarDoctor()
    {

    }

    public void eliminarDoctor(){


    }
}
