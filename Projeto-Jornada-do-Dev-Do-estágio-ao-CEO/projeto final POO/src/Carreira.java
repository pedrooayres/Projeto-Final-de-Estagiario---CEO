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

    // ---- Verifica XP e informa situação de promoção (sem mostrar o desafio) ----
    public void verificarEvolucao() {
        int xpNecessario = desenvolvedor.xpNecessarioParaProximoCargo();

        if (xpNecessario < 0) {
            System.out.println("\nVocê já está no cargo máximo (CEO). Nao há mais promoções.");
            return;
        }

        System.out.println("\n--- STATUS DE PROMOÇAO ---");
        System.out.println("Cargo atual: " + desenvolvedor.getNivel());
        System.out.println("XP atual: " + desenvolvedor.getXp());
        System.out.println("XP necessário para próximo cargo: " + xpNecessario);

        if (desenvolvedor.getXp() < xpNecessario) {
            System.out.println("Situaçao: Ainda nao há uma chance de promoçao disponível. Continue ganhando XP!");
            return;
        }

        // Já tem XP suficiente
        if (desenvolvedor.getDesafioPromocao() == null) {
            // Gera desafio de promoção silenciosamente (sem mostrar detalhes)
            Desafio d = gerarDesafioPromocao(desenvolvedor.getNivel());
            desenvolvedor.setDesafioPromocao(d);
        }

        System.out.println("Situaçao: Uma oportunidade de promoçao está disponível!");
        System.out.println("Use a opçao '7 - Tentar promoçao' para enfrentar o desafio.");
    }

    // Gera um desafio de promoção aleatório de acordo com o cargo atual
    public Desafio gerarDesafioPromocao(NivelCargo nivel) {
        switch (nivel) {
            case ESTAGIARIO:
                // desafios mais básicos de WEB
                return new Desafio("Criar página HTML/CSS responsiva", 2, TipoProjeto.WEB);
            case JUNIOR:
                // desafios intermediários WEB/MOBILE
                return Desafio.gerarDesafioPorTipo(random.nextBoolean() ? TipoProjeto.WEB : TipoProjeto.MOBILE);
            case PLENO:
                // desafios mais complexos MOBILE / IA
                return Desafio.gerarDesafioPorTipo(random.nextBoolean() ? TipoProjeto.MOBILE : TipoProjeto.IA);
            case SENIOR:
                // desafios avançados IA
                return new Desafio("Projetar modelo de IA para previsao avançada", 7, TipoProjeto.IA);
            case CEO:
            default:
                return null;
        }
    }

    // ---- Desafio normal da empresa (ganha XP se passar) ----
    public void enfrentarDesafioEmpresa() {
        if (!desenvolvedor.temEnergia(10)) {
            System.out.println("Você está cansado demais para enfrentar um desafio agora. Energia: " + desenvolvedor.getEnergia());
            return;
        }

        Desafio d = Desafio.gerarDesafioAleatorio();

        System.out.println(Color.RED + "\n⚠ DESAFIO NA EMPRESA! ⚠" + Color.RESET);
        System.out.println("Desafio: " + d.getNome());
        System.out.println("Tipo: " + d.getTipo() + " | Dificuldade: " + d.getDificuldade());

        int chance = d.calcularChanceFinal(desenvolvedor);
        System.out.println(" Chance de sucesso (com base nas suas skills): " + chance + "%");

        int rolagem = random.nextInt(100) + 1;
        System.out.println(" Você rolou: " + rolagem + " (1-100)");

        if (rolagem <= chance) {
            System.out.println(Color.GREEN + " Sucesso! Você concluiu o desafio!" + Color.RESET);
            int xpGanho = 10 + random.nextInt(21); // 10 a 30 XP
            desenvolvedor.ganharXP(xpGanho);
            System.out.println("Você ganhou " + xpGanho + " XP no desafio!");
        } else {
            System.out.println(Color.RED + " Falha! Você nao conseguiu finalizar o desafio desta vez." + Color.RESET);
            System.out.println("Talvez investir mais pontos em habilidades relacionadas ajude...");
        }

        desenvolvedor.consumirEnergia(10);
    }

    // ---- Tentar concluir desafio de promoção ----
    public void enfrentarDesafioPromocao() {
        Desafio d = desenvolvedor.getDesafioPromocao();
        if (d == null) {
            System.out.println("Você nao possui nenhum desafio de promoção pendente.");
            return;
        }

        if (!desenvolvedor.temEnergia(10)) {
            System.out.println("Você está cansado demais para tentar a promoção agora. Energia: " + desenvolvedor.getEnergia());
            return;
        }

        System.out.println(Color.PURPLE + "\n🏆 TENTANDO PROMOÇÃO DE CARGO! 🏆" + Color.RESET);
        System.out.println("Desafio: " + d.getNome());
        System.out.println("Tipo: " + d.getTipo() + " | Dificuldade: " + d.getDificuldade());

        int chance = d.calcularChanceFinal(desenvolvedor);
        System.out.println(" Chance de sucesso na promoção (skills): " + chance + "%");

        int rolagem = random.nextInt(100) + 1;
        System.out.println(" Você rolou: " + rolagem + " (1-100)");

        if (rolagem <= chance) {
            System.out.println(Color.GREEN + "\n Você concluiu o desafio de promoção!" + Color.RESET);
            desenvolvedor.setDesafioPromocao(null);
            try {
                desenvolvedor.subirDeCargo();
                System.out.println(Color.GREEN + "🎉 PROMOÇÃO CONCLUÍDA COM SUCESSO! 🎉" + Color.RESET);
            } catch (SemXPException e) {
                System.out.println("Erro inesperado ao promover: " + e.getMessage());
            }
        } else {
            System.out.println(Color.RED + " Você falhou no desafio de promoção. Tente novamente depois!" + Color.RESET);
        }

        desenvolvedor.consumirEnergia(10);
    }

    // ---- Investir ponto em habilidade (java/web/mobile/ia/devops) ----
    public void investirPonto(String habilidade) {
        boolean ok = desenvolvedor.gastarPonto(habilidade);

        if (ok) {
            System.out.println("\n Ponto investido em " + habilidade.toUpperCase() + "!");
        } else {
            System.out.println("\n Nso foi possível investir ponto. "
                    + "Ou a habilidade nao existe, ou você nao tem pontos disponíveis.");
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
        if (desenvolvedor.getDesafioPromocao() != null) {
            System.out.println("Desafio de promoçao: disponível (use a opçao 7 para tentar).");
        } else {
            System.out.println("Desafio de promoçao: nenhum disponível.");
        }
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
            System.out.println("│ " + Color.GREEN + "2" + Color.RESET + " - Estudar (Custa 10 de energia)        │");
            System.out.println("│ " + Color.GREEN + "3" + Color.RESET + " - Enfrentar Desafio da Empresa (10 EN) │");
            System.out.println("│ " + Color.GREEN + "4" + Color.RESET + " - Ver Status / Situaçao de Promoçao    │");
            System.out.println("│ " + Color.YELLOW + "5" + Color.RESET + " - Ativar Modo Dev Noturno              │");
            System.out.println("│ " + Color.GREEN + "6" + Color.RESET + " - Investir Ponto em Habilidade         │");
            System.out.println("│ " + Color.GREEN + "7" + Color.RESET + " - Tentar Promoçao                      │");
            System.out.println("│ " + Color.GREEN + "8" + Color.RESET + " - Descansar (+20 Energia)              │");
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
                    if (!desenvolvedor.temEnergia(10)) {
                        System.out.println(Color.RED + "Energia insuficiente! Você precisa de 10." + Color.RESET);
                        break;
                    }
                    System.out.print("Digite uma habilidade para estudar: ");
                    String hab = sc.nextLine().trim();
                    if (hab.isEmpty()) {
                        System.out.println("Você precisa digitar uma habilidade válida!");
                    } else {
                        desenvolvedor.estudar(hab); // já consome energia internamente
                    }
                    break;

                case 3:
                    enfrentarDesafioEmpresa();
                    break;

                case 4:
                    desenvolvedor.mostrarStatus();
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
                    enfrentarDesafioPromocao();
                    break;

                case 8:
                    desenvolvedor.descansar();
                    break;

                case 0:
                    continuar = false;
                    System.out.println("Encerrando jornada. XP final: " + desenvolvedor.getXp() 
                            + " | Cargo: " + desenvolvedor.getNivel());
                    break;
                default:
                    System.out.println("Opçao inválida.");
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
}
