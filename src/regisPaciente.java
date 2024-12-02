import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import entidades.Paciente;
import metodos.pacientesCRUD;

public class regisPaciente extends JFrame {
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



    public regisPaciente() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pacientesCRUD crud = new pacientesCRUD();
                String id = txtID.getText();
                Paciente a = crud.getInfoPacientePorNombre(id);
                if(a == null)
                {
                    int respuesta = JOptionPane.showConfirmDialog(miPanel,"No se encuentra información del paciente con #ID " + id + ". ¿Desea ingresarlo?","Registro",JOptionPane.YES_NO_OPTION);

                    if(respuesta == 0)
                    {
                        //sí quiere dar de alta el paciente inexistente
                        btnAgregar.setEnabled(true);
                        txtNombre.requestFocus();
                    }
                    else if(respuesta == 1)
                    {

                        txtID.setText("");
                        txtNombre.setText("");
                        txtApPat.setText("");
                        txtApMat.setText("");
                        txtEdad.setText("");
                    }
                }
                else
                {
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
                Paciente miPaciente = new Paciente();
                miPaciente.setID(txtID.getText());
                miPaciente.setNombre(txtNombre.getText());
                miPaciente.setApellidoPat(txtApPat.getText());
                miPaciente.setApellidoMat(txtApMat.getText());
                miPaciente.setEdad(Integer.parseInt(txtEdad.getText()));

                pacientesCRUD crud = new pacientesCRUD();
                crud.agregarPaciente(miPaciente);
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
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
    }
    public static void main(String[] args)
    {
        regisPaciente v = new regisPaciente();
        v.setContentPane(v.miPanel);
        v.setSize(500,500);
        v.setTitle("Registrar o Consultar Pacientes");
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
