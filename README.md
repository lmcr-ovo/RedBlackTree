# 🌳 Java 左倾红黑树（Left-Leaning Red-Black Tree, LLRB）

## 📖 简介
本项目实现了 **左倾红黑树**（Left-Leaning Red-Black Tree），一种高效的 **自平衡二叉查找树**，在插入数据时会自动保持平衡，保证查找、插入的时间复杂度趋近 **O(log n)**。

特点：
- 使用泛型，支持任意可比较类型（如 Integer、String、Date 等）
- 插入后自动平衡，无需额外维护
- 可查找最近值（Nearest），不仅限于精确匹配
- 使用 **`nullNode`** 哨兵节点替代 `null`，降低空指针风险
- 提供可视化层序输出，清晰展示节点位置与颜色（⚫黑色 / 🔴红色）

---

## 📂 类结构

### **RBTNode<T>**
红黑树普通节点类：
- `item`：存储的值，类型为 `T extends Comparable<T>`
- `left` / `right`：左右子节点
- `isRed`：节点颜色标记

主要静态方法：
- `isRed()`：判断节点是否为红色
- `insert()`：插入并触发自平衡操作
- `rotateLeft()` / `rotateRight()`：左旋 / 右旋
- `flipColors()`：颜色翻转
- `search()`：查找指定值
- `nearest()`：查找与目标值数值差或距离最近的节点

---

### **nullNode<T>**
特殊哨兵节点类：
- 永远为黑色
- `item` 固定为 `null`
- 避免使用 `null` 引用，简化空判断逻辑

---

### **RBTree<T>**
红黑树封装类：
- `root`：根节点
- `size`：树中有效节点数量

主要方法：
- `insert(T item)`：插入新值
- `search(T item)`：查找值，未找到则返回 `nullNode`
- `nearest(T item, ToDoubleFunction<T> valueFunc)`：找最近值
- `size()`：节点总数
- `toString()`：层序遍历输出树结构（带颜色标记）

---

## ⚙️ 左倾红黑树平衡规则
插入完成后按照如下规则保持树的平衡：
1. **右子是红色、左子是黑色** → 左旋  
2. **左子是红色且它的左子也是红色** → 右旋  
3. **左右子都是红色** → 颜色翻转  
4. **根节点始终保持黑色**

---

## 📌 使用示例
```java
RBTree<Integer> tree = new RBTree<>();
tree.insert(10);
tree.insert(5);
tree.insert(15);

System.out.println(tree);

RBTNode<Integer> nearest = tree.nearest(7, x -> x.doubleValue());
System.out.println("Nearest to 7: " + nearest.item);
```

---

## 🖨 输出示例
插入数据后调用 `toString()`，可能输出：
```
⚫10 
🔴5 ⚫15
```
- `⚫` 表示黑色节点  
- `🔴` 表示红色节点  

---

## 🧪 测试说明
已编写 **JUnit 单元测试** 覆盖：
- 整数、字符串、日期的插入与最近值查找
- 空树、单节点树的边界情况
- 重复插入不增加节点数
- 查找不存在的值
- 最近值位于不同子树的场景
- 插入随机数后中序遍历保持有序性

---

## 📈 复杂度
- 插入：O(log n)
- 查找：O(log n)
- 最近值查找：O(log n)
红黑树保证了高度近似平衡，性能稳定。

