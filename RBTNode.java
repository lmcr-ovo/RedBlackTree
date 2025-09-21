public class RBTNode<T extends Comparable<T>> {
    public T item;
    public RBTNode<T> left;
    public RBTNode<T> right;
    public boolean isRed;

    public RBTNode(T item) {
        this.item = item;
        if (!(this instanceof nullNode)) {
            left = new nullNode<>();
            right = new nullNode<>();
        }
        isRed = true;
    }

    public static <T extends Comparable<T>>
    boolean isRed(RBTNode<T> node) {
        return node.isRed;
    }
    public static <T extends Comparable<T>>
    RBTNode<T> insert(RBTNode<T> node, T item) {
        if (node instanceof nullNode<T>)
            return new RBTNode<>(item);

        int cmp = item.compareTo(node.item);
        if (cmp == 0) return node;
        else if (cmp < 0) node.left = insert(node.left, item);
        else node.right = insert(node.right, item);

        if (!isRed(node.left) && isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) node = flipColors(node);
        return node;
    }

    public static <T extends Comparable<T>>
    RBTNode<T> rotateLeft(RBTNode<T> node) {
        RBTNode<T> next = node.right;
        node.right = next.left;
        next.left = node;

        next.isRed = node.isRed;
        node.isRed = true;
        return next;
    }

    public static <T extends Comparable<T>>
    RBTNode<T> rotateRight(RBTNode<T> node) {
        RBTNode<T> next = node.left;
        node.left = next.right;
        next.right = node;

        next.isRed = node.isRed;
        node.isRed = true;
        return next;
    }

    public static <T extends Comparable<T>>
    RBTNode<T> flipColors(RBTNode<T> node) {
        node.left.isRed = false;
        node.right.isRed = false;

        node.isRed = true;
        return node;
    }
}
