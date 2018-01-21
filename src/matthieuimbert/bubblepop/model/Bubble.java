package matthieuimbert.bubblepop.model;

public class Bubble {
	int numero;
	String couleur;
	
	public Bubble(int numero, String couleur) {
		this.numero = numero;
		this.couleur = couleur;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	@Override
	public String toString() {
		return "Numero : " + this.numero + " Couleur : " + this.couleur;
	}
	
	
	
	
}
