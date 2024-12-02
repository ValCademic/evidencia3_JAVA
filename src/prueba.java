import entidades.Citas;
import entidades.Paciente;
import metodos.CitasManager;
import metodos.pacientesCRUD;

public class prueba
{

    public static void main(String[] args)
    {
        pacientesCRUD paciCRUD = new pacientesCRUD();
        Paciente resultado = paciCRUD.getInfoPacientePorNombre("0000");

        if(resultado==null)
        {
            System.out.println("No se encuentra ese #ID.");
        }
        else
        {
            System.out.println(resultado.getNombre());
            System.out.println(resultado.getApellidoPat());
            System.out.println(resultado.getEdad());
        }
        String patientIdToSearch = "0002";
        Citas cita = CitasManager.getAppointmentByPatientId(patientIdToSearch);

        if (cita != null) {
            // Display the appointment details
            System.out.println("Cita encontrada: " + cita.toString());
        } else {
            System.out.println("No se encontro ninguna cita con el #ID: " + patientIdToSearch);
        }
    }
}