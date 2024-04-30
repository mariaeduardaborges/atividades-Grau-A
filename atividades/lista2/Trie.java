package lista2;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

public class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insert(String key) {
        TrieNode current = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    boolean search(String key) {
        TrieNode current = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return (current != null && current.isEndOfWord);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        String[] words = { "bread", "apple", "bridge", "brie", "app", "llama", "lamp" };

        System.out.println("Inserindo palavras na árvore TRIE:");

        for (String word : words) {
            System.out.println("\n--- Inserindo palavra: " + word + " ---");
            trie.insert(word);
        }

        System.out.println("\nInserção concluída.");

        String[] testWords = { "bread", "app", "banana", "bridge", "apple", "brie", "llama", "lamp", "rainbow" };

        System.out.println("\nVerificando existência na árvore TRIE:");

        for (String word : testWords) {
            if (trie.search(word)) {
                System.out.println(word + " está presente na árvore.");
            } else {
                System.out.println(word + " não está presente na árvore.");
            }
        }
    }
}
