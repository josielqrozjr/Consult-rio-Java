package application;

import java.io.IOException;
import java.util.*;

import entities.*;

public class InterfaceUser{
    public static void main(String[] args) throws IOException{
        
        String nome_do_arquivo_consultas = "consultas.bin";
        String nome_do_arquivo_pacientes = "pacientes.bin";
        String nome_do_arquivo_medicos = "medicos.bin";

        List<Consulta> consultas = null;
        List<Paciente> pacientes = null;
        List<Medico> medicos = null;

        // Recupera os dados salvos no arquivo binário
        try{
            consultas = Consulta.abrirConsultas(nome_do_arquivo_consultas);
            pacientes = Paciente.abrirPacientes(nome_do_arquivo_pacientes);
            medicos = Medico.abrirMedicos(nome_do_arquivo_medicos);

            System.out.println("Consultas de " + nome_do_arquivo_consultas + " recuperadas com successo!");
            System.out.println("Consultas de " + nome_do_arquivo_pacientes + " recuperadas com successo!");
            System.out.println("Consultas de " + nome_do_arquivo_medicos + " recuperadas com successo!");

            Menu menu = new Menu(consultas, pacientes, medicos);

            Menu.criar_interface();


        } 
        catch (IOException e) {
            System.out.println("Excecao de I/O");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Excecao de classe desconhecida");
            e.printStackTrace();
        }

        /* 
        for(Consulta consulta : consultas){
           System.out.println(Medico.getMedicoNomePorCodigo(medicos, consulta));
        }
        */

        // Fomulário...
 


    }           
}