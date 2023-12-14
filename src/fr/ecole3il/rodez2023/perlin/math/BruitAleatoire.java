package fr.ecole3il.rodez2023.perlin.math;

import java.util.Random;

/**
 * La classe RandomNoise étend la classe Noise2D et génère du bruit aléatoire en deux dimensions.
 * Elle utilise la classe Random de Java pour générer des valeurs aléatoires.
 */
public class BruitAleatoire extends Bruit2D {

    /**
     * Constructeur de la classe BruitAleatoire.
     * @param graine La graine utilisée pour initialiser le générateur de bruit.
     * @param resolution La resolution utilisée pour paramétrer la granularité de l'image générée.
     */
    public BruitAleatoire(long graine, double resolution) {
        super(graine, resolution);
    }

    /**
     * Génère un double aléatoire.
     * @param x Coordonnée x pour laquelle obtenir le bruit.
     * @param y Coordonnée y pour laquelle obtenir le bruit.
     * @return un double aléatoire.
     */
    @Override
    public double bruit2D(double x, double y) {
        Random r = new Random(this.getGraine());
        return r.nextDouble();
    }
}