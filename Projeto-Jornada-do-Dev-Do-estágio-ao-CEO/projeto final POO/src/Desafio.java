import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Desafio {
    private String nome;
    private int dificuldade; // 1 (fácil) a 10 (difícil)
    private TipoProjeto tipo;
    private Map<String, Double> pesosHabilidades;
    private static final Random random = new Random();

    public Desafio(String nome, int dificuldade, TipoProjeto tipo) {
        this.nome = nome;
        this.dificuldade = dificuldade;
        this.tipo = tipo;
        this.pesosHabilidades = new HashMap<>();
        configurarPesosPorTipo();
    }

    private void configurarPesosPorTipo() {
        switch (tipo) {
            case WEB:
                pesosHabilidades.put("web", 1.0);
                pesosHabilidades.put("java", 0.5);
                break;
            case MOBILE:
                pesosHabilidades.put("mobile", 1.0);
                pesosHabilidades.put("java", 0.5);
                break;
            case IA:
                pesosHabilidades.put("ia", 1.0);
                pesosHabilidades.put("java", 0.5);
                break;
            default:
                pesosHabilidades.put("java", 0.5);
                pesosHabilidades.put("web", 0.5);
                pesosHabilidades.put("mobile", 0.5);
                pesosHabilidades.put("ia", 0.5);
                break;
        }
    }

    public String getNome() {
        return nome;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public TipoProjeto getTipo() {
        return tipo;
    }

    // ---- Cálculo de bônus a partir do mapa de skills do Desenvolvedor ----
    public double calcularBonusPercentual(Desenvolvedor dev) {
        double bonus = 0.0;
        Map<String, Integer> skills = dev.getSkills();

        for (Map.Entry<String, Double> entry : pesosHabilidades.entrySet()) {
            String habilidade = entry.getKey();
            double peso = entry.getValue();
            int pontos = skills.getOrDefault(habilidade, 0);
            bonus += pontos * peso; // ex: 3 pontos * 1.0 → +3% de chance
        }
        return bonus;
    }

    public int calcularChanceFinal(Desenvolvedor dev) {
        int chanceBase = 50 - (dificuldade * 5);  // ex: dificuldade 5 → 25% base
        double bonus = calcularBonusPercentual(dev);
        int chanceFinal = (int) Math.round(chanceBase + bonus);

        if (chanceFinal < 5) chanceFinal = 5;
        if (chanceFinal > 95) chanceFinal = 95;

        return chanceFinal;
    }

    // ---- Gerador de desafio aleatório geral (empresa) ----

    public static Desafio gerarDesafioAleatorio() {
        TipoProjeto[] tipos = { TipoProjeto.WEB, TipoProjeto.MOBILE, TipoProjeto.IA };
        TipoProjeto tipoEscolhido = tipos[random.nextInt(tipos.length)];
        return gerarDesafioPorTipo(tipoEscolhido);
    }

    public static Desafio gerarDesafioPorTipo(TipoProjeto tipo) {
        switch (tipo) {
            case WEB:
                return ProjetoWebDesafios.gerarDesafioAleatorio();
            case MOBILE:
                return ProjetoMobileDesafios.gerarDesafioAleatorio();
            case IA:
                return ProjetoIADesafios.gerarDesafioAleatorio();
            default:
                return ProjetoWebDesafios.gerarDesafioAleatorio();
        }
    }

    @Override
    public String toString() {
        return nome + " (Tipo: " + tipo + ", Dificuldade: " + dificuldade + ")";
    }
}
