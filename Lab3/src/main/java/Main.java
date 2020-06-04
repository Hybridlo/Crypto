

public class Main {
    public static void main(String[] args) throws Exception {
        MD5 md5 = new MD5();

        System.out.println(md5.hash("Hello"));

        /*BitArray arr = BitArray.fromLongs(new long[]{0x10});
        Thread.yield();*/
    }
}
