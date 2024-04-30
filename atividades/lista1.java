class Node {
    int chave;
    int altura;
    Node left, right;

    Node(int value) {
        chave = value;
        altura = 1;
    }
}

public class lista1 {
    Node root;

    int altura(Node node) {
        if (node == null)
            return 0;
        return node.altura;
    }

    int balanceamento(Node node) {
        if (node == null)
            return 0;
        return altura(node.left) - altura(node.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.altura = Math.max(altura(y.left), altura(y.right)) + 1;
        x.altura = Math.max(altura(x.left), altura(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.altura = Math.max(altura(x.left), altura(x.right)) + 1;
        y.altura = Math.max(altura(y.left), altura(y.right)) + 1;

        return y;
    }

    Node inserir(Node node, int chave) {
        if (node == null)
            return (new Node(chave));

        if (chave < node.chave) {
            System.out.println("\nInserindo " + chave + " à esquerda de " + node.chave);
            node.left = inserir(node.left, chave);
        } else if (chave > node.chave) {
            System.out.println("\nInserindo " + chave + " à direita de " + node.chave);
            node.right = inserir(node.right, chave);
        } else {
            System.out.println("Chave " + chave + " já existe na árvore!");
            return node;
        }

        node.altura = 1 + Math.max(altura(node.left), altura(node.right));

        int balance = balanceamento(node);

        if (balance > 1 && chave < node.left.chave) {
            System.out.println("\nRotação simples à direita em " + node.chave);
            return rightRotate(node);
        }

        if (balance < -1 && chave > node.right.chave) {
            System.out.println("\nRotação simples à esquerda em " + node.chave);
            return leftRotate(node);
        }

        if (balance > 1 && chave > node.left.chave) {
            System.out.println("Rotação dupla à direita em " + node.chave);
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && chave < node.right.chave) {
            System.out.println("\nRotação dupla à esquerda em " + node.chave);
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.chave + " ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        lista1 tree = new lista1();

        System.err.println("---arvores Avl---");

        int[] keys = { 10, 5, 20, 25, 30, 15, 23, 35, 33 };
        for (int key : keys) {
            System.out.println("\nInserindo chave: " + key);
            tree.root = tree.inserir(tree.root, key);
            System.out.println("\nÁrvore AVL após inserção de " + key);
            tree.inorder(tree.root);
        }

        tree.root = null;
        
        // pt2

        int[] keysForRotation = { 80, 96, 45, 6, 20 };
        for (int key : keysForRotation) {
            System.out.println("\nInserindo chave: " + key);
            tree.root = tree.inserir(tree.root, key);
            System.out.println("\nÁrvore AVL após inserção de " + key);
            tree.inorder(tree.root);
        }
    }
}
