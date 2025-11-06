# RPG Escape Castele (Java Mini Project)

A small **text-based RPG** where a prisoner tries to escape a dungeon, fights monsters, breaks obstacles, buys weapons, and manages an inventory — all via console menus. Great practice for core OOP in Java. 
---


I chose Maven because I had already used it on a previous project and ran into library issues there, so adopting it again helped me avoid dependency problems and also improve my tooling skills. The project is organized into four packages, with App as the entry point. In ascii, the PrintAscii class centralizes how ASCII assets are loaded and printed. Early on I was calling similar methods from different entities, so I standardized this to unify the visuals for teams, weapons, and other elements.

In player, the Team enum provides fixed values and small helpers to list and choose a team. Inventory manages a player’s weapons in isolation,  and Player implements Iplace (so it can live on the map) and IDestructible (so it can take part in fights), while also rendering the avatar and inventory.

In weapon, Weapon is an abstract class with members shared by all weapons, and the subclasses (Axe, Bow, Sword, etc.) only differ by their values, following the project consigne. WeaponStore models buying and selling.

In gameMap, GameMap builds the map, places the player, obstacles, and monsters, renders the current state, moves the player, and resolves combats. To support that, I defined two interfaces: Iplace for placement and rendering, and IDestructible for combat. The entities (Monster, Obstacle, ExitCastele, EmptyCell, and Player) implement these roles according to their function. A typical run is: initialize with fisrtMaph, move with playerMovement, on encounter call fight, show ASCII via PrintAscii, and finish at ExitCastele with the end screens. One important design decision: I introduced Iplace specifically to avoid using instanceof for combat resolution and player location on the map; as discussed in class and confirmed by other sources, modeling behavior with interfaces is better than relying on type checks.

-----------------------

J’ai choisi Maven parce que je l’avais déjà utilisé sur un projet précédent où j’avais rencontré des problèmes de bibliothèques, l’adopter à nouveau m’a permis d’éviter ces soucis de dépendances et d’approfondir ma maîtrise de l’outil. L’application est organisée en quatre packages, avec App comme point d’entrée. Dans ascii, la classe PrintAscii centralise le chargement et l’affichage des ressources ASCII. Au début, j’appelais des méthodes semblables depuis plusieurs entités, j’ai donc standardisé ce flux pour unifier le rendu des équipes, des armes et d’autres éléments.

Dans player, l’énum Team fournit des valeurs fixes ainsi que des utilitaires pour lister et choisir une équipe. Inventory gère les armes de chaque joueur de façon isolée, et Player implémente Iplace (présence sur la carte) et IDestructible (participation aux combats), tout en affichant l’avatar et l’inventaire.

Dans weapon, Weapon est une classe abstraite qui regroupe les membres communs à toutes les armes,les sous-classes (Axe, Bow, Sword, etc.) ne changent que les valeurs, conformément à la consigne du projet. WeaponStore modélise l’achat et la vente.

Dans gameMap, GameMap construit la carte, positionne le joueur, les obstacles et les monstres, affiche l’état courant, gère les déplacements du joueur et résout les combats. Pour cela, j’ai défini deux interfaces : Iplace pour le positionnement et l’affichage, et IDestructible pour le combat. Les entités (Monster, Obstacle, ExitCastele, EmptyCell et Player) implémentent ces rôles selon leur fonction. Le déroulé typique est : initialisation avec fisrtMaph, déplacements via playerMovement, rencontre puis fight, affichage ASCII avec PrintAscii, et fin sur ExitCastele avec les écrans de conclusion. Point de conception important : j’ai introduit Iplace précisément pour éviter l’usage de instanceof dans la résolution des combats et la localisation du joueur sur la carte, comme vu en cours et confirmé par d’autres sources, modéliser le comportement par des interfaces est préférable aux tests de type.

---


## How to Run the App

Make sure you have **Java 17+** and **Maven** installed. 

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="rpgEscapeCastele.App"
```

---

## How to Run `diagram.puml`

** VS Code extension**

1. Install the extension “PlantUML”.
2. Open `diagram.puml` and press `Alt` + `D` (or opiton + D on Mac) to preview.

---

## Resources

https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html
https://www.asciiart.eu
https://plantuml.com/class-diagram#google_vignette
https://marketplace.visualstudio.com/items?itemName=jebbs.plantuml
https://codegym.cc/groups/posts/matrix-in-java-2d-arrays
https://www.geeksforgeeks.org/java/multidimensional-arrays-in-java/