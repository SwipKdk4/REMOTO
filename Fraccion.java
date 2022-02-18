package Practica4;

import java.util.ArrayList;

public class Fraccion {
	private int numer;
	private int denom;

	public static void main(String[] args) {
		Fraccion primera = new Fraccion();
		System.out.println("Fraccion() = " + primera);
		Fraccion segunda = new Fraccion(1, 4);
		Fraccion tercera = new Fraccion(0.125);
		Fraccion cuarta = segunda.suma(tercera);
		Fraccion quinta = new Fraccion(2.5);
		System.out.println("Fraccion(1,4) = " + segunda);
		System.out.println("Fraccion(0.125) = " + tercera);
		System.out.println("Fraccion(2.5) = " + quinta);
		System.out.println("numer de 1/4 = " + segunda.getnumer());
		System.out.println("denom de 1/4 = " + segunda.getdenom());
		System.out.println("La suma de " + segunda + " y " + tercera + " = " + cuarta);
		System.out.println("La suma de " + cuarta + " y 2 = " + cuarta.suma(2));
		System.out.println(segunda + " es igual que " + segunda + ": " + segunda.equals(segunda));
		System.out.println(segunda + " es igual que 1/2: " + segunda.equals(new Fraccion(1, 2)));
		System.out.println("0.125 es igual que " + tercera + ": " + tercera.equals(0.125));
		System.out.println("\"Hola\" es igual que " + segunda + ": " + segunda.equals("Hola"));

	}


	public Fraccion() {
		this(1, 1);
	}

	public Fraccion(int n, int d) {
		numer = n;
		denom = d;

		Simplificar();
	}

	public Fraccion(double d) {
		this.denom = 1;
		String convert = String.valueOf(d);
		int posPunto = convert.indexOf(".");
		String partDecimal = convert.substring(posPunto);
		int longDecimal = partDecimal.length();

		this.denom = 10 * longDecimal;

		this.numer = (int) (d * this.denom);

		Simplificar();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (obj instanceof Fraccion) {
			return ((Fraccion) obj).numer == this.numer && ((Fraccion) obj).denom == this.denom;
		}

		if ((obj instanceof Number)) {
			Fraccion temp = new Fraccion(((Number) obj).doubleValue());
			return this.equals(temp);
		}

		return false;
	}

	@Override
	public String toString() {
		if (denom == 1)
			return String.format("%d", numer);
		if (numer == 0)
			return "0";
		else
			return String.format("%d/%d", this.numer, this.denom);
	}

	// suma fracciones
	public Fraccion suma(Fraccion n) {
		Fraccion copia = new Fraccion(n.numer, n.denom);
		copia.numer = (this.numer * copia.denom) + (this.denom * copia.numer);
		copia.denom = this.denom * copia.denom;
		return copia;
	}

	// suma fraccion y entero
	public Fraccion suma(int n) {
		int denomSum = 1;
		int numerSum = n;

		numerSum = (this.numer * denomSum) + (this.denom * numerSum);
		denomSum = this.denom * denomSum;
		return new Fraccion(numerSum, denomSum);
	}

	private void Simplificar() {
		if (this.denom < 0) {
			this.numer *= -1;
			this.denom *= -1;
		}

		int menor = Math.min(this.numer, this.denom);
		ArrayList<Integer> multiplos = new ArrayList<Integer>();
		int multiplo = 2;
		while (multiplo <= menor) {
			if (menor % multiplo != 0) {
				multiplo++;
				continue;
			}

			menor /= multiplo;
			multiplos.add(multiplo);
		}

		for (Integer num : multiplos) {
			if (this.numer % num == 0 && this.denom % num == 0) {
				this.numer /= num;
				this.denom /= num;
			}
		}
	}

	//Getters
	public int getnumer() {
		return this.numer;
	}

	public int getdenom() {
		return this.denom;
	}
}
