package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvTool {
	private List<String> parties;
	private final String PATH = System.getProperty("user.dir") + "\\historique.csv";

	public CsvTool() throws IOException {
		if (!new File(PATH).exists())
			new FileWriter(PATH).close();
		parties = new ArrayList<String>();
		FileReader dr = null;
		try {
			dr = new FileReader(PATH);
		} catch (FileNotFoundException e) {
			System.out.println("Erreur d'ouverture de fichier CSV.\n");
		}
		BufferedReader br = new BufferedReader(dr);

		for (String line = br.readLine(); line != null; line = br.readLine()) {
			parties.add(line);
		}
	}

}
