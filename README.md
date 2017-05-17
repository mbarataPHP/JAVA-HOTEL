# JAVA HOTEL

## installation

Cloner le repo: [https://github.com/KILLERMAX91/JAVA-HOTEL](https://github.com/KILLERMAX91/JAVA-HOTEL) 

Installer les extensions sur le projet:

- **toplink-essentials-agent.jar**
- **toplink-essentials.jar**
- **mysql-connector-java-5.1.41-bin.jar**

>MySQL

Créer une base de données ou vous le nommerez  `hotel`

1. lancer une fois l'apllication afin créer les tables automatiquement.
2. puis inserer les requetes qui se trouve das le fichier `JAVA-HOTEL\code\data\hotel.sql`.


## TUTO

### CONTROLLER

Pour créer un controller allez dans le dossier: `JAVA-HOTEL\code\src\Controller`

Puis créer un fichier `nomCtrl.java` mettre **Ctrl** à la fin est conseiller.

```java
package Controller;

import Annotation.View;

@View(view="MaVue.fxml", css={"base.css"})
public class nomCtrl extends Parent.Ctrl{
	
    ...
}
```

L'annotation `@View(...)` comporte deux paramétres

- **view** appelle la vue mais c'est aussi le nom de la route
- **css** appelle le css

vous pouvez voir les noms des routes qui sont liés à leur Controller dans le fichier log  `JAVA-HOTEL\code\log\logCtrl.txt`

pour changer de controller(**changer de page**)
```java
    public void changeCtrl(){

        Route route = (Route) this.dependance.get("route");
    
        route.get("autreView.fxml");
    }
```
il faut que la vue `autreView.fxml` soit appeller dans un autre controller `@View(view="autreView.fxml")`
### MODEL

