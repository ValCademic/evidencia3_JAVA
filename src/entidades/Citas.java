package entidades;

import java.io.Serializable;

public class Citas implements Serializable {
    private String doctor;
    private String fecha;
    private String hora;
    private String notas;
    private String pacienteId;  // Optional: Associate the appointment with a patient

    // Constructor
    public Citas(String doctor, String fecha, String hora, String notas, String pacienteId) {
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.notas = notas;
        this.pacienteId = pacienteId;
    }

    // Getter methods
    public String getDoctor() {
        return doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getNotas() {
        return notas;
    }

    public String getId() {
        return pacienteId;
    }

    // To return a string representation of the Cita
    @Override
    public String toString() {
        return "Doctor: " + doctor + ", Fecha: " + fecha + ", Hora: " + hora + ", Notas: " + notas;
    }
}