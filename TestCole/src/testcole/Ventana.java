/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testcole;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static testcole.TestCole.colegio;
import static testcole.TestCole.escribeError;

/**
 *
 * @author Diurno
 */
public class Ventana extends JFrame implements ActionListener{
        
    private JButton izqBt, drBt, matriculaBt, guardarBt, altaBt, bajaBt, editarBt;
    private JLabel cursoLb, dniLb, nombreLb, fechaNacimientoLb;
    private JTextField cursoTf, dniTf, nombreTf, fechaNacimientoTf;
    private JRadioButton nombreRb, cursoRb;
    private JPanel panelAsignaturaPn;
    private JCheckBox a1, a2;
    private JComboBox<String> cursoCb;
    private Colegio cole;
    public int alumno = 0;
    public ArrayList<Alumno> alumnos = new ArrayList();
    public int cursoSeleccionado;
    
    public Ventana(Colegio cole){
        this.cole=cole;
        seleccionaCurso("Todos");
        cole.ordenaPorNombre();
        System.out.println("Alumnos sin cmabios" + alumnos);
        
        //PANELS
        panelAsignaturaPn = new JPanel();
        panelAsignaturaPn.setLayout(new GridLayout(0, 1));
        panelAsignaturaPn.setBounds(300, 10, 200, 270);
        panelAsignaturaPn.setBorder(BorderFactory.createTitledBorder("Asignaturas"));
        panelAsignaturaPn.setVisible(false);
        
        
        //CHECKBOXES INTO PANEL
        //a1 = new JCheckBox("Asignatura1");
        //a1.setBounds(30, 30, 100, 30);
        
        //a2 = new JCheckBox("Asignatura2");
        //a2.setBounds(30, 60, 100, 30);
        
        //panelAsignaturaPn.add(a1);
        //panelAsignaturaPn.add(a2);
        
        //BUTTON INTO PANNEL
        guardarBt=new JButton("Guardar");
        guardarBt.setBounds(80, 500, 120, 30);
        guardarBt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                panelAsignaturaPn.setVisible(false);
                guardarBt.setVisible(false);
                Ventana.this.setSize(300, 400);
                izqActiva(true);
            }
        });
        
        //COMBOBOX
        cursoCb=new JComboBox<String>();
        cursoCb.setBounds(150, 20, 80, 30);
        add(cursoCb);
        cursoCb.addItem("Todos");
        cursoCb.addItem("1");
        cursoCb.addItem("2");
        cursoCb.addItem("3");
        cursoCb.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                System.out.println("CAMBIO CURSO a "+(String)cursoCb.getSelectedItem() );
                seleccionaCurso((String)cursoCb.getSelectedItem());
                alumno=0;
                System.out.println(alumnos.toString());
                cargaAlumno();
            }
        });
        
        

        //LAYOUT
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(300,450);
        
        //BUTTONS
        izqBt=new JButton("<<");
        izqBt.setBounds(20, 250, 80 ,30);
        izqBt.addActionListener(this);
        
        drBt=new JButton(">>");
        drBt.setBounds(180, 250, 80, 30);
        drBt.addActionListener(this);
        
        matriculaBt=new JButton("Matricula");
        System.out.println("INICIO MATRICULA");
        matriculaBt.setBounds(80, 300, 120, 30);
       // matriculaBt.addActionListener(this);
       matriculaBt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                panelAsignaturaPn.setVisible(true);
                guardarBt.setVisible(true);
                cargaAlumno();        
                Ventana.this.setSize(600, 450);
                izqActiva(false);

                for(int i=0;i<Colegio.getCURSO_ASIGNATURAS().length;i++){
                        boolean checked=false;
                        //Como el array de COlegio es de cadenas, y no de asignaturas no puedo usar un contains al no ser del mismo tipo
                        for(Asignatura a : alumnos.get(alumno).getAsignaturas()){
                            if(a.getNombreAsig().equalsIgnoreCase(Colegio.getCURSO_ASIGNATURAS()[alumnos.get(alumno).getCurso()-1][i])){
                                checked=true;
                                System.out.println(a.toString()+"esta activada");
                                break;
                            }else{
                            checked=false;
                                System.out.println(a.toString()+"NO esta activada");
                            }
                        }
                        System.out.println("Este alumno cursa: "+alumnos.get(alumno).getAsignaturas().toString());
                        a1 = new JCheckBox(Colegio.getCURSO_ASIGNATURAS()[alumnos.get(alumno).getCurso()-1][i], checked);
                        panelAsignaturaPn.add(a1);
                        
                        
                        
                        
                    
                    
                    System.out.println(Colegio.getCURSO_ASIGNATURAS()[alumnos.get(alumno).getCurso()-1][i]);
                
                }
                
                
            }
       });
       
       guardarBt=new JButton("Guardar");
        guardarBt.setBounds(330, 300, 120, 30);
        guardarBt.setVisible(false);
        guardarBt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                System.out.println("ACTIVO GUARDAR");
                panelAsignaturaPn.setVisible(false);
                Ventana.this.setSize(300, 450);
                izqActiva(true);
                Asignatura asig;
                int contadorAsig=0;
                System.out.println("de las asignaturas: ");
                //Si borro uno los indices de todeos cambian así que se queda alguno sin borar a menos que el indice empiece por abajo
                for(int i=0; i<=panelAsignaturaPn.getComponents().length-1;i++){
                    System.out.println(panelAsignaturaPn.getComponent(i).toString());
                }
                System.out.println("Limpio Asignaturas del alumno");
                alumnos.get(alumno).getAsignaturas().clear();
                System.out.println("Este alumno cursa: "+alumnos.get(alumno).getAsignaturas().toString());
                for(int i=0; i<=panelAsignaturaPn.getComponents().length+1;i++){
                    JCheckBox CbAuxiliar = new JCheckBox();
                    if(((JCheckBox)panelAsignaturaPn.getComponent(0)).isSelected()){
                        System.out.println("Creo: "+panelAsignaturaPn.getComponent(0).toString() + "que es el numero " + contadorAsig);
                        
                        asig=new Asignatura(Colegio.getCURSO_ASIGNATURAS()[alumnos.get(alumno).getCurso()-1][contadorAsig]);
                        alumnos.get(alumno).getAsignaturas().add(asig);
                   
                    }
                   
                   
                   
                   System.out.println("Borro: "+panelAsignaturaPn.getComponent(0).toString());
                   panelAsignaturaPn.remove(panelAsignaturaPn.getComponent(0));
                   contadorAsig++;
                   
                   System.out.println("Este alumno cursa: "+alumnos.get(alumno).getAsignaturas().toString());
                
                }
                /*
                for(int i=0; i<=panelAsignaturaPn.getComponents().length+1;i++){
                   System.out.println("Borro: "+panelAsignaturaPn.getComponent(0).toString());
                   panelAsignaturaPn.remove(panelAsignaturaPn.getComponent(0));
                    
                
                }*/
                
                /*
                alumnos.clear();
                alumnos=cole.getAlumnos();
                System.out.println(alumnos.toString());
                */
            }
            });
        
        
       
        altaBt = new JButton("Alta");
        altaBt.setBounds(20, 350, 80 ,30);
        altaBt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                System.out.println("ACTIVO ALTA");
                String datosBrutos = cursoTf.getText()+"/"+dniTf.getText()+"/"+nombreTf.getText()+"/"+fechaNacimientoTf.getText();
               
                String nombreFichero="errores_"+LocalDateTime.now().toString().replace(":", "_")+".txt";
                Alumno alu;
          
                try {
                    alu = new Alumno(Integer.parseInt(cursoTf.getText()), dniTf.getText(), nombreTf.getText(), fechaNacimientoTf.getText());
                    cole.insertaAlumno(alu);
                    BufferedWriter salidaAlumno = IO.abreEscrituraAnnadir("alumnos.txt");
                    IO.escribeLinea(salidaAlumno, datosBrutos);
                    IO.cierraEscritura(salidaAlumno);
                    System.out.println("construido alumno");
                    System.out.println(cole.getAlumnos());
                } catch (MiExcepcion ex) {
                    escribeError(nombreFichero, datosBrutos+"---Error: "+ex.getMessage());
                    
                }
                
                seleccionaCurso((String)cursoCb.getSelectedItem());
                
                if(nombreRb.isSelected()){
                    alumnos.sort(null);
                    alumno=0;
                    cargaAlumno();
                }else{
                    cursoCb.setSelectedIndex(0);
                    alumnos.sort(new ComparaCursoNombre());
                    alumno=0;
                    cargaAlumno();
                }
   
            }
            });
            
        
        bajaBt = new JButton("Baja");
        bajaBt.setBounds(180, 350, 80, 30);
        bajaBt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                System.out.println("ACTIVO BAJA");
          
                String datosExtraccion;
                datosExtraccion = alumnos.get(alumno).getCurso()+"/"+alumnos.get(alumno).getDni()+"/"+alumnos.get(alumno).getNombre()+"/"+alumnos.get(alumno).getFechaNacimiento();
                colegio.getAlumnos().remove(alumnos.get(alumno));
                        
                BufferedWriter salidaExpulsados = IO.abreEscrituraAnnadir("ficheroExpulsados.txt");
                IO.escribeLinea(salidaExpulsados, datosExtraccion);
                IO.cierraEscritura(salidaExpulsados);
                
                seleccionaCurso((String)cursoCb.getSelectedItem());
                
                if(nombreRb.isSelected()){
                    alumnos.sort(null);
                    alumno=0;
                    cargaAlumno();
                }else{
                    cursoCb.setSelectedIndex(0);
                    alumnos.sort(new ComparaCursoNombre());
                    alumno=0;
                    cargaAlumno();
                }
            }
            });
        editarBt = new JButton("Editar");
        editarBt.setBounds(105, 350, 70, 30);
        editarBt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                System.out.println("ACTIVO EDITAR");
                
            }
            });
        
        //RADIALBUTTONS 
        nombreRb = new JRadioButton("Ordena por nombre");
        nombreRb.setSelected(true);
        nombreRb.setBounds(100, 170, 150, 30);
        nombreRb.addActionListener(this);
        
        cursoRb = new JRadioButton("Ordena por curso");
        cursoRb.setBounds(100, 200, 150, 30);
        cursoRb.addActionListener(this);
        
        //ButtonGroup
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(nombreRb);
        grupo.add(cursoRb);
        
        
        //LABELS
        cursoLb = new JLabel("Curso:"); 
        cursoLb.setBounds(20, 20, 50, 30);
        
        dniLb = new JLabel("DNI:"); 
        dniLb.setBounds(20, 60, 50, 30);
        
        nombreLb = new JLabel("Nombre:"); 
        nombreLb.setBounds(20, 100, 50, 30);
        
        fechaNacimientoLb = new JLabel("Fecha de nacimiento:"); 
        fechaNacimientoLb.setBounds(20, 140, 150, 30);
        
        //TEXTFIELDS
        cursoTf = new JTextField(10); 
        cursoTf.setBounds(80, 20, 50, 30);
         
        dniTf = new JTextField(30); 
        dniTf.setBounds(80, 60, 150, 30);
        
        nombreTf = new JTextField(60); 
        nombreTf.setBounds(80, 100, 150, 30);
        
        fechaNacimientoTf = new JTextField(30); 
        fechaNacimientoTf.setBounds(160, 140, 100, 30);
        
        
        
        
        //INIT
        this.add(izqBt);
        this.add(drBt);
        this.add(matriculaBt);
        this.add(cursoLb);
        this.add(dniLb);
        this.add(nombreLb);
        this.add(fechaNacimientoLb);
        this.add(cursoTf);
        this.add(dniTf);
        this.add(nombreTf);
        this.add(fechaNacimientoTf);
        this.add(nombreRb);
        this.add(cursoRb);
        this.add(panelAsignaturaPn);
        this.add(guardarBt);
        this.add(altaBt);
        this.add(bajaBt);
        this.add(editarBt);
        
    
        cargaAlumno();
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==izqBt&&alumno>0){
            //System.out.println(alumno);
            this.alumno--;
            cargaAlumno();
        }
        
        if(e.getSource()==drBt&&alumno<alumnos.size()-1){
            //System.out.println(alumno);
            this.alumno++;
            dniTf.setText(" ");
            cargaAlumno();
        }
        
        if(e.getSource()==nombreRb){
            alumnos.sort(null);
            alumno=0;
            cargaAlumno();
        }
        
        if(e.getSource()==cursoRb){
            cursoCb.setSelectedIndex(0);
            alumnos.sort(new ComparaCursoNombre());
            alumno=0;
            cargaAlumno();
        }
        
        

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void cargaAlumno(){
        cursoTf.setText(String.valueOf(alumnos.get(alumno).getCurso()));
        dniTf.setText(String.valueOf(alumnos.get(alumno).getDni()));
        nombreTf.setText(String.valueOf(alumnos.get(alumno).getNombre()));
        fechaNacimientoTf.setText(String.valueOf(alumnos.get(alumno).getFechaNacimiento()));
    }
    
    private void izqActiva(boolean opcion){
        izqBt.setEnabled(opcion);
        drBt.setEnabled(opcion);
        matriculaBt.setEnabled(opcion);
        cursoLb.setEnabled(opcion);
        dniLb.setEnabled(opcion);
        nombreLb.setEnabled(opcion);
        fechaNacimientoLb.setEnabled(opcion);
        cursoTf.setEnabled(opcion);
        dniTf.setEnabled(opcion);
        nombreTf.setEnabled(opcion);
        fechaNacimientoTf.setEnabled(opcion);
        nombreRb.setEnabled(opcion);
        cursoRb.setEnabled(opcion);
        izqBt.setEnabled(opcion);
        izqBt.setEnabled(opcion);
        izqBt.setEnabled(opcion);
        altaBt.setEnabled(opcion);
        bajaBt.setEnabled(opcion);
        editarBt.setEnabled(opcion);
        guardarBt.setEnabled(!opcion);
        panelAsignaturaPn.setEnabled(!opcion);
    }
    
    private void seleccionaCurso(String curso){
        switch (curso) {
            case "Todos":
                alumnos.clear();
                for(Alumno alu : cole.getAlumnos()){
                    alumnos.add(alu);
                    
                }
                System.out.println("Copio alumnos desde cole "+cole.getAlumnos().toString());
                System.out.println("desde seleccionacurso"+alumnos.toString());
                break;
            case "1":
                alumnos.clear();
                for(Alumno alu : cole.getAlumnos()){
                        System.out.println(alu.toString());
                    if(alu.getCurso()==1){
                        alumnos.add(alu); 
                    }
                    
                }  
                System.out.println("desde seleccionacurso"+alumnos.toString());
                break;
            case "2":
                alumnos.clear();
                for(Alumno alu : cole.getAlumnos()){
                    if(alu.getCurso()==2){
                        alumnos.add(alu);
                    } 
                    
                }
                System.out.println("desde seleccionacurso"+alumnos.toString());
                break;
            
            case "3":
                alumnos.clear();
                for(Alumno alu : cole.getAlumnos()){
                    if(alu.getCurso()==3){
                        alumnos.add(alu);
                    } 
                    
                }  
                System.out.println("desde seleccionacurso"+alumnos.toString());
                break;
            
                
            default:
                alumnos.clear();
                alumnos=cole.getAlumnos();
                System.out.println("desde seleccionacurso"+alumnos.toString());
                break;
        }
    
    }
    class ComparaCursoNombre implements Comparator<Alumno>{

        @Override
        public int compare(Alumno o1, Alumno o2) {
            int numero = 0;
            
            if (o1.getCurso() > o2.getCurso()) {
                numero = 1;
            } else {
                if (o1.getCurso() < o2.getCurso()) {
                    numero = -1;
                } else {
                    /*if (o1.getCurso() == o2.getCurso()) {*/
                        numero = o1.getNombre().compareToIgnoreCase(o2.getNombre());
                    //}
                }
            }
            
            return numero;
            
        }
        
        
        
        
    }
    
}