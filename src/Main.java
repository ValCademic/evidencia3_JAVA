import entidades.Citas;
import entidades.Doctor;
import entidades.Paciente;
import metodos.CitasManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();

            Paciente a1 = new Paciente();
            a1.setID("0000");
            a1.setNombre("Pedro");
            a1.setApellidoPat("Jimenez");
            a1.setApellidoMat("Perez");
            a1.setEdad(19);

            Paciente a2 = new Paciente();
            a2.setID("0001");
            a2.setNombre("Ana");
            a2.setApellidoPat("Gutierrez");
            a2.setApellidoMat("Sanchez");
            a2.setEdad(18);

            Paciente a3 = new Paciente();
            a3.setID("0002");
            a3.setNombre("Juan");
            a3.setApellidoPat("Lopez");
            a3.setApellidoMat("Pedroza");
            a3.setEdad(21);

            listaPacientes.add(a1);
            listaPacientes.add(a2);
            listaPacientes.add(a3);



            FileOutputStream escribir = new FileOutputStream("h:\\Sistema_HealthPlus\\pacientes.txt");
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(listaPacientes);
            miStream.flush();
            miStream.close();
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
        ArrayList<Doctor> listaDoctores = new ArrayList<Doctor>();
        Doctor a0 = new Doctor();
        a0.setID("1000");
        a0.setNombre("Erick");
        a0.setApellidoPat("Lopez");
        a0.setApellidoMat("Cazarez");
        a0.setEdad(26);

        Doctor a4 = new Doctor();
        a4.setID("0034");
        a4.setNombre("Edith");
        a4.setApellidoPat("Garcia");
        a4.setApellidoMat("Suarez");
        a4.setEdad(23);

        Doctor a6 = new Doctor();
        a6.setID("0015");
        a6.setNombre("Mario");
        a6.setApellidoPat("Lechuga");
        a6.setApellidoMat("Juarez");
        a6.setEdad(21);

        Doctor a5 = new Doctor();
        a5.setID("0015");
        a5.setNombre("Samara");
        a5.setApellidoPat("Aganza");
        a5.setApellidoMat("Montaño");
        a5.setEdad(25);
        listaDoctores.add(a0);
        listaDoctores.add(a4);
        listaDoctores.add(a5);
        listaDoctores.add(a6);
        FileOutputStream escribir = new FileOutputStream("h:\\Sistema_HealthPlus\\doctores.txt");
        ObjectOutputStream miStream = new ObjectOutputStream(escribir);
        miStream.writeObject(listaDoctores);
        miStream.flush();
        miStream.close();
    }catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
        Citas newCita = new Citas("Dr. Edith", "05-12-2024", "10:00", "Resultados examen de tiroide", "0002");
        CitasManager.guardarCita(newCita);
        Citas newCita2 = new Citas("Dr. Erick", "13-01-2025", "13:25", "Ultrasonido Uterino", "0000");
        CitasManager.guardarCita(newCita2);
        Citas newCita3 = new Citas("Dr. Mario", "07-12-2024", "07:10", "Cita rutinaria", "0001");
        CitasManager.guardarCita(newCita3);
    }
}