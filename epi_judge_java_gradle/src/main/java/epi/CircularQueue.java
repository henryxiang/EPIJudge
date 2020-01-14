package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import java.util.List;
import java.util.NoSuchElementException;

public class CircularQueue {
  private static final int EXPANSION_FACTOR = 2;
  public static class Queue {
    Integer[] data;
    int size, head, tail;
    public Queue(int capacity) {
      data = new Integer[capacity];
      size = 0;
      head = 0;
      tail = 0;
    }
    public void enqueue(Integer x) {
      // TODO - you fill in here.
      if (data.length == size) {
        Integer[] temp = new Integer[data.length*EXPANSION_FACTOR];
        int it = head;
        for (int i = 0; i < size; i++) {
          temp[i] = data[it];
          it = (it+1) % size;
        }
        data = temp;
        head = 0;
        tail = size;
      }
      data[tail] = x;
      tail = (tail + 1) % data.length;
      size++;
      return;
    }
    public Integer dequeue() {
      // TODO - you fill in here.
      if (size == 0) throw new NoSuchElementException("Empty queue");
      Integer v = data[head];
      head = (head + 1) % data.length;
      size--;
      return v;
    }
    public int size() {
      // TODO - you fill in here.
      return size;
    }
    @Override
    public String toString() {
      // TODO - you fill in here.
      return super.toString();
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTest(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
