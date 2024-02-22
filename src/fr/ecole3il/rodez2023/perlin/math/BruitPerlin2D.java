package fr.ecole3il.rodez2023.perlin.math;


/**
 * La classe BruitPerlin2D étends la classe Bruit2D et génère du bruit de Perlin en 2 dimensions.
 * Elle utilise son attribut statique PERMUTATION pour générer des valeurs aléatoires.
 * @author Hugo JUNIOR
 */
public class BruitPerlin2D extends Bruit2D {

	// Vecteurs de gradient pour le bruit de Perlin
	private static final float[][] GRADIENT_2D = {
			{ 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 },
			{ 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
	};

	// Tableau de permutations initial pour le bruit de Perlin commun à toutes les instances de BruitPerlin2D
	private static final int[] PERMUTATION = { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225,
			140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94,
			252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74,
			165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41,
			55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169,
			200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5,
			202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183,
			170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98,
			108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12,
			191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204,
			176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195,
			78, 66, 215, 61, 156, 180 };

	// Tableau de permutations pour le bruit de Perlin
	private final int[] permutation;

	/**
	 * Constructeur de la classe BruitPerlin2D.
	 * @param graine La graine utilisée pour instancier la classe mère de BruitPerlin2D, Bruit2D.
	 * @param resolution La resolution utilisée pour paramétrer la granularité de l'image générée.
	 */
	public BruitPerlin2D(long graine, double resolution) {
		super(graine, resolution);
		this.permutation = PERMUTATION;
	}

	/**
	 * Génère un double pseudo-aléatoire à partir d'un bruit de Perlin.
	 * @param x Coordonnée x pour laquelle obtenir le bruit.
	 * @param y Coordonnée y pour laquelle obtenir le bruit.
	 * @return un double pseudo-aléatoire.
	 */
	@Override
	public double bruit2D(double x, double y) {
		int negX, negY, posX, posY, indexGradientNegXNegY, indexGradientPosXNegY, indexGradientNegXPosY, indexGradientPosXPosY;
		double vecteurNegXNegY, vecteurPosXNegY, vecteurNegXPosY, vecteurPosXPosY, Cx, Cy, Li1, Li2, deltaNegX, deltaPosX, deltaNegY, deltaPosY;

		// Adapter pour la résolution
		x /= this.getResolution();
		y /= this.getResolution();

		// Obtenir les coordonnées de la grille associées à (x, y)
		negX = (int) (x);
		negY = (int) (y);
		posX = negX + 1;
		posY = negY + 1;

		// Calcul des deltas entre le point (x, y) et les 4 sommets de sa cellule
		deltaNegX = x - negX;
		deltaPosX = x - posX;
		deltaNegY = y - negY;
		deltaPosY = y - posY;

		// Récupérer les indices de gradient associés aux coins du quadrilatère
		indexGradientNegXNegY = this.permutation[negX + this.permutation[negY]] % 8;
		indexGradientPosXNegY = this.permutation[posX + this.permutation[negY]] % 8;
		indexGradientNegXPosY = this.permutation[negX + this.permutation[posY]] % 8;
		indexGradientPosXPosY = this.permutation[posX + this.permutation[posY]] % 8;

		// Récupérer les vecteurs de gradient et effectuer des interpolations pondérées
		vecteurNegXNegY = GRADIENT_2D[indexGradientNegXNegY][0] * deltaNegX + GRADIENT_2D[indexGradientNegXNegY][1] * deltaNegY;
		vecteurPosXNegY = GRADIENT_2D[indexGradientPosXNegY][0] * deltaPosX + GRADIENT_2D[indexGradientPosXNegY][1] * deltaNegY;
		vecteurNegXPosY = GRADIENT_2D[indexGradientNegXPosY][0] * deltaNegX + GRADIENT_2D[indexGradientNegXPosY][1] * deltaPosY;
		vecteurPosXPosY = GRADIENT_2D[indexGradientPosXPosY][0] * deltaPosX + GRADIENT_2D[indexGradientPosXPosY][1] * deltaPosY;

		// Interpolations pour lisser les valeurs obtenues
		Cx = 3 * Math.pow(deltaNegX, 2) - 2 * Math.pow(deltaNegX, 3);
		Cy = 3 * Math.pow(deltaNegY, 2) - 2 * Math.pow(deltaNegY, 3);

		Li1 = vecteurNegXNegY + Cx * (vecteurPosXNegY - vecteurNegXNegY);
		Li2 = vecteurNegXPosY + Cx * (vecteurPosXPosY - vecteurNegXPosY);

		return Li2 + Cy * (Li2 - Li1);
	}
}
