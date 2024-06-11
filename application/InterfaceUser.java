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

            // Instaciando um objeto da classe Menu
            Menu menu = new Menu();

            // Passando como parâmetro para o método as listas lidas dos binários
            menu.criar_interface(consultas, pacientes, medicos);

            // --=-=-=-=-=-=-=-=- TESTE -=-=-=-=-=-=-=-=-=-=
            /* -=-=- Para testar se a leitura e o armazenamento em listas está correto -=-=-
            // Criando um array de Strings para armazenar os nomes dos médicos
            String[] nomesMedicos = new String[medicos.size()];

            // Percorrendo a lista de médicos e preenchendo o array de Strings
            for (int i = 0; i < medicos.size(); i++) {
                nomesMedicos[i] = medicos.get(i).getNome();
            }

            // Imprimindo o array de nomes para verificar
            for (String nome : nomesMedicos) {
                System.out.println(nome);
            }
            */


        } 
        catch (IOException e) {
            System.out.println("Excecao de I/O");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Excecao de classe desconhecida");
            e.printStackTrace();
        }
    }           
}