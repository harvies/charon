## 疑问？

### 优先队列是什么？
优先队列是一种按优先级取数据的队列（一般队列是先进先出）。

### 优先队列用来解决什么问题？
求 Top K 大/小 的元素

### 优先队列为什么一般用堆来实现？
节省内存

## 属性
```java
transient Object[] queue; //存放实际数据

private int size = 0; //优先队列中的元素个数

private final Comparator<? super E> comparator; //默认使用元素实现的比较器

transient int modCount = 0; //结构被修改的次数,防止迭代时被修改 see AbstractList

```

## 构造方法
```java
 public PriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

public PriorityQueue(int initialCapacity) {
        this(initialCapacity, null);
    }

public PriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

```

## 应用
```java
@Test
    public void test() {
        //从大到小排序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        priorityQueue.add(1);
        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(4);
        priorityQueue.add(7);
        priorityQueue.add(2);
        priorityQueue.add(6);
        System.out.println("array:" + priorityQueue);
        System.out.println("peek:" + priorityQueue.peek());
        System.out.println("poll:" + priorityQueue.poll());
        System.out.println("poll:" + priorityQueue.poll());

//           7
//         5   6   
//        1 4 2 3    
    }

```
输出
```shell script
array:[7, 5, 6, 1, 4, 2, 3]
peek:7
poll:7
poll:6
```