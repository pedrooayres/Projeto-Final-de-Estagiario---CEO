import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Carreira {
    private List<Projeto> projetosDisponiveis;
    private List<Desafio> desafiosPendentes;
    private Desenvolvedor desenvolvedor;
    private List<Desenvolvedor> ranking;

    private Random random;

    public Carreira(Desenvolvedor desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
        this.projetosDisponiveis = new ArrayList<>();
        this.desafiosPendentes = new ArrayList<>();
        this.ranking = new ArrayList<>();
        this.random = new Random();

        inicializarProjetosBase();
        inicializarDesafiosBase();
    }

    public Desenvolvedor getDesenvolvedor() {
        return desenvolvedor;
    }

    private void inicializarProjetosBase() {
        projetosDisponiveis.add(new ProjetoWeb("Landing Page", 5, 20));
        projetosDisponiveis.add(new ProjetoWeb("Portfólio Pessoal", 4, 18));
        projetosDisponiveis.add(new ProjetoWeb("Sistema de Blog", 8, 30));
        projetosDisponiveis.add(new ProjetoWeb("E-commerce Simples", 12, 45));
        projetosDisponiveis.add(new ProjetoWeb("Dashboard Administrativo", 14, 55));
        projetosDisponiveis.add(new ProjetoWeb("Clone do Twitter (Front-end)", 10, 40));
        projetosDisponiveis.add(new ProjetoMobile("App de Login", 10, 35));
        projetosDisponiveis.add(new ProjetoMobile("App de Lista de Tarefas", 6, 25));
        projetosDisponiveis.add(new ProjetoMobile("App de Notícias", 9, 33));
        projetosDisponiveis.add(new ProjetoMobile("App de Hábitos", 11, 40));
        projetosDisponiveis.add(new ProjetoMobile("App de Receitas", 7, 28));
        projetosDisponiveis.add(new ProjetoMobile("App de Finanças Pessoais", 13, 48));
        projetosDisponiveis.add(new ProjetoIA("Recomendador de Cursos", 15, 50));
        projetosDisponiveis.add(new ProjetoIA("Classificador de Imagens", 18, 60));
        projetosDisponiveis.add(new ProjetoIA("Chatbot de Atendimento", 16, 55));
        projetosDisponiveis.add(new ProjetoIA("Detector de Spam", 12, 42));
        projetosDisponiveis.add(new ProjetoIA("Analisador de Sentimentos", 15, 50));
        projetosDisponiveis.add(new ProjetoIA("Previsor de Séries Temporais", 20, 70));

    }

    private void inicializarDesafiosBase() {
        desafiosPendentes.add(new Desafio("Bug crítico em produção", -15, -2000.0));
        desafiosPendentes.add(new Desafio("Servidor caiu", -10, -500.0));
        desafiosPendentes.add(new Desafio("Burnout", -20, 0.0));
        desafiosPendentes.add(new Desafio("Cliente mudou requisitos", -12, -800.0));
        desafiosPendentes.add(new Desafio("Falha de segurança", -18, -1500.0));
        desafiosPendentes.add(new Desafio("Perda de dados", -25, -3000.0));
        desafiosPendentes.add(new Desafio("Conflito na equipe", -8, -200.0));
        desafiosPendentes.add(new Desafio("Retrabalho inesperado", -10, -600.0));
        desafiosPendentes.add(new Desafio("Prazo apertado", -7, -300.0));
        desafiosPendentes.add(new Desafio("Ambiente de testes indisponível", -6, -150.0));
        desafiosPendentes.add(new Desafio("Dívida técnica acumulada", -14, -700.0));
        desafiosPendentes.add(new Desafio("Feedback negativo do cliente", -11, -250.0));
        desafiosPendentes.add(new Desafio("Erro de comunicação interna", -5, -100.0));
        desafiosPendentes.add(new Desafio("Escopo aumentou sem aviso", -13, -900.0));
        desafiosPendentes.add(new Desafio("Dependência externa indisponível", -8, -350.0));

    }

    public void iniciarJornada() {
       System.out.println(Color.PURPLE + "\n>>> A JORNADA COMEÇA! <<<\n" + Color.RESET);
       System.out.println(Color.GREEN + "\n>>> Bem Vindo ! <<<\n" + Color.RESET+desenvolvedor.getNome());
       System.out.println(Color.GREEN + "\n>>> Cargo Atual : <<<\n" + Color.RESET+desenvolvedor.getNivel());
       System.out.println(Color.GREEN + "\n>>> XP Atual : <<<\n" + Color.RESET+desenvolvedor.getXp());
    }

    public Projeto gerarProjetoAleatorio() {
        if (projetosDisponiveis.isEmpty()) {
            return null;
        }
        int index = random.nextInt(projetosDisponiveis.size());
        return projetosDisponiveis.get(index);
    }

    public Desafio gerarDesafioAleatorio() {
        Utils.slowPrint(Color.RED + "⚠ BUG CRÍTICO DETECTADO...\n" + Color.RESET, 40);
        if (desafiosPendentes.isEmpty()) {
            return null;
        }
        int index = random.nextInt(desafiosPendentes.size());
        return desafiosPendentes.get(index);
    }

    public void verificarEvolucao() {
        try {
            desenvolvedor.subirDeCargo();
             Utils.slowPrint(Color.GREEN + "\n🎉 PROMOÇÃO! Você subiu de cargo! 🎉\n" + Color.RESET, 15);
        } catch (SemXPException e) {
             Utils.slowPrint(Color.RED + "\n Jogador não foi promovido - XP insuficiente\n" + Color.RESET, 15);
        }
    }

    public void executarTurnoTexto() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
           System.out.println(Color.CYAN + "╔═════════════════════════════════════╗" + Color.RESET);
           System.out.println(Color.BLUE + "║           MENU PRINCIPAL            ║" + Color.RESET);
           System.out.println(Color.CYAN + "╠═════════════════════════════════════╣" + Color.RESET);
           System.out.println("║ " + Color.GREEN + "1" + Color.RESET + " - Trabalhar em Projeto Aleatorio  ║");
           System.out.println("║ " + Color.GREEN + "2" + Color.RESET + " - Estudar                         ║");
           System.out.println("║ "+Color.GREEN + "3" + Color.RESET + "- Enfrentar Desafio Aleatorio      ║");
           System.out.println("║ " + Color.GREEN + "4" + Color.RESET + " - Ver Status                      ║");
           System.out.println("║ " + Color.YELLOW + "5" + Color.RESET + " - Ativar Modo Dev Noturno         ║");
           System.out.println("║ " + Color.RED + "0" + Color.RESET + " - Sair                            ║");
           System.out.println(Color.CYAN + "╚═════════════════════════════════════╝" + Color.RESET);
            int opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    Projeto p = gerarProjetoAleatorio();
                    if (p == null) {
                        System.out.println("Nenhum projeto disponível.");
                    } else {
                        try {
                            desenvolvedor.trabalharEmProjeto(p);
                        } catch (ProjetoInexistenteException e) {
                            System.out.println("Erro ao trabalhar em projeto: " + e.getMessage());
                        }
                    }
                    break;
                case 2:
                    System.out.print("Digite uma habilidade para estudar: ");
                    String hab = sc.nextLine().trim();
                     if (hab.isEmpty()) {
                         System.out.println("Você precisa digitar uma habilidade válida!");
                        } else {
                            desenvolvedor.estudar(hab);
                         }
                         break;
                case 3:
                    Desafio d = gerarDesafioAleatorio();
                    if (d == null) {
                        System.out.println("Nenhum desafio no momento.");
                    } else {
                        try {
                            d.aplicarConsequencia(desenvolvedor);
                        } catch (DesafioInvalidoException e) {
                            System.out.println("Erro ao aplicar desafio: " + e.getMessage());
                        }
                    }
                    break;
                case 4:
                    verificarEvolucao();
                    break;
                case 5:
                    System.out.print("Digite a senha secreta: ");
                    String senha = sc.nextLine();
                    ativarModoDevNoturnoSeSenhaCorreta(senha);
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Encerrando jornada. XP final: " + desenvolvedor.getXp() 
                            + " | Cargo: " + desenvolvedor.getNivel());
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public void registrarNoRanking(Desenvolvedor dev) {
        ranking.add(dev);
        // você pode ordenar depois por XP
    }

    public void ativarModoDevNoturnoSeSenhaCorreta(String input) {
        if (input != null && input.equalsIgnoreCase("CAFE")) {
        desenvolvedor.ativarDevNoturno();
        loadingBar(Color.PURPLE + "Ativando MODO DEV NOTURNO..." + Color.RESET, 25, 40);
    } else {
        System.out.println("Nada aconteceu...");
     }
    }
    public static void loadingBar(String mensagem, int steps, int delay) {
    System.out.println(mensagem);

    for (int i = 0; i <= steps; i++) {
        String bar = "[" + "=".repeat(i) + " ".repeat(steps - i) + "]";
        System.out.print("\r" + bar);
        try { Thread.sleep(delay); } catch (Exception e) {}
    }
    System.out.println();
    }
}