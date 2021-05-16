package Auth;

public class Node {
    public String val;
    public Node[] children;
    
    public static Node Node(String val) {
        Node n = new Node();
        n.val = val;
        return n;
    }
}
