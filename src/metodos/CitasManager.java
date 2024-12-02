package metodos;
import entidades.Citas;

import java.io.*;
import java.util.ArrayList;

public class CitasManager {

    private static final String FILE_PATH = "h:\\Sistema_HealthPlus\\citas.txt";  // File path

    // Method to save a list of appointments to the file
    public static void guardarCita(Citas cita) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Write the appointment details as a comma-separated line
            writer.write(cita.getId() + "," + cita.getDoctor() + "," + cita.getFecha() + "," + cita.getHora() + "," + cita.getNotas());
            writer.newLine();  // Add a new line after each appointment
            System.out.println("Cita creada y guardada: " + cita.toString());
        } catch (IOException e) {
            System.err.println("Error escribiendo en archivo: " + e.getMessage());
        }
    }

    // Method to read all appointments from the file
    public static ArrayList<Citas> leerCitas() {
        ArrayList<Citas> citasList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    // Create a Cita object and add it to the list
                    Citas cita = new Citas(parts[1], parts[2], parts[3], parts[4], parts[0]);
                    citasList.add(cita);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading appointments from file: " + e.getMessage());
        }
        return citasList;
    }

    // Method to get an appointment by patient ID
    public static Citas getAppointmentByPatientId(String pacienteId) {
        ArrayList<Citas> citasList = leerCitas();
        for (Citas cita : citasList) {
            if (cita.getId().equals(pacienteId)) {
                return cita;
            }
        }
        return null;  // Return null if no appointment found
    }
}