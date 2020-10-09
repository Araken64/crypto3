import java.io.*;

public class SecuJavaExo3 {

    public static void afficherDonneesBrutes(String filename) throws Exception  {
	System.out.println("\nlecture du fichier \"" +filename+ "\" (donnees brutes):\n---");
		try {
			InputStream is = new FileInputStream(filename);
			try {
				byte[] buffer = new byte[64];
				boolean eof = false;
				do {
					int c = is.read(buffer);
					if (c != -1)
						System.out.print(new String(buffer,0,c));
					else
						eof = true;
				} while (!eof);
				System.out.println("---\n");
			} catch (IOException e) {
				System.err.println("Erreur entree-sortie");
			} finally {
				is.close();
			}
		} catch(FileNotFoundException e) {
			System.err.println(filename + " pas trouve");
			return;
		}
    }

    public static void afficherDonneesDechiffrees(String filename) throws Exception  {
		System.out.println("\nlecture du fichier \"" +filename+ "\" (donnees dechiffrees):\n---");

		java.security.KeyStore store = java.security.KeyStore.getInstance("jks");
		InputStream isKey = new FileInputStream("keystore");
		store.load(isKey, "password".toCharArray());
		java.security.cert.Certificate cer = store.getCertificate("munier");

		javax.crypto.Cipher dec = javax.crypto.Cipher.getInstance("RSA/ECB/PKCS1Padding");
		dec.init(javax.crypto.Cipher.DECRYPT_MODE, cer.getPublicKey());
		try {
			InputStream is = new FileInputStream(filename);
			try {
				byte[] buffer = new byte[64];
				boolean eof = false;
				do {
					int c = is.read(buffer);
					if (c != -1) dec.update(buffer);
					else eof = true;

				} while (!eof);
			} catch (IOException e) {
				System.err.println("Erreur entree-sortie");
			} finally {
				is.close();
			}
		} catch(FileNotFoundException e) {
			System.err.println(filename + " pas trouve");
			return;
		}
		System.out.println(new String(dec.doFinal()));
		System.out.println("---\n");
    }

    public static void main(String[] args) throws Exception {
		// Affichage du contenu du fichier
		String fichier;
		if (args.length>0)
			fichier = args[0];
		else
			fichier = "tahiti.txt";

		afficherDonneesBrutes(fichier);
		afficherDonneesDechiffrees(fichier);
    }
}
