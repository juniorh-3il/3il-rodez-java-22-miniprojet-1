---
titre: Java
sous-titre: Miniprojet 1 - Génération de terrain aléatoire en utilisant le bruit de Perlin
auteur: Philippe \textsc{Roussille}
date: 3iL 1A 2023
---

**Date de rendu du projet : 22/02/2024**

**Formulaire de rendu :** [ici](https://framaforms.org/rendu-du-miniprojet-1-1702438510)

# Bienvenue dans votre projet

## Tests unitaires, `git` et Javadoc

- Il vous est demandé de bien *commenter* et de bien réaliser la documentation (au format Javadoc) de vos fichiers.
- Pensez à commiter & pusher votre travail ***de façon régulière***.
- Les messages de vos commits sont essentiels (pas de "lol ça marche", "ENFIN!!!!", "toto", ou autres). Soyez clairs (vous pouvez vous inspirer de [cette norme](https://buzut.net/cours/versioning-avec-git/bien-nommer-ses-commits)).
- N'oubliez pas de réaliser des tests unitaires afin de valider votre code au fur et à mesure de votre production. Essayez d'en réaliser au moins un par partie demandée.

## Structure du rendu

1. Il vous est demandé un rendu *propre*, c'est à dire qui **compile** à minima.
2. Si votre projet **ne compile pas**, la note est automatiquement plafonnée à 10.
3. Votre projet comportera à la racine un fichier `AUTHORS` qui comporte votre nom et votre adresse 3il (du genre `Philippe ROUSSILLE <philr@3il.fr>`).
4. Ce projet est à faire ***individuellement***. Toutefois, je n'ai rien contre l'entraide entre vous (tant que cela ne se résume pas à "pomper le code du voisin"). Si vous avez été aidé par quelqu'un, réalisez un fichier `HELPERS` à la racine de votre projet où vous indiquez celui qui vous a aidé, et de quelle façon (cela constituera un bonus pour cette personne lors de la notation).
5. Enfin, vous réaliserez un fichier `README.md` à la racine du projet dans lequel vous indiquerez, sous réserve de pertinence :
    - ce que vous avez réalisé du projet ;
    - les réponses aux diverses questions du sujet s'il y en a ;
    - un retour personnel sur les points qui vous ont paru difficiles ;
    - tout autre commentaire que vous jugerez utile...


# Principe du bruit de Perlin

## Origine du bruit de Perlin

Le bruit de Perlin a été développé par Ken Perlin, un informaticien et chercheur en informatique graphique. Il a créé cet algorithme en 1983, principalement dans le but de générer des images et des animations qui semblent plus naturelles et moins artificielles. L'idée était de simuler des variations aléatoires mais cohérentes dans les textures et les formes, en particulier pour la modélisation de surfaces complexes dans les graphismes par ordinateur.

## Utilité du bruit de Perlin

- Génération de textures : Le bruit de Perlin est largement utilisé pour générer des textures réalistes dans les jeux vidéo, les films d'animation et d'autres applications graphiques. Il peut simuler des surfaces rugueuses, des terrains naturels, et d'autres détails visuels.
- Animation réaliste : L'algorithme de bruit de Perlin est également appliqué pour créer des mouvements et des animations réalistes. Par exemple, il peut être utilisé pour animer le mouvement de l'eau, des nuages, ou d'autres phénomènes naturels.
- Génération de terrains : Dans le domaine de la modélisation de terrains, le bruit de Perlin est très utile pour créer des paysages variés et naturels, en simulant des altitudes aléatoires et des détails géographiques (ce qui est ce qui nous intéresse ici).
- Anti-aliasing : Il est parfois utilisé dans les techniques d'anti-aliasing pour adoucir les contours et rendre les images plus esthétiques.
- Effets visuels : Le bruit de Perlin est également utilisé pour ajouter des détails visuels et du réalisme à des scènes graphiques, en simulant des variations subtiles dans les couleurs, les formes, et les éclairages.

## Grandes lignes de l'algorithme

À la base de cet algorithme se trouvent des vecteurs de gradients pseudo-aléatoires. Ces vecteurs sont assignés à des points de grille dans un espace multidimensionnel. Par exemple, dans un espace à deux dimensions (comme une image), chaque point de grille a un vecteur de gradient associé.

L'idée principale de l'algorithme est de prendre un point dans cet espace, de déterminer dans quelles cellules de grille il se trouve, puis de calculer des poids en fonction de la position du point par rapport à ces cellules. Ces poids sont ensuite utilisés pour interpoler les valeurs des gradients auxquels ces points sont associés.

Plus simplement, l'algorithme fonctionne en quatre étapes :

1. Grille de points et de gradients : Création d'une grille de points où chaque point a un vecteur de gradient associé.
2. Détermination des cellules : Pour un point donné, on détermine les cellules de la grille qui l'entourent.
3. Calcul des poids : On calcule des poids pour chaque point de la grille en fonction de la distance et de la direction par rapport au point donné.
4. Interpolation : En utilisant ces poids, on interpole les valeurs des gradients pour obtenir une valeur de bruit en ce point.

Ce processus est répété pour différents points de l'espace, créant ainsi un motif de bruit cohérent et réaliste.

La principale force du bruit de Perlin réside dans sa capacité à produire des motifs aléatoires mais réguliers, ce qui le rend idéal pour la création de textures naturelles et réalistes, comme des terrains, des nuages, ou même des textures de bois.

## En vidéo

[Un short !](https://www.youtube.com/shorts/NGziLrKvgGU)

## Version en pseudocode

Voici une version simplifiée du pseudocode pour l'algorithme du bruit de Perlin :

```
function bruit_de_perlin(x, y):
    // 1. Déterminer les cellules de la grille pour x, y
    x0 = entier_inferieur(x)
    x1 = x0 + 1
    y0 = entier_inferieur(y)
    y1 = y0 + 1
    
    // 2. Vecteurs de gradient pour les quatre coins de la cellule
    gradient00 = vecteur_de_gradient(x0, y0)
    gradient01 = vecteur_de_gradient(x0, y1)
    gradient10 = vecteur_de_gradient(x1, y0)
    gradient11 = vecteur_de_gradient(x1, y1)
    
    // 3. Vecteurs entre les points et les coins de la cellule
    distance_x = x - x0
    distance_y = y - y0
    
    // 4. Calcul des produits scalaires entre les gradients et les vecteurs de distance
    dot00 = dot_product(gradient00, (distance_x, distance_y))
    dot01 = dot_product(gradient01, (distance_x, distance_y - 1))
    dot10 = dot_product(gradient10, (distance_x - 1, distance_y))
    dot11 = dot_product(gradient11, (distance_x - 1, distance_y - 1))
    
    // 5. Interpolation bilinéaire des valeurs de bruit
    poids_x = fonction_d'interpolation(distance_x)
    poids_y = fonction_d'interpolation(distance_y)
    
    interpolation_haut = interpolation_lineaire(dot00, dot10, poids_x)
    interpolation_bas = interpolation_lineaire(dot01, dot11, poids_x)
    valeur_finale = interpolation_lineaire(interpolation_haut, interpolation_bas, poids_y)
    
    return valeur_finale
```

## Table de permutation

La table de permutation est un élément clé de l'algorithme du bruit de Perlin. Elle est utilisée pour créer un ensemble de valeurs pseudo-aléatoires et pour faciliter la cohérence des motifs générés.

Cette table est essentiellement un tableau de nombres, généralement de 0 à 255, où chaque nombre apparaît exactement une fois. Cette séquence est souvent créée de manière pseudo-aléatoire et reste fixe dans l'algorithme. La table de permutation est utilisée pour mélanger ou permuter les vecteurs de gradients dans l'espace.

Dans l'algorithme de Perlin, ces valeurs sont utilisées pour indexer les vecteurs de gradient. Par exemple, pour un espace en deux dimensions, chaque point de la grille a un vecteur de gradient associé à un indice unique basé sur cette table de permutation.

Cette table permet d'assurer que pour tout point donné dans l'espace, les vecteurs de gradients associés restent cohérents. Ils conservent une direction spécifique et sont assignés de manière cohérente aux points de la grille. Ainsi, le bruit généré est continu et semble naturel, avec des transitions fluides entre les différentes valeurs de bruit.

# Objectif du Projet : Génération de Bruit de Perlin et Création de Terrains

Dans ce mini-projet, votre objectif principal est de travailler sur la génération de bruit de Perlin et de créer un visualiseur pour afficher ce bruit sous forme d'image à l'aide de la classe fournie `VisualiseurBruitPerlin`. En plus de cela, vous allez étendre cette fonctionnalité pour générer des terrains, à la manière de jeux tels que Minecraft ou Terraria.

## Partie 1: Affichage du Bruit de Perlin

Utilisez la classe `VisualiseurBruitPerlin` fournie pour afficher le bruit de Perlin généré. Vous devrez implémenter une méthode qui génère le bruit de Perlin pour différentes dimensions, octaves et amplitudes, puis utilisez cette méthode pour afficher le bruit résultant en tant qu'image à l'aide de la classe `VisualiseurBruitPerlin`. Assurez-vous de permettre à l'utilisateur de contrôler les paramètres pour observer les variations du bruit de Perlin.

## Partie 2: Génération de Terrain

En utilisant les concepts de bruit de Perlin, créez une triple carte aléatoire représentant différents aspects d'un terrain : hydrométrie, altitude et température. Vous devrez expérimenter avec différentes combinaisons de fréquences et d'amplitudes pour obtenir des résultats intéressants.

Votre objectif est de permettre à l'utilisateur d'explorer visuellement ces cartes de terrain, en visualisant les variations d'altitude, d'humidité et de température. Ces cartes pourraient être des images en niveaux de gris ou en couleurs, chaque aspect du terrain étant représenté par une couleur différente.

N'hésitez pas à expérimenter avec des valeurs différentes et à explorer comment les différentes caractéristiques du bruit de Perlin peuvent influencer la génération de ces cartes de terrain.

# C'est parti !

## Travail déjà fait

Bonne nouvelle : les classes du paquet `gui`, qui fournissent l'interface graphique, sont (plus ou moins) déjà faites ! Vous n'aurez que peu à modifier sur ces fichiers si vous le souhaitez.

## Commençons simplement

### Les maths

#### Faites du bruit

On peut commencer directement par le code de la classe `Bruit2D`. C'est censé être une classe abstraite de définition du bruit, mais on dirait que celui qui l'a développé s'est arrêté en plein milieu...

**Question :** pourquoi l'attribut graine est-il en final ?

Corrigez la classe en ajoutant l'attribut resolution qui manque, ainsi que l'accesseur et le mutateur.

#### Faites du bruit, mais aléatoire

Réalisez maintenant la classe `BruitAleatoire`. Cette classe étend la classe précédente, mais ne fait rien de bien folichon. Elle se contente d'utiliser la classe `Random` de base de Java pour produire un nombre aléatoire (qui ne dépend même pas de x et de y - c'est un comble !).

Regardez bien la documentation de `Random`. L'implémentation de la classe est triviale.

#### Mr Perlin et son bruit

Bien. On progresse ! Quelqu'un vous a apparemment aidé, et a codé la classe `BruitPerlin2D`. Enfin, codé... C'est vite dit. Les commentaires sont mal faits, il y a un `resolution` qui pendouille dans le code... et la classe daignerait à être un peu plus propre.

Faites les corrections nécessaires.

On remarquera que le constructeur est censé utiliser `graine`... mais apparemment, ce n'est pas le cas. Comme indice, on pourra utiliser la fonction `Utils.melanger`. Enfin. On pourrait, si elle n'avait pas un GROS souci de programmation...

#### En bonus : rendre la classe plus jolie et fonctionnelle

Si vous regardez le code, outre le manque de commentaires, vous pouvez remarquer que beaucoup de lignes se ressemblent.

Si vous le souhaitez, vous pouvez étendre la classe `Bruit2D` en `SuperBruitPerlin2D`, et reprendre la base de `BruitPerlin2D`. Le code est fonctionnel, donc vous pouvez plus ou moins reprendre son fonctionnement.

Je vous conseille de créer trois méthodes (des points bonus seront attribués si vous le faites) :

- `double produitScalaire(double[] vecteur, double x, double y)` : Cette méthode multiplie la composante x du vecteur par la distance x, et la composante y par la distance y, puis renvoie la somme des deux produits.
- `double lissage(double t)` : Cette méthode applique une fonction de lissage à une valeur donnée `t`. Cette fonction est spécifique à l'algorithme du bruit de Perlin. Elle prend en entrée un paramètre `t` et effectue un calcul pour lisser cette valeur. Le calcul de lissage doit produire $6t^5-5t^4$. Attention à la virgule flottante et aux erreurs d'arrondi...
- `double interpolation(double t, double a, double b)` : Cette méthode effectue une interpolation **linéaire** entre deux valeurs `a` et `b` en utilisant un coefficient `t` comme facteur de mélange. Cela permet de générer des valeurs intermédiaires entre `a` et `b` en fonction de `t`.

#### Interlude : fonction `min`

Pensez à corriger également la fonction `min` dans `Utils`.

#### Tester si tout marche bien

Le fichier `VisualiseurBruitPerlin` vous attend.

## Un peu de modélisation objet dans votre Perlin ?

Soufflez un peu. Vous avez fini la partie la plus mathématique du sujet. Passons à la partie modélisation, que vous adorez déjà !

On cherche à réaliser un générateur de carte 2D avec différents types de terrains (une grille de cases, à l'ancienne).

Voici les terrains que l'on souhaite pouvoir manipuler :

- des collines
- des déserts
- des forêts de conifères
- des forêts de feuillus
- des marais
- des montagnes
- des océans
- des plaines
- des toundra

On pourrait, par exemple, construire une classe pour chaque, qui hériterait de `Terrain`, comme classe mère.

**Question :** Réaliser l'architecture que prendrait la forme d'une telle modélisation sous forme d'un diagramme UML. Quelle serait la différence d'attributs et de méthodes entre ces classes ? Expliquer, en s'appuyant sur un exercice vu en cours, pourquoi c'est une mauvaise idée. Quelle est la solution qu'il faudrait préférer (et que l'on va préférer) ?

### Modélisons le (type de) terrain

Si vous remarquez le dossier `data`, il contient les images des différents types de terrain que l'on va gérer. Voici la description exhaustive :

- Collines : hills.png 
- Désert : desert.png 
- Forêt de conifères : coniferous_forest.png 
- Forêt de feuillus : deciduous_forest.png 
- Marais : marsh.png 
- Montagne : mountain.png 
- Océan : ocean.png 
- Plaine : plain.png 
- Toundra : tundra.png 

Dans le paquet `fr.ecole3il.rodez2023.perlin.terrain.elements`, implémentez `TypeTerrain` selon votre réponse à la question précédente.

On demandera un accesseur particulier, `getImage()`, qui doit renvoyer un élément de type `java.awt.image.BufferedImage`. Utilisez la fonction `Utils.chargerTuile` pour cela. Minimisez le nombre d'attributs (en théorie, deux suffisent).

N'oubliez pas la méthode `toString()`, qui convertit votre `TypeTerrain` en chaîne de caractère (son nom en français, par exemple ?).

### Au terrain, maintenant

La plupart des jeux de type bac à sable utilisent trois valeurs pour générer ce qui est nécessaire à la détermination d'un élément : son altitude, son hydrométrie, et sa température. Ça tombe bien, on va suivre plus ou moins (en simplifiant) ce principe.

Créez la classe `Terrain` qui comportera trois accesseurs (un pour l'hydrométrie, un pour la température, et un pour l'altitude) - `getHydrometrie`, `getTemperature` et `getAltitude`. On veut pouvoir rentrer des informations décimales suffisamment précises (au moins en double précision).

**Question :** quel type utilisez-vous ?

Pour des raisons de simplification, on **ne veut pas** que les valeurs de ces caractéristiques soient faites "à la louche". On s'assurera donc de les limiter : l'hydrométrie et la température doivent être comprises entre 0 et 1, l'altitude entre -1 et 1. On lèvera l'exception `MauvaiseValeurException` si une condition n'est pas respectée. L'exception en question héritera d'une exception **qui détermine qu'un argument est illégal**.

Ajoutez ensuite une méthode qui accepte un visiteur, la méthode `TypeTerrain getTypeTerrain(DetermineurTerrain dt)`.

Cette méthode vous semble étrange ? C'est l'implémentation d'un patron qu'on a déjà vu, le patron Visiteur (mais sans le nommer). Pour faire simple, c'est un modèle comportemental qui permet de séparer un algorithme de la structure d'un objet sur lequel il opère. Il est utilisé lorsque vous avez une structure complexe d'objets et que vous souhaitez appliquer différentes opérations à ces objets, mais que vous ne voulez pas modifier directement leurs classes.

Ce modèle est composé de trois éléments principaux :

- Visiteur : Il définit une interface comportant une méthode pour chaque type d'objet de la structure. Chaque méthode visite un type spécifique d'objet.
- Éléments : Ce sont les différents types d'objets de la structure. Ils possèdent une méthode acceptant un visiteur en paramètre. Cette méthode invoque la méthode correspondante du visiteur.
- Structure : C'est la collection d'objets que le visiteur peut parcourir et traiter.

L'avantage clé du patron Visiteur est sa capacité à ajouter de nouvelles opérations sans modifier les classes des éléments existants. C'est une approche idéale lorsque la structure d'objets est fixe, mais que de nouvelles opérations sont susceptibles d'être ajoutées fréquemment.

En utilisant le patron Visiteur, vous pouvez séparer le code lié à la manipulation des objets de leur structure, ce qui rend le code plus modulaire et extensible. Cependant, cela peut rendre le code plus complexe, surtout lorsque la structure d'objets est sujette à des changements fréquents.

Dans notre cas, on veut pouvoir déterminer le type de terrain en fonction des trois caractéristiques, mais on veut éviter de modifier la classe à chaque fois qu'on change de détermination - on a donc un Visiteur qui s'en occupe.

**Question :** pourquoi sortir, selon vous, ce bout d'algorithme de la classe ?

### JE SUIS LA CARTE

Pour qu'on puisse enregistrer une carte, on considère qu'on peut représenter la carte comme une longue chaîne de caractères, où les métadonnées sont situées au début, suivies des données de chaque terrain, avec les valeurs d'altitude, d'hydrométrie et de température séparées par des espaces ou des retours à la ligne.

Vous allez maintenant devoir coder la classe `Carte`.
Elle se comporte de quatre éléments : son nom, sa largeur, sa hauteur, et un tableau (à deux dimensions) de `Terrain`.

Vous réaliserez les accesseurs pour le nom de la carte, la largeur et la hauteur.

Vous réaliserez un accesseur (`getTerrain(int x, int y)`) pour le terrain situé à `[x][y]`, en veillant bien à respecter la convention du fichier `GenerateurCarte`. Si on demande une case invalide, il faut lever l'exception `TerrainInexistant` à créer (qui hérite d'une bonne exception **pour signifier qu'on est hors des zones d'un tableau**).

Enfin, vous réaliserez le constructeur `Carte(String nom, int largeur, int hauteur, GenerateurCarte generateurCarte)`.

*Conseil :* utilisez `generateurCarte` pour créer `terrains`.

#### Petite difficulté : charger la carte

Vous réaliserez un **deuxième** constructeur, `Carte(String donneesCarte)`, qui charge la carte depuis une chaîne de caractères.

On suppose que les données de la carte sont organisées de manière spécifique dans la chaîne :

1. Métadonnées de la carte :

La première ligne de la chaîne de caractères est le nom de la carte.
La deuxième ligne contient la largeur de la carte.
La troisième ligne contient la hauteur de la carte.

2. Éléments de terrain :

Les données de terrain sont ensuite lues. Chaque élément est composé de trois valeurs, dans cet ordre : altitude, hydrométrie, température.

*Conseil :* Utilisez un `Scanner` sur `donneesCarte`, ça va tout seul. N'oubliez pas de le fermer !

## Génération de la carte

Pour générer une carte, on doit pouvoir utiliser un générateur. Ici, l'implémentation est donnée par la classe `GenerateurCarte`. Vous n'avez pas besoin d'éditer ce fichier (mais c'est sympa de comprendre ce qu'il fait).

#### Au pifomètre

Réalisez une implémentation concrète, `GenerateurAleatoire`, de `GenerateurCarte`. Ce générateur initialise des valeurs au hasard pour les trois caractéristiques de terrain.

#### En triple Perlin

Réalisez une implémentation concrète, `GenerateurPerlin`, de `GenerateurCarte`. Ce générateur utilise trois "couches" de générateur de bruit de Perlin pour les éléments du terrain.

Pour calculer la valeur correcte, normalisez la "case" sur laquelle vous êtes. Ainsi, si vous êtes en `(i, j)` et que votre carte est de taille `(largeur, hauteur)`, cherchez en `i/largeur` et `j/hauteur`.

Pour les graines, on demande à ce que la graine de l'hydrométrie soit celle du générateur, celle de la température soit le double, et celle de l'altitude soit le double de la température. Vous pouvez laisser la résolution à 1.

## Le dernier rempart : l'affichage

Une des raisons d'utiliser des classes et des objets, c'est de modulariser au maximum l'affichage de la carte par rapport aux données qui sont stockées. Pour cela, on propose d'utiliser trois nouvelles entités pour simplifier l'affichage, à réaliser dans `fr.ecole3il.rodez2023.perlin.terrain.visualisation`.

- `AltitudeAffichee` : il y a trois "paliers" intéressants d'altitude, `Basse` (inférieure à 0.33), `Moyenne` (entre 0.33 et 0.66) et `Elevee` (supérieure à 0.66). On considère aussi le `FondMarin` qui est négatif.
- `HydrometrieAffichee` : uniquement pour des valeurs positives, avec deux paliers à 0.33 et 0.66, où il fait soit `Sec`, soit `Moyen`, soit `Humide`.
- `TempératureAffichee` : de même, il peut faire `Froid`, `Tempéré` ou `Chaud`.

Pour qu'on puisse utiliser ces jolies valeurs, il ne manque que la classe `VisualiseurTerrain`, qui est une Façade. Pour faire simple, le patron de conception Façade est un modèle structurel qui fournit une interface unifiée pour un ensemble complexe d'interfaces ou de sous-systèmes. Il permet de simplifier l'utilisation de ces systèmes en fournissant une interface de haut niveau plus conviviale.

La classe `VisualisateurTerrain` prend une `Carte` et un `DetermineurTerrain` dans son constructeur, et propose d'avoir la température affichée, l'altitude affichée, l'hydrométrie affichée, et le type de terrain d'une case de la carte. Faites les accesseurs nécessaires.

## C'est fini : le concret

Il ne vous reste plus qu'à implémenter la classe `VisualiseurTerrainEnonce` dans le paquet `fr.ecole3il.rodez2023.perlin.terrain.concrets`, en respectant les contraintes suivantes :

- Plaine : Humidité basse, altitude basse, température basse/moyenne/haute
- Forêt de feuillus : Humidité basse, altitude basse/moyenne, température moyenne/élevée
- Toundra : Humidité basse, altitude basse, température élevée
- Forêt de conifères : Humidité basse/élevée, altitude basse, température moyenne
- Montagne : Humidité basse/moyenne/élevée, altitude élevée, température basse/moyenne/élevée
- Désert : Humidité basse, altitude élevée, température basse
- Collines : Humidité basse/moyenne/élevée, altitude moyenne/élevée, température basse/moyenne/élevée
- Marais : Humidité moyenne/élevée, altitude basse, température basse/moyenne
- Océan : altitude négative

On considère une valeur comme *basse* si elle est entre 0 et 0.25, moyenne si elle est entre 0.25 et 0.7, et haute sinon.

## Vous en voulez encore ?

***SI VOUS AVEZ LE TEMPS ET L'ENVIE :***

- Générateur de bruit de Perlin pour îles : Ajustez les paramètres du générateur de bruit de Perlin pour produire des formes insulaires. Manipulez les octaves, les amplitudes et les fréquences pour créer des masses terrestres entourées d'eau, reproduisant ainsi des contours et des formes d'îles.
- Générateur basé sur des algorithmes spécifiques : Explorez d'autres algorithmes de génération de terrain tels que le "Diamond-Square", le "Worley Noise" ou des méthodes fractales pour obtenir des résultats différents. Ces approches peuvent offrir des variations uniques pour simuler des îles ou d'autres topographies spécifiques.
- Hybridation de générateurs : Combinez plusieurs générateurs pour créer des résultats uniques. Par exemple, utilisez le bruit de Perlin pour générer la forme de base de l'île, puis appliquez un autre générateur pour définir des caractéristiques spécifiques comme des pics, des cratères ou des détails côtoyant le littoral.
- Défiez-vous en optimisant les algorithmes de génération de bruit de Perlin ou de génération de terrain pour des performances améliorées. Explorez des structures de données plus efficaces, des techniques de calcul parallèle ou d'autres méthodes d'optimisation pour réduire le temps d'exécution ou la consommation de mémoire.
- Si vous avez déjà couvert l'ensemble des fonctionnalités de base, étendez davantage votre projet en ajoutant des caractéristiques avancées. Explorez la génération de biomes, simulez des phénomènes naturels comme l'érosion ou créez des cartes personnalisées en fonction de critères spécifiques tels que la densité de végétation ou la répartition des ressources. Cette extension permettra une exploration plus approfondie et une compréhension plus étendue des mécanismes de génération de terrain.
