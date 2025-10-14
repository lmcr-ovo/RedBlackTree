import org.junit.Test;
import static org.junit.Assert.*;

public class RBTreeTest {

    @Test
    public void testIntegerInsertAndNearest() {
        RBTree<Integer> tree = new RBTree<>();
        for (int i = 0; i < 20; i++) {
            tree.insert(i * i);
        }
        System.out.println("整数树结构:\n" + tree);

        RBTNode<Integer> nearestInt = tree.nearest(180, i -> i.doubleValue());
        assertNotNull(nearestInt);
        System.out.println("Nearest to 180: " + nearestInt.item);

        // 180 应该接近 169 或 196，这里断言两者之一
        assertTrue(nearestInt.item == 169 || nearestInt.item == 196);
    }

    @Test
    public void testStringInsertAndNearest() {
        RBTree<String> stringTree = new RBTree<>();
        String[] animals = {"cat", "elephant", "dog", "tiger", "lion", "hippopotamus"};
        for (String a : animals) {
            stringTree.insert(a);
        }
        System.out.println("字符串树结构:\n" + stringTree);

        String target = "giraffe"; // 长度 7
        RBTNode<String> nearestStr = stringTree.nearest(target, s -> s.length());
        assertNotNull(nearestStr);
        System.out.println("目标字符串: " + target +
                "，最接近的字符串: " + nearestStr.item +
                "（长度: " + nearestStr.item.length() + "）");

        // 检查最近长度差是否 <= 1
        int diff = Math.abs(target.length() - nearestStr.item.length());
        assertTrue(diff <= 1);
    }

    @Test
    public void testDateNearest() {
        RBTree<java.util.Date> dateTree = new RBTree<>();
        dateTree.insert(new java.util.Date(1000000L));
        dateTree.insert(new java.util.Date(2000000L));
        dateTree.insert(new java.util.Date(3000000L));
        dateTree.insert(new java.util.Date(4000000L));

        java.util.Date targetDate = new java.util.Date(2500000L);
        RBTNode<java.util.Date> nearestDate = dateTree.nearest(targetDate, d -> d.getTime());
        assertNotNull(nearestDate);
        System.out.println("目标日期时间戳: " + targetDate.getTime() +
                "，最接近的日期时间戳: " + nearestDate.item.getTime());

        long diff = Math.abs(targetDate.getTime() - nearestDate.item.getTime());
        assertTrue(diff <= 1000000L);
    }
}
