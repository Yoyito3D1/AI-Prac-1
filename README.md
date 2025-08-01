# 🗺️ Pathfinding Algorithms: A* & Best-First Search 🚀

Aquest projecte implementa i compara dos algoritmes clàssics de cerca heurística per trobar camins òptims en un mapa: **A\*** i **Best-First Search**. Està desenvolupat en Java i treballa amb mapes representats com a matrius carregades des de fitxers de text.

---

## 🔍 Descripció del projecte

- **A\*** (A-Star): Algoritme de cerca informada que utilitza una heurística per trobar el camí més curt des d’un node inicial fins a un node final, considerant el cost acumulat i l’estimació restant.  
- **Best-First Search**: Cerca heurística que prioritza expandir el node més proper al destí segons una funció heurística, però sense tenir en compte el cost acumulat com A*.

Els mapes s’implementen mitjançant la classe `MapGrid`, que carrega una matriu des d’un fitxer `.txt`. Els nodes són instàncies de la classe `Node`, que guarden informació de posició, cost i heurística.

---

## 🛠️ Funcionament

- Es pot triar entre executar A* o Best-First Search canviant una flag (`Node.setUseAStar(true/false)`).  
- Els mapes d’exemple es troben a `src/data/matrix.txt` i `src/data/matrix2.txt`.  
- L’algoritme busca el camí entre un node d’inici i un node final donats (per exemple, `(0,0)` a `(9,9)`).  
- Es mostra per consola la seqüència de nodes del camí trobat, el cost total i el nombre d’iteracions que ha trigat l’algoritme.  
- També es visualitza el camí sobre la matriu impresa a consola.

---

## 📂 Estructura principal del codi

- **Main.java**: punt d’entrada on es configura el mapa, els nodes d’inici i final, i s’executa l’algoritme seleccionat.  
- **algorithms.AStar**: implementació de l’algoritme A*.  
- **algorithms.BestFirstSearch**: implementació de l’algoritme Best-First Search.  
- **heuristics.HeuristicImplementation**: classe que defineix la funció heurística utilitzada.  
- **utils.MapGrid**: classe per gestionar el mapa i imprimir el camí.  
- **models.Node**: representació dels nodes amb dades de posició, cost i heurística.

---
