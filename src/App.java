
import materia.controller.BinaryTree;

public class App {
    public static void main(String[] args) throws Exception {

        BinaryTree ab = new BinaryTree();
        ab.insert(50);
        ab.insert(17);
        ab.insert(76);
        ab.insert(9);
        ab.insert(23);
        ab.insert(54);
        ab.insert(14);
        ab.insert(19);
        System.out.println("\nNombre: Pedro Panjón");
        System.out.println(" ");
        System.out.print("Peso del Arbol: ");
        ab.printPesoDelArbol();
        System.out.print("Altura del Arbol: ");
        ab.printHeight();
        System.out.println("\n ");
        System.out.print("PosOrden= ");
        ab.printPosOrder();
        System.out.println(" ");
        System.out.print("InOrden= ");
        ab.printerInOrder();
        System.out.println("");
        System.out.println("\n Arbol InOrden con alturas:");
        ab.printerInOrderHeight();
        System.out.println(" ");
        System.out.println("\n Arbol InOrden con Factor Equilibrio:");
        ab.printerInOrderfactorEquilibrio();
        System.out.println(" ");
        System.out.print("\nArbol esta Equilibrado: ");
        ab.printIsEquilibrado();
        System.out.print("Existe el nodo 15: ");
        if (ab.serch(15)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        System.out.println("Afregamos el nodo 15");
        ab.insert(15);
        System.out.print("\nArbol esta Equilibrado: ");
        ab.printIsEquilibrado();
        System.out.println("");
        System.out.println("\nArbol InOrden con Factor Equilibrio:");
        ab.printerInOrderfactorEquilibrio();
        System.out.print("\nNodos Desequilibrados: ");
        ab.printNodosDesequilibrados();
        System.out.println("\n");

    }
}
