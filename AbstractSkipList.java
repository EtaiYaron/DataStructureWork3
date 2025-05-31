import java.util.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractSkipList {
    final protected SkipListNode head;
    final protected SkipListNode tail;
    private int cnt = 0;

    public AbstractSkipList() {
        head = new SkipListNode(Integer.MIN_VALUE);
        tail = new SkipListNode(Integer.MAX_VALUE);
        increaseHeight();
    }

    public void increaseHeight() {
        head.addLevel(tail, null);
        tail.addLevel(null, head);
        tail.setSkips(tail.height, cnt);
    }

    abstract void decreaseHeight();

    abstract SkipListNode find(int key);

    abstract int generateHeight();

    public SkipListNode search(int key) {
        SkipListNode curr = find(key);

        return curr.key() == key ? curr : null;
    }

    public SkipListNode insert(int key) {
        int height = generateHeight();
        while (height > head.height()) {
            increaseHeight();
        }
        SkipListNode prev = find(key);
        if (prev.key() == key) {
            return null;
        }
        SkipListNode inserted = new SkipListNode(key);
        int moves = 0;
        for (int level = 0; level <= head.height && prev != null; level++) {
            SkipListNode next = prev.getNext(level);
            if(level <= height){
                inserted.addLevel(next, prev);
                prev.setNext(level, inserted);
                next.setPrev(level, inserted);
                inserted.setSkips(level, moves);
                next.setSkips(level,next.getSkips(level)-moves);
            }
            else{
                next.setSkips(level,next.getSkips(level)+1);
            }
            while (prev != null && prev.height() == level) {
                moves = moves + prev.skips.get(level) + 1;
                prev = prev.getPrev(level);
            }

        }
        cnt++;
        return inserted;
    }



    public boolean delete(SkipListNode skipListNode) {

        if (skipListNode == null || find(skipListNode.key()).key() != skipListNode.key())
            return false;

        SkipListNode curr = skipListNode.next.get(0);

        for (int level = 0; level <= head.height(); level++) {
            if(level <= skipListNode.height){
                curr = skipListNode.next.get(level);
                SkipListNode prev = skipListNode.getPrev(level);
                SkipListNode next = skipListNode.getNext(level);
                prev.setNext(level, next);
                next.setPrev(level, prev);
                next.setSkips(level, skipListNode.getSkips(level)+next.getSkips(level));
            }
            else{
                while(curr.height < level){
                    curr = curr.getNext(level-1);
                }
                curr.setSkips(level,curr.getSkips(level)-1);
            }
        }

        while (head.height() >= 0 && head.getNext(head.height()) == tail) {
            decreaseHeight();
        }
        cnt--;
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
            if (curr.height >= level) {
                s.append(curr.key());
                s.append("    ");
            }
            else {
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
        public List<Integer> skips;
        private int height;

        public SkipListNode(int key) {
            super(key);
            next = new ArrayList<>();
            prev = new ArrayList<>();
            skips = new ArrayList<>();
            this.height = -1;
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

        public void addLevel(SkipListNode next, SkipListNode prev) {
            height++;
            this.next.add(next);
            this.prev.add(prev);
            this.skips.add(0);
        }

        public void removeLevel() {
            this.next.remove(height);
            this.prev.remove(height);
            this.skips.removeLast();
            height--;
        }

        public int height() {
            return height;
        }


        public int getSkips(int level) {
            return this.skips.get(level);
        }

        public void setSkips(int level, int value) {
            this.skips.set(level, value);
        }
    }
}

