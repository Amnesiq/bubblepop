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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((couleur == null) ? 0 : couleur.hashCode());
		result = prime * result + numero;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bubble other = (Bubble) obj;
		if (couleur == null) {
			if (other.couleur != null)
				return false;
		} else if (!couleur.equals(other.couleur))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}
	
}
