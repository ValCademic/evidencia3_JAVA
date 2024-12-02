import entidades.Doctor;
import entidades.Paciente;
import metodos.doctoresCRUD;
import metodos.pacientesCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static metodos.CitasManager.getAppointmentByPatientId;

public class crearCita extends JFrame {
    private JButton cancelarButton;
    private JButton btnIngresar;
    private JButton buscarButton;
    private JTextArea txtObserv;
    private JComboBox cmbHora;
    private JComboBox cmbMinutos;
    private JComboBox cmbAnio;
    private JComboBox cmbMes;
    private JComboBox cmbDia;
    private JTextField txtPaciente;
    private JComboBox cmbPaciente;
    private JTextField txtFecha;
    private JPanel miPanel;
    private JComboBox cmbDoctor;
    private JTextField txtDoctor;
    ArrayList<Paciente> lista;
    ArrayList<Doctor> list;

    public boolean validaFecha(String fecha) {
        SimpleDateFormat formatoFecha =
                new SimpleDateFormat("dd/MM/yyyy");
        try {
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    //método constructor
    public crearCita() {
        //conectarme al archivo y extraer la lista
        try {
            FileInputStream leer =
                    new FileInputStream("h:\\Sistema_HealthPlus\\pacientes.txt");
            ObjectInputStream lectorObjetos = new ObjectInputStream(leer);
            Object o = lectorObjetos.readObject();
            lista = (ArrayList<Paciente>) o;

            for (Paciente a : lista) {
                cmbPaciente.addItem(a.getID());
                lectorObjetos.close();
                leer.close();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException|IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileInputStream leer =
                    new FileInputStream("h:\\Sistema_HealthPlus\\doctores.txt");
            ObjectInputStream lectorObjetos = new ObjectInputStream(leer);
            Object o = lectorObjetos.readObject();
            list = (ArrayList<Doctor>) o;

            for (Doctor a : list) {
                cmbDoctor.addItem(a.getID());
                lectorObjetos.close();
                leer.close();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException|IOException e) {
            throw new RuntimeException(e);
        }

        cmbPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pacientesCRUD crud = new pacientesCRUD();
                String mat = String.valueOf(cmbPaciente.getSelectedItem());
                String nombreCompleto;
                Paciente a = crud.getInfoPacientePorNombre(mat);
                nombreCompleto = a.getNombre() + " " + a.getApellidoPat()+ " " + a.getApellidoMat();
                txtPaciente.setText(nombreCompleto);
                String Id= a.getID();
                String cita = String.valueOf(getAppointmentByPatientId(Id));
                System.out.println(Id);

            }
        });
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fecha = cmbDia.getSelectedItem().toString() + "/" + cmbMes.getSelectedItem().toString() + "/" + cmbAnio.getSelectedItem().toString();

                boolean resultado = validaFecha(fecha);
                if (resultado == true) {
                    JOptionPane.showMessageDialog(miPanel, "No existen impedimientos en la fecha establecida.");
                } else {
                    JOptionPane.showMessageDialog(miPanel, "Error en la fecha.");
                }
            }
        });
// ActionListener for selecting a patient from cmbPaciente
        cmbPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientID = cmbPaciente.getSelectedItem().toString();
                Paciente selectedPatient = getPacienteByID(patientID);

                if (selectedPatient != null) {
                    txtPaciente.setText(selectedPatient.getNombre() + " " + selectedPatient.getApellidoPat());
                    }
            }
        });

        // ActionListener for the "Ingresar" button (to validate date)
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fecha = cmbDia.getSelectedItem().toString() + "/" +
                        cmbMes.getSelectedItem().toString() + "/" +
                        cmbAnio.getSelectedItem().toString();
                boolean resultado = validaFecha(fecha);
                if (resultado) {
                    JOptionPane.showMessageDialog(miPanel, "No existen impedimientos en la fecha establecida.");
                } else {
                    JOptionPane.showMessageDialog(miPanel, "Error en la fecha.");
                }
            }
        });

        // ActionListener for "Cancelar" button
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(miPanel, "Desea volver al menu?", "Salir", JOptionPane.YES_NO_OPTION);
                if (respuesta == 0) {
                    dispose();
                    String[] tipoUsuario = {"admin"};
                    Sistema.main(tipoUsuario);
                }
            }
        });
        cmbDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctoresCRUD crud = new doctoresCRUD();
                String id = String.valueOf(cmbDoctor.getSelectedItem());
                String nombreCompleto;
                Doctor a = crud.getInfoDoctorPorNombre(id);
                nombreCompleto = a.getNombre() + " " + a.getApellidoPat()+ " " + a.getApellidoMat();
                txtDoctor.setText(nombreCompleto);
                String Id= a.getID();
                String cita = String.valueOf(getAppointmentByPatientId(Id));
                System.out.println(Id);
            }
        });
    }

    // Get Paciente object by ID
    private Paciente getPacienteByID(String id) {
        for (Paciente p : lista) {
            if (p.getID().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        crearCita vC = new crearCita();
        vC.setContentPane(vC.miPanel);
        vC.setSize(500, 500);
        vC.setTitle("Citas");
        vC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vC.setVisible(true);
    }
}