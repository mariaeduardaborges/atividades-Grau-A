package lista2;

class TSTNode {
    char data;
    boolean isEndOfWord;
    TSTNode left, middle, right;

    TSTNode(char data) {
        this.data = data;
        isEndOfWord = false;
        left = null;
        middle = null;
        right = null;
    }
}

public class TST {
    private TSTNode root;

    TST() {
        root = null;
    }

    void inserir(String key) {
        root = inserirUtil(root, key, 0);
    }

    TSTNode inserirUtil(TSTNode node, String key, int index) {
        char c = key.charAt(index);
        if (node == null) {
            node = new TSTNode(c);
        }

        if (c < node.data) {
            node.left = inserirUtil(node.left, key, index);
        } else if (c > node.data) {
            node.right = inserirUtil(node.right, key, index);
        } else {
            if (index < key.length() - 1) {
                node.middle = inserirUtil(node.middle, key, index + 1);
            } else {
                node.isEndOfWord = true;
            }
        }

        return node;
    }

    boolean search(String key) {
        return searchUtil(root, key, 0);
    }

    boolean searchUtil(TSTNode node, String key, int index) {
        if (node == null) {
            return false;
        }

        char c = key.charAt(index);

        if (c < node.data) {
            return searchUtil(node.left, key, index);
        } else if (c > node.data) {
            return searchUtil(node.right, key, index);
        } else {
            if (index == key.length() - 1) {
                return node.isEndOfWord;
            } else {
                return searchUtil(node.middle, key, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        TST tst = new TST();

        String[] words = { "bread", "apple", "bridge", "brie", "app", "llama", "lamp" };

        System.out.println("Inserindo palavras na árvore TST:");

        for (String word : words) {
            System.out.println("\n--- Inserindo palavra: " + word + " ---");
            tst.inserir(word);
        }

        System.out.println("\nInserção concluída.");

        String[] testWords = { "bread", "app", "banana", "bridge", "apple", "brie", "llama", "lamp", "duda" };

        System.out.println("\nVerificando existência na árvore TST:");

        for (String word : testWords) {
            if (tst.search(word)) {
                System.out.println(word + " está presente na árvore.");
            } else {
                System.out.println(word + " não está presente na árvore.");
            }
        }
    }
}
