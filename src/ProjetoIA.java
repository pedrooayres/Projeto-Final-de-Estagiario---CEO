public class ProjetoIA extends Projeto {

    public ProjetoIA(String nome, int dificuldade, int recompensaEmXP) {
        super(nome, dificuldade, recompensaEmXP, TipoProjeto.IA);
    }

    @Override
    protected int calcularRecompensa(Desenvolvedor dev) {
        int base = getRecompensaEmXP();
        // Se não tiver IA como habilidade, pode até resultar em prejuízo
        boolean temIA = dev.getHabilidades().stream().anyMatch(h ->
            h.equalsIgnoreCase("IA") || 
            h.equalsIgnoreCase("Machine Learning") ||
            h.equalsIgnoreCase("Data Science"));

        if (temIA) {
            base += 15;
        } else {
            base -= 10; // tomou nabo no projeto de IA
        }
        return base;
    }
}