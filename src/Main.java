public class Main {
    public static void main(String[] args) throws Exception {
        Desenvolvedor dev = new Desenvolvedor("Bernardo Dev");
        Carreira carreira = new Carreira(dev);
        Carreira.loadingBar(Color.BLUE + "Inicializando Sistema..." + Color.RESET, 20, 80);
        Carreira.loadingBar(Color.CYAN + "Preparando Menu..." + Color.RESET, 15, 60);
        
        carreira.iniciarJornada();
        carreira.executarTurnoTexto();
        dev.mostrarStatus();
    }
}