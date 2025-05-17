import java.util.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractSkipList {
    final protected SkipListNode head;
    final protected SkipListNode tail;

    public AbstractSkipList() {
        head = new SkipListNode(Integer.MIN_VALUE);
        tail = new SkipListNode(Integer.MAX_VALUE);
        increaseHeight();
    }

    public void increaseHeight() {
        head.addLevel(tail, null, 1);
        tail.addLevel(null, head, 0);
    }

    abstract void decreaseHeight();
    abstract SkipListNode find(int key);
    abstract int generateHeight();
    abstract int rank(int key);

    public SkipListNode search(int key) {
        SkipListNode curr = find(key);
        return curr.key() == key ? curr : null;
    }

    public SkipListNode insert(int key) {
        int nodeHeight = generateHeight();
        while (nodeHeight > head.height()) {
            increaseHeight();
        }
        int maxLevel = head.height();

        SkipListNode[] update = new SkipListNode[maxLevel + 1];
        int[] steps = new int[maxLevel + 1];
        SkipListNode node = head;
        int totalSteps = 0;

        for (int i = maxLevel; i >= 0; i--) {
            while (node.getNext(i) != tail && node.getNext(i).key() < key) {
                totalSteps += node.getSkip(i);
                node = node.getNext(i);
            }
            update[i] = node;
            steps[i] = totalSteps;
        }

        if (node.getNext(0) != tail && node.getNext(0).key() == key) {
            return null;
        }

        SkipListNode newNode = new SkipListNode(key);

        for (int i = 0; i <= nodeHeight; i++) {
            SkipListNode next = update[i].getNext(i);

            int skipped = steps[0] - steps[i];
            int newNodeSkip = update[i].getSkip(i) - skipped;
            if (newNodeSkip <= 0) newNodeSkip = 1; // safety

            newNode.addLevel(next, update[i], newNodeSkip);
            update[i].setNext(i, newNode);
            next.setPrev(i, newNode);

            update[i].setSkip(i, skipped + 1);
        }

        for (int i = nodeHeight + 1; i <= maxLevel; i++) {
            update[i].setSkip(i, update[i].getSkip(i) + 1);
        }

        return newNode;
    }

    public boolean delete(SkipListNode node) {
        int maxLevel = node.height();
        for (int i = 0; i <= maxLevel; i++) {
            SkipListNode prev = node.getPrev(i);
            SkipListNode next = node.getNext(i);

            prev.setNext(i, next);
            next.setPrev(i, prev);

            prev.setSkip(i, prev.getSkip(i) + node.getSkip(i) - 1);
        }

        for (int i = maxLevel + 1; i <= head.height(); i++) {
            SkipListNode prev = head;
            while (prev.getNext(i) != tail && prev.getNext(i) != node) {
                prev = prev.getNext(i);
            }
            prev.setSkip(i, prev.getSkip(i) - 1);
        }

        while (head.height() >= 0 && head.getNext(head.height()) == tail) {
            decreaseHeight();
        }
        return true;
    }

    public SkipListNode predecessor(SkipListNode skipListNode) {
        return skipListNode.getPrev(0);
    }

    public SkipListNode successor(SkipListNode skipListNode) {
        return skipListNode.getNext(0);
    }

    public SkipListNode minimum() {
        if (head.getNext(0) == tail) {
            throw new NoSuchElementException("Empty Linked-List");
        }
        return head.getNext(0);
    }

    public SkipListNode maximum() {
        if (tail.getPrev(0) == head) {
            throw new NoSuchElementException("Empty Linked-List");
        }
        return tail.getPrev(0);
    }

    private void levelToString(StringBuilder s, int level) {
        s.append("H    ");
        SkipListNode curr = head.getNext(0);

        while (curr != tail) {
            if (curr.height() >= level) {
                s.append(curr.key());
                s.append("    ");
            } else {
                s.append("    ");
                for (int i = 0; i < curr.key().toString().length(); i = i + 1)
                    s.append(" ");
            }
            curr = curr.getNext(0);
        }
        s.append("T\n");
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int level = head.height(); level >= 0; --level) {
            levelToString(str, level);
        }
        return str.toString();
    }

    public static class SkipListNode extends Element<Integer, Object> {
        final private List<SkipListNode> next;
        final private List<SkipListNode> prev;
        private int height;
        private List<Integer> skips;

        public SkipListNode(int key) {
            super(key);
            next = new ArrayList<>();
            prev = new ArrayList<>();
            this.height = -1;
            skips = new ArrayList<>();
        }

        public SkipListNode getPrev(int level) {
            if (level > height) {
                throw new IllegalStateException("Fetching height higher than current node height");
            }
            return prev.get(level);
        }

        public SkipListNode getNext(int level) {
            if (level > height) {
                throw new IllegalStateException("Fetching height higher than current node height");
            }
            return next.get(level);
        }

        public void setNext(int level, SkipListNode next) {
            if (level > height) {
                throw new IllegalStateException("Fetching height higher than current node height");
            }
            this.next.set(level, next);
        }

        public void setPrev(int level, SkipListNode prev) {
            if (level > height) {
                throw new IllegalStateException("Fetching height higher than current node height");
            }
            this.prev.set(level, prev);
        }

        public void addLevel(SkipListNode next, SkipListNode prev, int skip) {
            ++height;
            this.next.add(next);
            this.prev.add(prev);
            this.skips.add(skip);
        }

        public void removeLevel() {
            this.next.remove(height);
            this.prev.remove(height);
            this.skips.remove(height);
            --height;
        }

        public int height() {
            return height;
        }

        public int getSkip(int level) {
            if (level > height) {
                throw new IllegalStateException("Fetching skip at level higher than current node height");
            }
            return skips.get(level);
        }

        public void setSkip(int level, int skip) {
            if (level > height) {
                throw new IllegalStateException("Setting skip at level higher than current node height");
            }
            skips.set(level, skip);
        }
    }
}
