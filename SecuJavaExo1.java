import java.util.Properties;
import java.io.*;

public class SecuJavaExo1 {

    private static void afficherPropriete(String nomPropriete) {
		System.out.println("Propriete " + nomPropriete + "=" + System.getProperty(nomPropriete));
    }
    
    public static void main(String[] args) throws Exception {
		afficherPropriete("java.version");
		afficherPropriete("user.name");
		afficherPropriete("user.home");
		// Affiche toutes les proprietes
		System.out.println("\nToutes les proprietes :");
		System.getProperties().list(System.out);
	
		// Affichage du contenu du fichier
		String fichier;
		int nblignes = 12;

		String ligne;
		if (args.length>0)
			fichier = args[0];
		else
			fichier = "tahiti.txt";
		System.out.println("\n"+nblignes+" premieres lignes de \"" +fichier+ "\":");
		try {
			FileReader fr = new FileReader(fichier);
			BufferedReader br = new BufferedReader(fr);
			try {
				for (int i = 0; i < nblignes; i++) {
					ligne = br.readLine();
					if (ligne == null) break;
					System.out.println("> "+ligne);
				}
				System.out.println("> ...");
				}
			catch (IOException e) {
				System.err.println("Erreur entree-sortie");
			}
			finally {
				br.close();
			}
		}
		catch(FileNotFoundException e) {
			System.err.println(fichier + " pas trouve");
			return;
		}
    }
}
