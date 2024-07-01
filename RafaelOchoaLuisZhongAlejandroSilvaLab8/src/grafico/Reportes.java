package grafico;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logica.Becas;
import logica.Estudiantes;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

public class Reportes extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldCedula;
    private JTextArea textAreaResultados;
    private JComboBox<String> comboBoxCarrera;
    private JComboBox<String> comboBoxSexo;
    private Becas becas;

    public Reportes(Becas becas) {
        this.becas = becas;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 666, 483);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Reportes");
        lblNewLabel.setBounds(238, 10, 149, 46);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Cédula:");
        lblNewLabel_1.setBounds(52, 106, 95, 22);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        contentPane.add(lblNewLabel_1);

        textFieldCedula = new JTextField();
        textFieldCedula.setBounds(157, 106, 153, 21);
        contentPane.add(textFieldCedula);
        textFieldCedula.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Carrera:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(52, 159, 95, 22);
        contentPane.add(lblNewLabel_2);

        comboBoxCarrera = new JComboBox<>();
        comboBoxCarrera.setModel(new DefaultComboBoxModel<>(new String[] {
            "Ingeniería Civil", "Ingeniería Eléctrica", "Ingeniería Industrial", "Ingeniería en Sistemas", "Ingeniería Mecánica", "Ingeniería Marítima"
        }));
        comboBoxCarrera.setBounds(157, 163, 153, 21);
        comboBoxCarrera.setSelectedIndex(-1);
        contentPane.add(comboBoxCarrera);

        JLabel lblNewLabel_3 = new JLabel("Sexo:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(52, 212, 95, 22);
        contentPane.add(lblNewLabel_3);

        comboBoxSexo = new JComboBox<>();
        comboBoxSexo.setModel(new DefaultComboBoxModel<>(new String[] {"Masculino", "Femenino"}));
        comboBoxSexo.setBounds(157, 216, 153, 21);
        comboBoxSexo.setSelectedIndex(-1);
        contentPane.add(comboBoxSexo);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnBuscar.setBounds(157, 269, 153, 34);
        contentPane.add(btnBuscar);

        textAreaResultados = new JTextArea();
        textAreaResultados.setEditable(false);
        textAreaResultados.setBounds(52, 320, 524, 104);
        contentPane.add(textAreaResultados);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarBusqueda();
            }
        });
    }

    private void realizarBusqueda() {
        String cedula = textFieldCedula.getText();
        String carrera = (String) comboBoxCarrera.getSelectedItem();
        String sexo = (String) comboBoxSexo.getSelectedItem();

        StringBuilder sb = new StringBuilder();

        if (!cedula.isEmpty()) {
            Estudiantes estudiante = becas.buscarPorCedula(cedula);
            if (estudiante != null) {
                sb.append("Estudiante encontrado: \n");
                sb.append(estudiante.getNombre()).append(" - ").append(estudiante.getCarrera()).append(" - ").append(estudiante.getSexo()).append("\n");
            } else {
                sb.append("No se encontró un estudiante con la cédula ").append(cedula).append("\n");
            }
        } else {
            if (carrera != null) {
                ArrayList<Estudiantes> estudiantesPorCarrera = becas.buscarPorCarrera(carrera);
                if (!estudiantesPorCarrera.isEmpty()) {
                    sb.append("Estudiantes en ").append(carrera).append(":\n");
                    for (Estudiantes estudiante : estudiantesPorCarrera) {
                        sb.append(estudiante.getNombre()).append(" - ").append(estudiante.getSexo()).append("\n");
                    }
                } else {
                    sb.append("No se encontraron estudiantes en la carrera ").append(carrera).append("\n");
                }
            }

            if (sexo != null) {
                ArrayList<Estudiantes> estudiantesPorSexo = becas.buscarPorSexo(sexo);
                if (!estudiantesPorSexo.isEmpty()) {
                    sb.append("Estudiantes de sexo ").append(sexo).append(":\n");
                    for (Estudiantes estudiante : estudiantesPorSexo) {
                        sb.append(estudiante.getNombre()).append(" - ").append(estudiante.getCarrera()).append("\n");
                    }
                } else {
                    sb.append("No se encontraron estudiantes de sexo ").append(sexo).append("\n");
                }
            }
        }

        textAreaResultados.setText(sb.toString());
    }
}
