public class Utils {
    public static void slowPrint(String texto, int delay) {
    for (char c : texto.toCharArray()) {
        System.out.print(c);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
     }
    }
}