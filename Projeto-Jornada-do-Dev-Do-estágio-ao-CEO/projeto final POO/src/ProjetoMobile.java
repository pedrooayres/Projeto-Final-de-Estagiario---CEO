public class ProjetoMobile extends Projeto {

    public ProjetoMobile(String nome, int dificuldade, int recompensaEmXP) {
        super(nome, dificuldade, recompensaEmXP, TipoProjeto.MOBILE);
    }

    @Override
    protected int calcularRecompensa(Desenvolvedor dev) {
        int base = getRecompensaEmXP();
        boolean temMobile = dev.getHabilidades().stream().anyMatch(h ->
            h.equalsIgnoreCase("Android") || 
            h.equalsIgnoreCase("Kotlin") || 
            h.equalsIgnoreCase("Flutter"));
        if (temMobile) {
            base += 10;
        }
        return base;
    }
}
