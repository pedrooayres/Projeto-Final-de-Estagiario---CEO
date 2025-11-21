public class ProjetoWeb extends Projeto {

    public ProjetoWeb(String nome, int dificuldade, int recompensaEmXP) {
        super(nome, dificuldade, recompensaEmXP, TipoProjeto.WEB);
    }

    @Override
    protected int calcularRecompensa(Desenvolvedor dev) {
        int base = getRecompensaEmXP();
        boolean temHabilidadeWeb = dev.getHabilidades().stream().anyMatch(h -> 
            h.equalsIgnoreCase("HTML") || 
            h.equalsIgnoreCase("CSS") || 
            h.equalsIgnoreCase("JavaScript"));
        if (temHabilidadeWeb) {
            base += 5;
        }
        return base;
    }
}
