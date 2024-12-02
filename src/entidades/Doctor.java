package entidades;
import java.io.Serializable;

public class Doctor implements Serializable {
        private String id, cita, nombre, apellidoPat, apellidoMat;
        private int edad;

        public Doctor()
        {
            System.out.println("Se registró exitosamente.");
        }

        public Doctor(String n)
        {

            this.nombre = n;
        }

        public String getID()
        {

            return id;
        }

        public void setID(String id)
        {
            this.id = id;
        }

        public String getCita()
        {

            return cita;
        }

        public void setCita(String cita)
        {
            this.cita = cita;
        }

        public String getNombre()
        {

            return nombre;
        }

        public void setNombre(String nombre)
        {
            this.nombre = nombre;
        }

        public String getApellidoPat()
        {
            return apellidoPat;
        }

        public void setApellidoPat(String apellidoPat)
        {
            this.apellidoPat = apellidoPat;
        }

        public String getApellidoMat()
        {
            return apellidoMat;
        }

        public void setApellidoMat(String apellidoMat)
        {
            this.apellidoMat = apellidoMat;
        }

        public int getEdad()
        {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }


}
