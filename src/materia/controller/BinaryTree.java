package materia.controller;

import materia.model.Node;

public class BinaryTree {
    private Node root;
    private int peso = 0;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(int value) {
        root = insertRec(root, value);
        peso++;
    }

    private Node insertRec(Node padre, int value) {
        if (padre == null) {
            return new Node(value);
        }
        if (value < padre.getValue()) {
            Node newNode = insertRec(padre.getLeft(), value);
            padre.setLeft(newNode);
        } else if (value > padre.getValue()) {

            padre.setRight(insertRec(padre.getRight(), value));

        }

        return padre;
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

    public void printPosOrder() {
        printPosOrderRec(root);
    }

    private void printPosOrderRec(Node node) {
        if (node != null) {
            printPosOrderRec(node.getLeft());
            printPosOrderRec(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }

    public void printerInOrder() {
        printerInOrderRec(root);
    }

    private void printerInOrderRec(Node node) {
        if (node != null) {
            printerInOrderRec(node.getLeft());
            System.out.print(node.getValue() + " ");
            printerInOrderRec(node.getRight());
        }
    }

    public boolean serch(int value) {
        return searchRec(root, value);
    }

    public boolean searchRec(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (node.getValue() == value) {
            return true;
        }
        if (value < node.getValue()) {
            return searchRec(node.getLeft(), value);
        } else {
            return searchRec(node.getRight(), value);
        }
    }

    public int getHightTree() {
        return getHightRec(root);
    }

    public int getHightRec(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHightRec(node.getLeft());
        int rightHeight = getHightRec(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1; // leftheight> rightHeight ? leftHeight + 1 : rightHeight + 1;

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

    public int pesoDelArbol() {
        return pesoDelArbolRec(root);
    }

    private int pesoDelArbolRec(Node node) {
        return peso;
    }

    public void printPesoDelArbol() {
        System.out.println(pesoDelArbol());
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
    
}