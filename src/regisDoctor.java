import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entidades.Doctor;
import entidades.Paciente;
import metodos.doctoresCRUD;
import metodos.pacientesCRUD;

public class regisDoctor extends JFrame {
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnAgregar;
    private JButton btnCancelar;
    public JPanel miPanel;
    private JTextField txtNombre;
    private JTextField txtApPat;
    private JTextField txtApMat;
    private JTextField txtEdad;
    private JComboBox comboBox1;
    private JTextField txtID;



    public regisDoctor() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctoresCRUD crud = new doctoresCRUD();
                String id = txtID.getText();
                Doctor a = crud.getInfoDoctorPorNombre(id);
                if(a == null){
                    int respuesta = JOptionPane.showConfirmDialog(miPanel,"No se encuentra información del doctor con #ID " + id + ". ¿Desea ingresarlo?","Registro",JOptionPane.YES_NO_OPTION);
                    if(respuesta == 0){
                        //sí quiere dar de alta el paciente inexistente
                        btnAgregar.setEnabled(true);
                        txtNombre.requestFocus();
                    }else if(respuesta == 1) {

                        txtID.setText("");
                        txtNombre.setText("");
                        txtApPat.setText("");
                        txtApMat.setText("");
                        txtEdad.setText("");
                    }

                }
                else{
                    //se encontro el paciente
                    txtNombre.setText(a.getNombre());
                    txtApPat.setText(a.getApellidoPat());
                    txtApMat.setText(a.getApellidoMat());
                    txtEdad.setText(String.valueOf(a.getEdad()));
                    btnCancelar.setEnabled(true);
                    btnEliminar.setEnabled(true);
                }
            }
        });
        btnAgregar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Doctor miDoctor = new Doctor();
                miDoctor.setID(txtID.getText());
                miDoctor.setNombre(txtNombre.getText());
                miDoctor.setApellidoPat(txtApPat.getText());
                miDoctor.setApellidoMat(txtApMat.getText());
                miDoctor.setEdad(Integer.parseInt(txtEdad.getText()));

                doctoresCRUD crud = new doctoresCRUD();
                crud.agregarDoctor(miDoctor);
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(miPanel,"Desea volver al menu?","Salir",JOptionPane.YES_NO_OPTION);
                if(respuesta == 0) {
                    dispose();
                    String[] tipoUsuario = {"admin"};
                    Sistema.main(tipoUsuario);
                }
            }
        });
    }
    public static void main(String[] args)
    {
        regisDoctor vD = new regisDoctor();
        vD.setContentPane(vD.miPanel);
        vD.setSize(500,500);
        vD.setTitle("Registrar o Consultar Pacientes");
        vD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vD.setVisible(true);
    }
}
