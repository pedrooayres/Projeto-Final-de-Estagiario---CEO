import java.util.Random;

public class ProjetoIADesafios {

    private static final String[] NOMES_DESAFIOS = {
        "Treinar modelo de recomendação",
        "Ajustar hiperparâmetros de rede neural",
        "Melhorar acurácia de classificação",
        "Implementar chatbot de suporte"
    };

    private static final int[] DIFICULDADES = {
        4, 5, 6, 7
    };

    private static final Random random = new Random();

    public static Desafio gerarDesafioAleatorio() {
        int idx = random.nextInt(NOMES_DESAFIOS.length);
        String nome = NOMES_DESAFIOS[idx];
        int dificuldade = DIFICULDADES[idx];
        return new Desafio(nome, dificuldade, TipoProjeto.IA);
    }
}
