package fr.ecole3il.rodez2023.perlin.test;

import fr.ecole3il.rodez2023.perlin.math.BruitAleatoire;


public class Test {

    public static void main(String[] args) {
        try {
            testBruitAleatoire();
        } catch (TestFailed e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testBruitAleatoire() throws TestFailed {
        long TEST_GRAINE = 0L;
        double TEST_RESOLUTION = 1.0;
        double TEST_X = 42;
        double TEST_Y = 13;
        double[] testDoubleAleatoire = new double[4];

        BruitAleatoire testBruitAleatoire = new BruitAleatoire(TEST_GRAINE, TEST_RESOLUTION);

        // Test des attributs de la classe
        if (testBruitAleatoire.getGraine() != TEST_GRAINE) {
            //TODO: Rédiger message d'erreur du test
            throw new TestFailed("");
        }

        if (testBruitAleatoire.getResolution() != TEST_RESOLUTION) {
            //TODO: Rédiger message d'erreur du test
            throw new TestFailed("");
        }

        // Test de la méthode bruit2D
        testDoubleAleatoire[0] = testBruitAleatoire.bruit2D(TEST_X, TEST_Y);
        testDoubleAleatoire[1] = testBruitAleatoire.bruit2D(TEST_X, TEST_Y);
        testDoubleAleatoire[2] = testBruitAleatoire.bruit2D(TEST_X, TEST_Y);
        testDoubleAleatoire[3] = testBruitAleatoire.bruit2D(TEST_X, TEST_Y);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(testDoubleAleatoire[i]);
                System.out.println(testDoubleAleatoire[j]);
                if (i == j) {
                    continue;
                }
                if (testDoubleAleatoire[i] == testDoubleAleatoire[j]) {
                    //TODO: Rédiger message d'erreur du test
                    throw new TestFailed("");
                }
            }
        }


    }
}
