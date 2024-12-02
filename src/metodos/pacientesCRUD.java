package metodos;

import entidades.Paciente;
import java.io.*;
import java.util.ArrayList;

public class pacientesCRUD {

    public static void agregarPaciente (Paciente a){
        ArrayList<String> pacientes = new ArrayList<String>();

        try {
            FileInputStream leer =
                    new FileInputStream("h:\\Sistema_HealthPlus\\pacientes.txt");
            ObjectInputStream lectorObjetos = new ObjectInputStream(leer);
            Object o = lectorObjetos.readObject();
            ArrayList<Paciente> lista = (ArrayList<Paciente>) o;

            lista.add(a);
            lectorObjetos.close();
            leer.close();

            FileOutputStream escribir =
                    new FileOutputStream("h:\\Sistema_HealthPlus\\pacientes.txt");
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

   public Paciente getInfoPacientePorNombre(String id) {
       try (FileInputStream leer = new FileInputStream("h:\\Sistema_HealthPlus\\pacientes.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer)) {
           Object miLista = miStream2.readObject();
           ArrayList<Paciente> listaPacientes = (ArrayList<Paciente>) miLista;

           for (Paciente k : listaPacientes) {
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

    public void actualizarPaciente()
    {

    }

    public void eliminarPaciente(){


    }
}
