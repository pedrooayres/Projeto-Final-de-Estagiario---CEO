public class Desafio {
    private String descricao;
    private int impactoEmXP;
    private double impactoFinanceiro;

    public Desafio(String descricao, int impactoEmXP, double impactoFinanceiro) {
        this.descricao = descricao;
        this.impactoEmXP = impactoEmXP;
        this.impactoFinanceiro = impactoFinanceiro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getImpactoEmXP() {
        return impactoEmXP;
    }

    public void setImpactoEmXP(int impactoEmXP) {
        this.impactoEmXP = impactoEmXP;
    }

    public double getImpactoFinanceiro() {
        return impactoFinanceiro;
    }

    public void setImpactoFinanceiro(double impactoFinanceiro) {
        this.impactoFinanceiro = impactoFinanceiro;
    }

    public void aplicarConsequencia(Desenvolvedor alvo) throws DesafioInvalidoException {
       System.out.println(Color.RED + "✖ DESAFIO ENCONTRADO: " + descricao + Color.RESET);
       System.out.println(Color.YELLOW + "Impacto em XP: " + impactoEmXP + Color.RESET);
       System.out.println(Color.YELLOW + "Impacto financeiro: " + impactoFinanceiro + Color.RESET);

    }
}