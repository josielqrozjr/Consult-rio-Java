package readers.readersCSV;

import java.io.*;
import java.util.*;

import entities.*;

public class DadosCSVReaderMedico {
	public static List<Medico> lerMedicosDoCSV(List<Paciente> pacientes) {
		String caminho_arquivo = "C:\\Users\\joaov_er3oeo2\\OneDrive - Grupo Marista\\Nova pasta\\Desktop\\Workspace\\ProjetoJava\\informacoes\\medicos.csv";

		Map<Integer, Medico> mapaMedicos = new HashMap<>();
			
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho_arquivo), "UTF-8"))) {
			
			String line = br.readLine();
			line = br.readLine();
			while (line != null) {
				
				String[] vect = line.split(",");
				String nome = vect[0];
				Integer codigoMedico = Integer.parseInt(vect[1]);
				
				Medico medico = mapaMedicos.getOrDefault(codigoMedico, new Medico(nome, codigoMedico));
				mapaMedicos.putIfAbsent(codigoMedico, medico);
	
				
				line = br.readLine();
			}
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		// Associar pacientes aos médicos
		for (Paciente paciente : pacientes) {
			for (Consulta consulta : paciente.getConsultas()) {
				Medico medico = mapaMedicos.get(consulta.getMedico());
				if (medico != null) {
					medico.adicionarPaciente(paciente);
				}
			}
		}
	
		return new ArrayList<>(mapaMedicos.values());
    }
}