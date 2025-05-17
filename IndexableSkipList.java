import java.util.Random;

public class IndexableSkipList extends AbstractSkipList {
    final protected double p;	// p is the probability for "success" in the geometric process generating the height of each node.
    public IndexableSkipList(double probability) {
        super();
        this.p = probability;
    }
	
	@Override
    public void decreaseHeight() {
        this.head.removeLevel();
        this.tail.removeLevel();
    }

    @Override
    public SkipListNode find(int key) {
        int h = this.head.height();
        SkipListNode node = this.head;
        for (int i = h; i >= 0; i--){
            SkipListNode nodetemp = node.getNext(i);
            while (nodetemp != tail && nodetemp.key() <= key)
            {
                if (nodetemp.key() == key)
                    return nodetemp;
                else
                    node = nodetemp;
            }
        }
        return node;
    }

    @Override
    public int generateHeight() {
        int height = 0;
        while (Math.random() > p) {
            height++;
            this.increaseHeight();
        }
        return height;
    }

    @Override
    public int rank(int key) {
        int h = this.head.height();
        SkipListNode node = this.head;
        int rank = 0;
        for (int i = h; i >= 0; i--){
            SkipListNode nodetemp = node.getNext(i);
            while (nodetemp != tail && nodetemp.key() <= key)
            {
                rank += nodetemp.skip;
                if (nodetemp.key() == key)
                    return rank;
                else
                    node = nodetemp;
            }
        }
        return rank;
    }


    public int select(int index) {
        int h = this.head.height();
        SkipListNode node = this.head;
        int cnt = 0;
        for (int i = h; i == 0; i--){
            SkipListNode nodetemp = node.getNext(i);
            while (nodetemp != tail && cnt + nodetemp.skip <= index)
            {
                cnt += nodetemp.skip;
                node = nodetemp;
            }
        }
        if (node.getNext(0) != tail && cnt + 1 == index + 1) {
            return node.getNext(0).key();
        }
        return -1;
    }

}
