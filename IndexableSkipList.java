public class IndexableSkipList extends AbstractSkipList {
    final protected double p;
    public IndexableSkipList(double probability) {
        super();
        this.p = probability;
    }

    @Override
    public void decreaseHeight() {
        if(this.head == null || this.head.height() < 0)
            return;
        int maxHeight = this.head.height();
        SkipListNode curr = this.head;
        while(curr != null){
            SkipListNode currAfterRemove = curr.getNext(maxHeight);
            curr.removeLevel();
            curr = currAfterRemove;
        }
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

    public int rank(int key) {
        int rank = -1;
        if(find(key).key() != key){
            rank++;
        }
        SkipListNode tmp = this.head;
        int level = head.height();
        while (level >= 0) {
            while (tmp.getNext(level) != tail && tmp.getNext(level).key() <= key) {

                tmp = tmp.getNext(level);
                rank += tmp.getSkips(level) + 1;

            }
            level--;
        }
        return rank;
    }



    public int select(int key) {
        int rank = -1;
        SkipListNode tmp = head;
        int level = head.height();
        while (level >= 0) {
            while(tmp.getNext(level)!=tail && rank + tmp.getNext(level).getSkips(level) + 1 <= key){
                tmp = tmp.getNext(level);
                rank += tmp.getSkips(level) + 1;

            }
            level--;
        }
        return tmp.key();
    }



}
