import java.util.Random;

public class IndexableSkipList extends AbstractSkipList {
    final protected double p; // Probability for geometric height

    public IndexableSkipList(double probability) {
        super();
        this.p = probability;
    }

    @Override
    public void decreaseHeight() {
        head.removeLevel();
        tail.removeLevel();
    }

    @Override
    public SkipListNode find(int key) {
        SkipListNode node = head;
        int level = head.height();
        while (level >= 0) {
            while (node.getNext(level) != tail && node.getNext(level).key() <= key) {
                node = node.getNext(level);
            }
            level--;
        }
        return node;
    }

    @Override
    public int generateHeight() {
        int h = 0;
        while (Math.random() > p) {
            h++;
        }
        return h;
    }

    @Override
    public int rank(int key) {
        SkipListNode node = head;
        int level = head.height();
        int rank = 0;
        while (level >= 0) {
            while (node.getNext(level) != tail && node.getNext(level).key() < key) {
                rank += node.getSkip(level);
                node = node.getNext(level);
            }
            level--;
        }
        return rank;
    }

    public int select(int index) {
        SkipListNode node = head;
        int level = head.height();
        int cnt = 0;
        while (level >= 0) {
            while (node.getNext(level) != tail && cnt + node.getSkip(level) <= index) {
                cnt += node.getSkip(level);
                node = node.getNext(level);
            }
            level--;
        }
        node = node.getNext(0);
        if (node != tail && cnt == index) {
            return node.key();
        }
        return -1;
    }
}
