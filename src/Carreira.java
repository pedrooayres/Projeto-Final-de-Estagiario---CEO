import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Carreira {
    private List<Projeto> projetosDisponiveis;
    private Desenvolvedor desenvolvedor;
    private List<Desenvolvedor> ranking;

    private Random random;

    public Carreira(Desenvolvedor desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
        this.projetosDisponiveis = new ArrayList<>();
        this.ranking = new ArrayList<>();
        this.random = new Random();

        inicializarProjetosBase();
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

    public void iniciarJornada() {
       System.out.println(Color.PURPLE + "\n>>> A JORNADA COMEÇA! <<<\n" + Color.RESET);
       System.out.println(Color.GREEN + "\n>>> Bem-vindo! <<< " + Color.RESET + desenvolvedor.getNome());
       System.out.println(Color.GREEN + "\n>>> Cargo Atual: <<< " + Color.RESET + desenvolvedor.getNivel());
       System.out.println(Color.GREEN + "\n>>> XP Atual: <<< " + Color.RESET + desenvolvedor.getXp());
       System.out.println(Color.GREEN + "\n>>> Energia Atual: <<< " + Color.RESET + desenvolvedor.getEnergia());
    }

    public Projeto gerarProjetoAleatorio() {
        if (projetosDisponiveis.isEmpty()) {
            return null;
        }
        int index = random.nextInt(projetosDisponiveis.size());
        return projetosDisponiveis.get(index);
    }

    public void verificarEvolucao() {
        try {
            desenvolvedor.subirDeCargo();
            Utils.slowPrint(Color.GREEN + "\n🎉 PROMOÇAO! Você subiu de cargo! 🎉\n" + Color.RESET, 15);
        } catch (SemXPException e) {
            Utils.slowPrint(Color.RED + "\nJogador nao foi promovido - XP insuficiente\n" + Color.RESET, 15);
        }
    }

    // ---- Novo: desafio aleatório da empresa usando skills ----
    public void enfrentarDesafioEmpresa() {
        if (!desenvolvedor.temEnergia(5)) {
            System.out.println("Você está cansado demais para enfrentar um desafio agora. Energia: " + desenvolvedor.getEnergia());
            return;
        }

        Desafio d = Desafio.gerarDesafioAleatorio();

        System.out.println(Color.RED + "\n⚠ DESAFIO NA EMPRESA! ⚠" + Color.RESET);
        System.out.println("Desafio: " + d.getNome());
        System.out.println("Tipo: " + d.getTipo() + " | Dificuldade: " + d.getDificuldade());

        int chance = d.calcularChanceFinal(desenvolvedor);
        System.out.println("🎯 Chance de sucesso (com base nas suas skills): " + chance + "%");

        int rolagem = random.nextInt(100) + 1;
        System.out.println("🎲 Você rolou: " + rolagem + " (1-100)");

        if (rolagem <= chance) {
            System.out.println(Color.GREEN + "✅ Sucesso! Você concluiu o desafio!" + Color.RESET);
            int xpGanho = 10 + random.nextInt(21); // 10 a 30 XP
            desenvolvedor.ganharXP(xpGanho);
            System.out.println("Você ganhou " + xpGanho + " XP no desafio!");
        } else {
            System.out.println(Color.RED + "❌ Falha! Você nao conseguiu finalizar o desafio desta vez." + Color.RESET);
            System.out.println("Talvez investir mais pontos em habilidades relacionadas ajude...");
        }

        desenvolvedor.consumirEnergia(5);
    }

    // ---- Investir ponto em habilidade (java/web/mobile/ia/devops) ----
    public void investirPonto(String habilidade) {
        boolean ok = desenvolvedor.gastarPonto(habilidade);

        if (ok) {
            System.out.println("\n🔥 Ponto investido em " + habilidade.toUpperCase() + "!");
        } else {
            System.out.println("\n❌ Não foi possível investir ponto. "
                    + "Ou a habilidade não existe, ou você não tem pontos disponíveis.");
        }

        mostrarStatusResumo();
    }

    public void mostrarStatusResumo() {
        System.out.println(Color.CYAN + "\n===== STATUS DO DESENVOLVEDOR =====" + Color.RESET);
        System.out.println("Nome: " + desenvolvedor.getNome());
        System.out.println("Cargo: " + desenvolvedor.getNivel());
        System.out.println("XP: " + desenvolvedor.getXp());
        System.out.println("Energia: " + desenvolvedor.getEnergia() + "/" + desenvolvedor.getEnergiaMaxima());
        System.out.println("Pontos de Habilidade Disponíveis: " + desenvolvedor.getPontosHabilidade());
        System.out.println("Pontos Gastos em Skills: " + desenvolvedor.getPontosGastos());
        System.out.println("Skills: " + desenvolvedor.getSkills());
        System.out.println("Habilidades estudadas: " + desenvolvedor.getHabilidades());
        System.out.println("===================================\n");
    }

    public void executarTurnoTexto() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println(Color.CYAN + "┌──────────────────────────────────────────┐" + Color.RESET);
            System.out.println(Color.BLUE + "│              MENU PRINCIPAL              │" + Color.RESET);
            System.out.println(Color.CYAN + "├──────────────────────────────────────────┤" + Color.RESET);

            System.out.println("│ " + Color.GREEN + "1" + Color.RESET + " - Trabalhar em Projeto Aleatório       │");
            System.out.println("│ " + Color.GREEN + "2" + Color.RESET + " - Estudar                              │");
            System.out.println("│ " + Color.GREEN + "3" + Color.RESET + " - Enfrentar Desafio da Empresa         │");
            System.out.println("│ " + Color.GREEN + "4" + Color.RESET + " - Ver Status de Promoçao               │");
            System.out.println("│ " + Color.YELLOW + "5" + Color.RESET + " - Ativar Modo Dev Noturno              │");
            System.out.println("│ " + Color.GREEN + "6" + Color.RESET + " - Investir Ponto em Habilidade         │");
            System.out.println("│ " + Color.GREEN + "7" + Color.RESET + " - Mostrar Status Completo              │");
            System.out.println("│ " + Color.RED   + "0" + Color.RESET + " - Sair                                 │");
            System.out.println(Color.CYAN + "└──────────────────────────────────────────┘" + Color.RESET);


            int opcao = sc.nextInt();
            sc.nextLine();

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
                    Desafio d = Desafio.gerarDesafioAleatorio();
                    System.out.println("\n⚠ DESAFIO NA EMPRESA! ⚠");
                    System.out.println("Desafio: " + d.getNome());
                    System.out.println("Tipo: " + d.getTipo() + " | Dificuldade: " + d.getDificuldade());
                    System.out.println("\nComo deseja lidar com o desafio?");
                    System.out.println("1 - Ignorar (XP -50)");
                    System.out.println("2 - Resolver rápido (Impacto normal)");
                    System.out.println("3 - Resolver direito (++XP, menos dinheiro)");
                    System.out.println("4 - Delegar (XP -5)");
                    int escolha = sc.nextInt();
                     d.modificarConsequencia(escolha);
                     enfrentarDesafioEmpresa();// Usa as skills, energia e chance REAL
                     break;
                case 4:
                    verificarEvolucao();
                    break;
                case 5:
                    System.out.print("Digite a senha secreta: ");
                    String senha = sc.nextLine();
                    ativarModoDevNoturnoSeSenhaCorreta(senha);
                    break;

                case 6:
                    System.out.println("\nHabilidades disponíveis para investir ponto: java, web, mobile, ia, devops");
                    System.out.println("Skills atuais: " + desenvolvedor.getSkills());
                    System.out.print("Digite a habilidade para investir ponto: ");
                    String skill = sc.nextLine().trim();
                    investirPonto(skill);
                    break;

                case 7:
                    mostrarStatusResumo();
                    break;

                case 0:
                    continuar = false;
                    exibirFinalDaJornada();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public void registrarNoRanking(Desenvolvedor dev) {
        ranking.add(dev);
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
    public void exibirFinalDaJornada() {
    System.out.println(Color.PURPLE + "\n>>> A JORNADA CHEGOU AO FIM! <<<\n" + Color.RESET);

    System.out.println(Color.GREEN + "\n>>> Resultado Final de " + desenvolvedor.getNome() + " <<<\n" + Color.RESET);
    System.out.println(Color.CYAN + "Cargo Final: " + Color.RESET + desenvolvedor.getNivel());
    System.out.println(Color.CYAN + "XP Final: " + Color.RESET + desenvolvedor.getXp());
    System.out.println(Color.CYAN + "Energia Final: " + Color.RESET + desenvolvedor.getEnergia());
    System.out.println(Color.CYAN + "Saldo Final: R$ " + Color.RESET + desenvolvedor.getSalario());

    System.out.println(Color.YELLOW + "\n>>> Analisando seu destino profissional... <<<\n" + Color.RESET);

    NivelCargo nivel = desenvolvedor.getNivel();
    int xp = desenvolvedor.getXp();
    double dinheiro = desenvolvedor.getSalario();
    int energia = desenvolvedor.getEnergia();
    // 🔥 MULTIPLOS FINAIS
    if (nivel == NivelCargo.CEO && dinheiro >= 50000 && energia > 40) {
        System.out.println(Color.GREEN + "\n🏆 FINAL LENDÁRIO: O CEO MILIONÁRIO!");
        System.out.println("Você se tornou uma lenda no mundo da tecnologia.");
        System.out.println("Empresas disputam você — e você finalmente venceu o jogo da vida!");
        System.out.println(Color.RESET);
    }
    else if (energia <= 0) {
        System.out.println(Color.RED + "\n💀 FINAL RUIM: BURNOUT TOTAL");
        System.out.println("Você se dedicou além dos seus limites... e pagou o preço.");
        System.out.println("Sua saúde mental não aguentou a pressão.");
        System.out.println(Color.RESET);
    }
    else if (nivel == NivelCargo.SENIOR && dinheiro > 20000) {
        System.out.println(Color.BLUE + "\n🌟 FINAL ESPECIAL: DEV SÊNIOR FREELANCER");
        System.out.println("Você abandonou a CLT e agora lucra mais trabalhando menos.");
        System.out.println("Liberdade finalmente alcançada.");
        System.out.println(Color.RESET);
    }
    else if (nivel == NivelCargo.ESTAGIARIO && xp < 40) {
        System.out.println(Color.YELLOW + "\n🤣 FINAL ENGRAÇADO: ESTAGIÁRIO ETERNO");
        System.out.println("Você tentou… mas a vida te deu um 'segura essa PR' eterna.");
        System.out.println("Talvez no próximo jogo…");
        System.out.println(Color.RESET);
    }
    else if (xp >= 300 && nivel != NivelCargo.CEO) {
        System.out.println(Color.CYAN + "\n💡 FINAL SECRETO: O VISIONÁRIO");
        System.out.println("Seu talento é tão grande que você abriu sua própria startup!");
        System.out.println("Agora você cria o próximo grande app do mundo.");
        System.out.println(Color.RESET);
        }
    else {
        System.out.println(Color.RED + "\n📘 FINAL NEUTRO: UMA CARREIRA PROMISSORA");
        System.out.println("Sua jornada está apenas começando. Continue evoluindo!");
        System.out.println(Color.RESET);
        }
    System.out.println(Color.PURPLE + "\n>>> FIM DO JOGO — OBRIGADO POR JOGAR! <<<\n" + Color.RESET);
    }
}
