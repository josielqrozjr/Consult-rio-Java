package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import entities.*;
import readers.*;
import readers.readersCSV.DadosCSVReaderConsulta;
import readers.readersCSV.DadosCSVReaderMedico;
import readers.readersCSV.DadosCSVReaderPaciente;

public class InterfaceUser2{
    public static void main(String[] args) throws IOException{

        List<Consulta> consultas = DadosCSVReaderConsulta.lerConsultasDoCSV();
        // Passo 2: Ler pacientes e associar consultas
        List<Paciente> pacientes = DadosCSVReaderPaciente.lerPacientesDoCSV(consultas);
        // Passo 3: Ler médicos e associar pacientes
        List<Medico> medicos = DadosCSVReaderMedico.lerMedicosDoCSV(pacientes);

        //Consulta consulta_teste = new Consulta('2024-06-08', LocalTime horario, int medico, String cpfPaciente);
        
        String nome = "alcides.bin";

        // Salva os dados lidos no CSV no arquivo binário
        try{
            for (Consulta consulta : consultas) {
                consulta.salvarConsultas(nome);
            }
            System.out.println("Consultas salvas em " + nome + " com successo!");
        } catch (IOException e) {
            System.out.println("Excecao de I/O");
            e.printStackTrace();
        }

        // Recupera os dados salvos no arquivo binário
        try{
            Consulta.abrirConsultas(nome);
            System.out.println("Consultas de " + nome + " recuperadas com successo!");
        } catch (IOException e) {
            System.out.println("Excecao de I/O");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Excecao de classe desconhecida");
            e.printStackTrace();
        }

        // Fomulário...
        
}