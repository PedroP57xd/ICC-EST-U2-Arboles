package materia.controller;

import materia.model.Node;

public class AVLTree {
    private Node root;

    public AVLTree() {
        this.root = null;
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, int value) {
        if (node == null) {
            Node newNode = new Node(value);
            newNode.setHeight(1);
            System.out.println("Nodo insertado: " + value + " con balance " + factorEquilibrio(newNode));
            return newNode;
        }
        System.out.println("Nodo actual: " + node.getValue());
        System.out.println("  Altura: " + root.getHeight());
        System.out.println("  Balance: " + factorEquilibrio(root));
        if (value < node.getValue()) {
            Node newNode = insertRec(node.getLeft(), value);
            node.setLeft(newNode);
        } else if (value > node.getValue()) {

            node.setRight(insertRec(node.getRight(), value));

        } else {

            return node;
        }

        int altura = 1 + Math.max(getHightRec(node.getLeft()), getHightRec(node.getRight()));
        node.setHeight(altura);

        int balance = factorEquilibrio(node);
        System.out.println("Balance del nodo " + node.getValue() + ": " + balance);

        if (balance > 1 && value < node.getLeft().getValue()) {
            System.out.println("rotacion simple izquierda");
            return leftRotatio(node);
        } else if (balance < -1 && value > node.getRight().getValue()) {
            System.out.println("rotacion simple derecha");
            return rightRotation(node);
        } else if (balance > 1 && value > node.getLeft().getValue()) {
            System.out.println("rotacion doble izquierda derecha");
            node.setLeft(insertRec(node.getLeft(), value));
            return rightRotation(node);
        } else if (balance < -1 && value < node.getRight().getValue()) {
            System.out.println("rotacion doble derecha izquierda");
            node.setRight(insertRec(node.getRight(), value));
            return leftRotatio(node);
        } else {

            return node;
        }
    }

    public void imprimirArbol() {
        imprimirArbolRec(root);
    }

    private void imprimirArbolRec(Node node) {
        if (node != null) {
            System.out.println(node.getValue() + ", ");
            imprimirArbolRec(node.getLeft());
        }
    }

    public int getHightTree() {
        return getHightRec(root);
    }

    public int getHightRec(Node node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();

    }

    public void printHeight() {
        System.out.println(getHightTree());
    }

    public void printerInOrderHeight() {
        printerInOrderRecHeight(root);
    }

    private void printerInOrderRecHeight(Node node) {
        if (node != null) {
            printerInOrderRecHeight(node.getLeft());
            int height = getHightRec(node);
            System.out.print(node.getValue() + "(h= " + height + "), ");
            printerInOrderRecHeight(node.getRight());
        }
    }

    public int factorEquilibrio(Node node) {
        int factorLeft = getHightRec(node.getLeft());
        int factorRight = getHightRec(node.getRight());
        int factor = factorLeft - factorRight;
        return factor;
    }

    public void printerInOrderfactorEquilibrio() {
        printerInOrderRecfactorEquilibrio(root);
    }

    private void printerInOrderRecfactorEquilibrio(Node node) {
        if (node != null) {
            printerInOrderRecfactorEquilibrio(node.getLeft());
            int factorEquilibrio = factorEquilibrio(node);
            System.out.print(node.getValue() + "(bf= " + factorEquilibrio + "), ");
            printerInOrderRecfactorEquilibrio(node.getRight());
        }
    }

    public boolean isEquilibrado() {
        return isEquilibradoRec(root);
    }

    private boolean isEquilibradoRec(Node node) {
        if (node == null) {
            return true;
        }
        int factor = factorEquilibrio(node);
        if (Math.abs(factor) > 1) {
            return false;
        }
        return isEquilibradoRec(node.getLeft()) && isEquilibradoRec(node.getRight());
    }

    public boolean printIsEquilibrado() {
        if (isEquilibrado()) {
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }

    public String nodosDesequilibrados() {
        return nodosDesequilibradosRec(root);
    }

    private String nodosDesequilibradosRec(Node node) {
        if (node == null) {
            return "";
        }
        // Usaremos una lista para almacenar los nodos desequilibrados con su FE
        java.util.List<String> nodosDesequilibrados = new java.util.ArrayList<>();
        nodosDesequilibradosRecHelper(node, nodosDesequilibrados);

        // Unimos la lista en un solo String separado por espacios
        return String.join(" ", nodosDesequilibrados);
    }

    // Método auxiliar para recorrer el árbol y llenar la lista
    private void nodosDesequilibradosRecHelper(Node node, java.util.List<String> lista) {
        if (node == null) {
            return;
        }
        int factor = factorEquilibrio(node);
        if (Math.abs(factor) > 1) {
            lista.add(node.getValue() + "(FE = " + factor + ")");
        }
        nodosDesequilibradosRecHelper(node.getLeft(), lista);
        nodosDesequilibradosRecHelper(node.getRight(), lista);
    }

    public void printNodosDesequilibrados() {
        String nodos = nodosDesequilibrados();
        if (nodos.isEmpty()) {
            System.out.println("No hay nodos desequilibrados.");
        } else {
            System.out.println(nodos);
        }
    }

    private Node leftRotatio(Node x) {
        Node y = x.getRight();
        Node temp = y.getLeft();

        // Imprimir antes de la rotación
        System.out.println("Rotación izquierda en el nodo: " + x.getValue() + "con balance " + factorEquilibrio(x));
        // Realiazar la rotación
        y.setLeft(x);
        x.setRight(temp);
        // Actualizar las alturas
        x.setHeight(Math.max(getHightRec(x.getLeft()), getHightRec(x.getRight())) + 1);
        y.setHeight(Math.max(getHightRec(y.getLeft()), getHightRec(y.getRight())) + 1);
        // imprimir informacion despues de la rotacion
        System.out.println("Nueva raiz despues de la rotacion " + y.getValue());

        // Retornar nueva raíz
        return y;
    }

    private Node rightRotation(Node y) {
        Node x = y.getLeft();
        if (x == null) {
            // No se puede realizar rotación si el hijo izquierdo es null
            System.out.println("No se puede realizar rotación derecha porque el hijo izquierdo es null.");
            return y;
        }
        Node temp = x.getRight();

        // Imprimir antes de la rotación
        System.out.println("Rotación derecha en el nodo: " + y.getValue() + " con balance " + factorEquilibrio(y));
        // Realizar la rotación
        x.setRight(y);
        y.setLeft(temp);
        // Actualizar las alturas
        y.setHeight(Math.max(getHightRec(y.getLeft()), getHightRec(y.getRight())) + 1);
        x.setHeight(Math.max(getHightRec(x.getLeft()), getHightRec(x.getRight())) + 1);
        // imprimir informacion despues de la rotacion
        System.out.println("Nueva raiz despues de la rotacion " + x.getValue());

        // Retornar nueva raíz
        return x;
    }

}