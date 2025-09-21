public class nullNode<T extends Comparable<T>> extends RBTNode<T>{
    public nullNode() {
        super(null);
        item = null;
        left = null;
        right = null;
        isRed = false;
    }
}