public class ProjetoIA extends Projeto {

    public ProjetoIA(String nome, int dificuldade, int recompensaEmXP) {
        super(nome, dificuldade, recompensaEmXP, TipoProjeto.IA);
    }

    @Override
    protected int calcularRecompensa(Desenvolvedor dev) {
        int base = getRecompensaEmXP();
        boolean temIA = dev.getHabilidades().stream().anyMatch(h ->
            h.equalsIgnoreCase("IA") || 
            h.equalsIgnoreCase("Machine Learning") ||
            h.equalsIgnoreCase("Data Science"));

        if (temIA) {
            base += 15;
        } else {
            base -= 10;
        }
        return base;
    }
}
