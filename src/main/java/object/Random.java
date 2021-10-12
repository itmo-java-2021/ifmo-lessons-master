package object;

public class Random {

    public static int getRandom(int min, int max){
        java.util.Random random = new java.util.Random();
        return  random.nextInt(max - min) + min;
    }

    public static int[] getArray(int length, int min, int max){
        java.util.Random random = new java.util.Random();
        int[] vs = new int[length];
        for (int i = 0; i < length; i++) {
            vs[i] = random.nextInt(max - min) + min;
        }

        return  vs;
    }
}
