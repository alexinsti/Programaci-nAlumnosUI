/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testcole;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Diurno
 */
public class Ventana extends JFrame implements ActionListener{
        
    private JButton izqBt, drBt, matriculaBt, guardarBt;
    private JLabel cursoLb, dniLb, nombreLb, fechaNacimientoLb;
    private JTextField cursoTf, dniTf, nombreTf, fechaNacimientoTf;
    private JRadioButton nombreRb, cursoRb;
    private JPanel panelAsignaturaPn;
    private JCheckBox a1, a2;
    private Colegio cole;
    public int alumno = 0;
    public ArrayList<Alumno> alumnos = new ArrayList();
    public int cursoSeleccionado;
    
    public Ventana(Colegio cole){
        this.cole=cole;
        cole.ordenaPorNombre();
        
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
                Ventana.this.setSize(300, 400);
                izqActiva(true);
            }
            });
        
        

        //LAYOUT
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(300,400);
        
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
                Ventana.this.setSize(600, 400);
                izqActiva(false);

                for(int i=0;i<Colegio.getCURSO_ASIGNATURAS().length;i++){
                        boolean checked=false;
                        //Como el array de COlegio es de cadenas, y no de asignaturas no puedo usar un contains al no ser del mismo tipo
                        for(Asignatura a : cole.getAlumnos().get(alumno).getAsignaturas()){
                            if(a.getNombreAsig().equalsIgnoreCase(Colegio.getCURSO_ASIGNATURAS()[cole.getAlumnos().get(alumno).getCurso()-1][i])){
                                checked=true;
                                System.out.println(a.toString()+"esta activada");
                                break;
                            }else{
                            checked=false;
                                System.out.println(a.toString()+"NO esta activada");
                            }
                        }
                        System.out.println("Este alumno cursa: "+cole.getAlumnos().get(alumno).getAsignaturas().toString());
                        a1 = new JCheckBox(Colegio.getCURSO_ASIGNATURAS()[cole.getAlumnos().get(alumno).getCurso()-1][i], checked);
                        panelAsignaturaPn.add(a1);
                        
                        
                        
                        
                    
                    
                    System.out.println(Colegio.getCURSO_ASIGNATURAS()[cole.getAlumnos().get(alumno).getCurso()-1][i]);
                
                }
                
                
            }
       });
       
       guardarBt=new JButton("Guardar");
        guardarBt.setBounds(330, 300, 120, 30);
        guardarBt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                System.out.println("ACTIVO GUARDAR");
                panelAsignaturaPn.setVisible(false);
                Ventana.this.setSize(300, 400);
                izqActiva(true);
                Asignatura asig;
                int contadorAsig=0;
                System.out.println("de las asignaturas: ");
                //Si borro uno los indices de todeos cambian así que se queda alguno sin borar a menos que el indice empiece por abajo
                for(int i=0; i<=panelAsignaturaPn.getComponents().length-1;i++){
                    System.out.println(panelAsignaturaPn.getComponent(i).toString());
                }
                System.out.println("Limpio Asignaturas del alumno");
                cole.getAlumnos().get(alumno).getAsignaturas().clear();
                System.out.println("Este alumno cursa: "+cole.getAlumnos().get(alumno).getAsignaturas().toString());
                for(int i=0; i<=panelAsignaturaPn.getComponents().length+1;i++){
                    JCheckBox CbAuxiliar = new JCheckBox();
                    if(((JCheckBox)panelAsignaturaPn.getComponent(0)).isSelected()){
                        System.out.println("Creo: "+panelAsignaturaPn.getComponent(0).toString());
                        asig=new Asignatura(Colegio.getCURSO_ASIGNATURAS()[cole.getAlumnos().get(alumno).getCurso()-1][contadorAsig]);
                        cole.getAlumnos().get(alumno).getAsignaturas().add(asig);
                   
                    }
                   
                   
                   
                   System.out.println("Borro: "+panelAsignaturaPn.getComponent(0).toString());
                   panelAsignaturaPn.remove(panelAsignaturaPn.getComponent(0));
                   contadorAsig++;
                   
                   System.out.println("Este alumno cursa: "+cole.getAlumnos().get(alumno).getAsignaturas().toString());
                
                }
                /*
                for(int i=0; i<=panelAsignaturaPn.getComponents().length+1;i++){
                   System.out.println("Borro: "+panelAsignaturaPn.getComponent(0).toString());
                   panelAsignaturaPn.remove(panelAsignaturaPn.getComponent(0));
                    
                
                }*/
                
                
                alumnos.clear();
                alumnos=cole.getAlumnos();
                System.out.println(alumnos.toString());
                
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
        
    
        cargaAlumno();
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==izqBt&&alumno>0){
            //System.out.println(alumno);
            this.alumno--;
            cargaAlumno();
        }
        
        if(e.getSource()==drBt&&alumno<cole.getAlumnos().size()-1){
            //System.out.println(alumno);
            this.alumno++;
            dniTf.setText(" ");
            cargaAlumno();
        }
        
        if(e.getSource()==nombreRb){
            cole.ordenaPorNombre();
            alumno=0;
            cargaAlumno();
        }
        
        if(e.getSource()==cursoRb){
            cole.ordenaPorCursoNombre();
            alumno=0;
            cargaAlumno();
        }
        
        

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void cargaAlumno(){
        cursoTf.setText(String.valueOf(cole.getAlumnos().get(alumno).getCurso()));
        dniTf.setText(String.valueOf(cole.getAlumnos().get(alumno).getDni()));
        nombreTf.setText(String.valueOf(cole.getAlumnos().get(alumno).getNombre()));
        fechaNacimientoTf.setText(String.valueOf(cole.getAlumnos().get(alumno).getFechaNacimiento()));
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
        guardarBt.setEnabled(!opcion);
        panelAsignaturaPn.setEnabled(!opcion);
    }
    
    private void seleccionaCurso(int curso){
        switch (curso) {
            case 0:
                alumnos.clear();
                alumnos=cole.getAlumnos();
                break;
            case 1:
                alumnos.clear();
                for(Alumno alu : cole.getAlumnos()){
                    if(alu.getCurso()==1){
                        alumnos.add(alu); 
                    }
                    
                }   
                break;
            case 2:
                alumnos.clear();
                for(Alumno alu : cole.getAlumnos()){
                    if(alu.getCurso()==2){
                        alumnos.add(alu);
                    } 
                    
                }   
                break;
            
            case 3:
                alumnos.clear();
                for(Alumno alu : cole.getAlumnos()){
                    if(alu.getCurso()==2){
                        alumnos.add(alu);
                    } 
                    
                }   
                break;
            
                
            default:
                alumnos.clear();
                alumnos=cole.getAlumnos();
                break;
        }
    
    }
    
}
