package application;

import java.io.IOException;
import java.util.*;

import entities.*;
import readers.readersCSV.DadosCSVReaderConsulta;
import readers.readersCSV.DadosCSVReaderMedico;
import readers.readersCSV.DadosCSVReaderPaciente;

public class SalvarBinario{
    public static void main(String[] args){

        List<Consulta> consultas = DadosCSVReaderConsulta.lerConsultasDoCSV();
        // Passo 2: Ler pacientes e associar consultas
        List<Paciente> pacientes = DadosCSVReaderPaciente.lerPacientesDoCSV(consultas);
        // Passo 3: Ler médicos e associar pacientes
        List<Medico> medicos = DadosCSVReaderMedico.lerMedicosDoCSV(pacientes);

        String nome_do_arquivo_consultas = "consultas.bin";
        String nome_do_arquivo_pacientes = "pacientes.bin";
        String nome_do_arquivo_medicos = "medicos.bin";

        // Salva os dados lidos no CSV no arquivo binário
        try {
            Consulta.salvarListaDeConsultas(consultas, nome_do_arquivo_consultas);
            Paciente.salvarListaDePacientes(pacientes, nome_do_arquivo_pacientes);
            Medico.salvarListaDeMedicos(medicos, nome_do_arquivo_medicos);

            System.out.println("Consultas salvas em " + nome_do_arquivo_consultas + " com successo!");
            System.out.println("Pacientes salvos em " + nome_do_arquivo_pacientes + " com successo!");
            System.out.println("Médicos salvos em " + nome_do_arquivo_medicos + " com successo!");


        } catch (IOException e) {
            System.out.println("Excecao de I/O");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

