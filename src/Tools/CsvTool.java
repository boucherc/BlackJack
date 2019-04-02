package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class CsvTool {
	private HashMap<String, String> parties;
	public static final String PATH = System.getProperty("user.dir") + "\\historique.csv";

	public CsvTool() throws IOException {
		if (!new File(PATH).exists())
			new FileWriter(PATH).close();
		parties = new HashMap<String, String>();
		FileReader dr = null;
		try {
			dr = new FileReader(PATH);
		} catch (FileNotFoundException e) {
			System.out.println("Erreur d'ouverture de fichier CSV.\n");
		}
		BufferedReader br = new BufferedReader(dr);

		for (String line = br.readLine(); line != null; line = br.readLine()) {
			parties.put(line.split("\t")[0], line);
		}
		dr.close();
	}

	public String getHistoriqueByUser(String user) {
		return parties.get(user);
	}

	public void UpdateProfile(String user, int credit, int lastMise, boolean win) throws IOException {
		if (parties.containsKey(user))
			parties.remove(user);
		new File(PATH).delete();
		OutputStreamWriter f = null;
		try {
			f = new OutputStreamWriter(new FileOutputStream(new File(PATH)));
		} catch (FileNotFoundException e) {
			System.out.println("ERREUR : Ecriture dans le l'historique.");
		}
		for (String s : parties.values()) {
			f.write(s + '\n');
		}
		f.flush();
		String update = user + '\t' + credit + '\t' + lastMise + '\t' + win + "\t\n";
		f.write(update);
		f.flush();
		f.close();
	}

}
