public abstract class Projeto {
    private String nome;
    private int dificuldade;
    private int recompensaEmXP;
    private TipoProjeto tipoDeProjeto;

    public Projeto(String nome, int dificuldade, int recompensaEmXP, TipoProjeto tipo) {
        this.nome = nome;
        this.dificuldade = dificuldade;
        this.recompensaEmXP = recompensaEmXP;
        this.tipoDeProjeto = tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public int getRecompensaEmXP() {
        return recompensaEmXP;
    }

    public TipoProjeto getTipoDeProjeto() {
        return tipoDeProjeto;
    }

    public void concluirProjeto(Desenvolvedor dev) {
        System.out.println("Concluindo projeto: " + nome 
            + " (tipo: " + tipoDeProjeto + ", dif: " + dificuldade + ")");

        int xpFinal = calcularRecompensa(dev);
        if (xpFinal > 0) {
            dev.ganharXP(xpFinal);
            System.out.println(Color.GREEN + "✔ Projeto concluído com sucesso!" + Color.RESET);
            System.out.println(Color.CYAN + "+" + xpFinal + " XP ganhos!" + Color.RESET);

        } else {
            int perda = Math.abs(xpFinal);
            dev.perderXP(perda);
            System.out.println("Projeto falhou, XP perdido: " + perda);
        }
    }

    protected abstract int calcularRecompensa(Desenvolvedor dev);
}
