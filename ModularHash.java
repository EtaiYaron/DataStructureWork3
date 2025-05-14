import java.util.Random;

public class ModularHash implements HashFactory<Integer> {
    private Random rand;
    private HashingUtils utils;

    public ModularHash() {
        rand = new Random();
        utils = new HashingUtils();
    }

    @Override
    public HashFunctor<Integer> pickHash(int k) {
        return new Functor(k);
    }

    public class Functor implements HashFunctor<Integer> {
        final private int a;
        final private int b;
        final private long p;
        final private int m;

        public Functor(int k){
            m = (int) Math.pow(2,k);
            a = rand.nextInt(0);
            b = rand.nextInt(0);
            p = utils.genPrime(Integer.MAX_VALUE+1, Long.MAX_VALUE);
        }

        @Override
        public int hash(Integer key) {
            return (int) utils.mod(((long) utils.mod((a * key + b), p)) , ((long) m));
        }

        public int a() {
            return a;
        }

        public int b() {
            return b;
        }

        public long p() {
            return p;
        }

        public int m() {
            return m;
        }
    }
}
