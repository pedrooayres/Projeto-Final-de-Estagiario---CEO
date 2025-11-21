import java.util.Random;

public class ProjetoMobileDesafios {

    private static final String[] NOMES_DESAFIOS = {
        "Resolver crash ao abrir app",
        "Implementar lista de tarefas offline",
        "Melhorar performance de scroll",
        "Adicionar login com biometria"
    };

    private static final int[] DIFICULDADES = {
        3, 4, 5, 6
    };

    private static final Random random = new Random();

    public static Desafio gerarDesafioAleatorio() {
        int idx = random.nextInt(NOMES_DESAFIOS.length);
        String nome = NOMES_DESAFIOS[idx];
        int dificuldade = DIFICULDADES[idx];
        return new Desafio(nome, dificuldade, TipoProjeto.MOBILE);
    }
}
