import java.util.Random;

public class ProjetoWebDesafios {

    private static final String[] NOMES_DESAFIOS = {
        "Corrigir bug em formulário de cadastro",
        "Implementar autenticação com sessão",
        "Otimizar carregamento da landing page",
        "Criar página de portfólio responsiva"
    };

    private static final int[] DIFICULDADES = {
        2, 3, 4, 5
    };

    private static final Random random = new Random();

    public static Desafio gerarDesafioAleatorio() {
        int idx = random.nextInt(NOMES_DESAFIOS.length);
        String nome = NOMES_DESAFIOS[idx];
        int dificuldade = DIFICULDADES[idx];
        return new Desafio(nome, dificuldade, TipoProjeto.WEB);
    }
}
