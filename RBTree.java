import java.util.function.ToDoubleFunction;

public class RBTree<T extends Comparable<T>> {
    private RBTNode<T> root;
    private int size;

    public RBTree() {
        root = new nullNode<>();
        size = 0;
    }

    public RBTree(T item) {
        root = new RBTNode<>(item);
        size = 1;
    }

    public int size() {
        return size;
    }

    public void insert(T item) {
        root = RBTNode.insert(root, item);
        root.isRed = false;
        size++;
    }

    public RBTNode<T> search(T item) {
        return RBTNode.search(root, item);
    }

    public RBTNode<T> nearest(T item, ToDoubleFunction<T> valueFunc) {
        return RBTNode.nearest(root, item, new nullNode<>(), valueFunc);
    }

    // 在 RBTree 里加一层序 toString()
    @Override
    public String toString() {
        if (root instanceof nullNode) return "[]";
        StringBuilder sb = new StringBuilder();
        java.util.Queue<RBTNode<T>> q = new java.util.ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                RBTNode<T> n = q.poll();
                sb.append(n.isRed ? "🔴" : "⚫").append(n.item).append(' ');
                if (!(n.left instanceof nullNode)) q.add(n.left);
                if (!(n.right instanceof nullNode)) q.add(n.right);
            }
            sb.append('\n');
        }
        return sb.toString().trim();
    }
}
