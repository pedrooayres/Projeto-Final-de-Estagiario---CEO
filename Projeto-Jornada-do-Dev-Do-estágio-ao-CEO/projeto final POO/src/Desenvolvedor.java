import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Desenvolvedor {
    private String nome;
    private NivelCargo nivel;
    private int xp;
    private List<String> habilidades; // assuntos estudados (texto livre)
    private boolean modoDevNoturno;
    private int energia;

    // ---- SISTEMA DE SKILLS ----
    private int xpParaSkill = 0;           // XP acumulado para converter em pontos
    private int pontosHabilidade = 0;      // pontos disponíveis para gastar
    private int pontosGastos = 0;          // total de pontos já investidos
    private Map<String, Integer> skills;   // "java", "web", "mobile", "ia", "devops"

    // Desafio de promoção pendente (se existir)
    private Desafio desafioPromocao;

    public Desenvolvedor(String nome) {
        this.nome = nome;
        this.nivel = NivelCargo.ESTAGIARIO;
        this.xp = 0;
        this.habilidades = new ArrayList<>();
        this.modoDevNoturno = false;
        this.energia = getEnergiaMaxima();
        inicializarSkills();
        this.desafioPromocao = null;
    }

    private void inicializarSkills() {
        skills = new HashMap<>();
        skills.put("java", 0);
        skills.put("web", 0);
        skills.put("mobile", 0);
        skills.put("ia", 0);
        skills.put("devops", 0);
    }

    public String getNome() {
        return nome;
    }

    public NivelCargo getNivel() {
        return nivel;
    }

    public int getXp() {
        return xp;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = Math.min(energia, getEnergiaMaxima());
    }

    public int getPontosHabilidade() {
        return pontosHabilidade;
    }

    public int getPontosGastos() {
        return pontosGastos;
    }

    public Map<String,Integer> getSkills() {
        return skills;
    }

    public Desafio getDesafioPromocao() {
        return desafioPromocao;
    }

    public void setDesafioPromocao(Desafio desafioPromocao) {
        this.desafioPromocao = desafioPromocao;
    }

    public void adicionarHabilidade(String habilidade) {
        habilidades.add(habilidade);
        System.out.println("Nova habilidade aprendida: " + habilidade + "!");
    }

    public void trabalharEmProjeto(Projeto projeto) throws ProjetoInexistenteException {
        if (projeto == null) {
            throw new ProjetoInexistenteException("Projeto informado é nulo ou inexistente.");
        }
        if (!temEnergia(15)) {
            System.out.println(nome + " nao tem energia suficiente para trabalhar no projeto! Energia atual: " + energia);
            return;
        }
        consumirEnergia(15);
        System.out.println("\n" + nome + " está trabalhando no projeto: " + projeto.getNome());
        projeto.concluirProjeto(this);
    }

    public void estudar(String habilidade) {
        habilidade = habilidade.toLowerCase();

        if (!temEnergia(10)) {
            System.out.println(nome + " está cansado demais para estudar! Energia atual: " + energia);
            return;
        }

        if (habilidades.contains(habilidade)) {
            System.out.println(nome + " já estudou " + habilidade + " antes! Revisao nao rende muito XP...");
            ganharXP(1);
            consumirEnergia(5);
            return;
        }

        System.out.println(nome + " está estudando " + habilidade + "...");
        adicionarHabilidade(habilidade);
        int xpGanho = calcularXP(habilidade);
        ganharXP(xpGanho);
        consumirEnergia(10);
        System.out.println("Ganhou " + xpGanho + " XP!");
    }

    public void subirDeCargo() throws SemXPException {
        int xpNecessario = xpNecessarioParaProximoCargo();
        if (xpNecessario < 0) {
            System.out.println("Você já é CEO. Não há mais cargos acima!");
            return;
        }
        if (this.xp < xpNecessario) {
            throw new SemXPException("XP insuficiente para subir de cargo. Necessário: " 
                    + xpNecessario + ", atual: " + xp);
        }

        switch (this.nivel) {
            case ESTAGIARIO:
                this.nivel = NivelCargo.JUNIOR;
                break;
            case JUNIOR:
                this.nivel = NivelCargo.PLENO;
                break;
            case PLENO:
                this.nivel = NivelCargo.SENIOR;
                break;
            case SENIOR:
                this.nivel = NivelCargo.CEO;
                break;
            case CEO:
                break;
        }

        System.out.println("PARABÉNS! " + nome + " subiu para o cargo: " + this.nivel);
    }

    // Agora é public para a Carreira consultar
    public int xpNecessarioParaProximoCargo() {
        switch (this.nivel) {
            case ESTAGIARIO: return 50;
            case JUNIOR:     return 120;
            case PLENO:      return 200;
            case SENIOR:     return 350;
            case CEO:        return -1;
            default:         return -1;
        }
    }

    public int getEnergiaMaxima() {
        switch (nivel) {
            case ESTAGIARIO: return 60;
            case JUNIOR:     return 80;
            case PLENO:      return 100;
            case SENIOR:     return 120;
            case CEO:        return 150;
            default:         return 100;
        }
    }

    public void ganharXP(int qtd) {
        if (modoDevNoturno) qtd += 5;

        if (energia < 20)
            qtd -= 3;
        else if (energia > 90)
            qtd += 2;

        if (qtd < 0) qtd = 0;

        xp += qtd;
        adicionarXPParaSkill(qtd);

        System.out.println(nome + " ganhou " + qtd + " XP! Energia atual: " + energia);
    }

    private void adicionarXPParaSkill(int quantidade) {
        xpParaSkill += quantidade;
        while (xpParaSkill >= 5) {
            xpParaSkill -= 5;
            pontosHabilidade++;
        }
    }

    public void perderXP(int qtd) {
        this.xp -= qtd;
        if (this.xp < 0) this.xp = 0;
        System.out.println(nome + " perdeu " + qtd + " XP. Total: " + xp);
    }

    public boolean isModoDevNoturno() {
        return modoDevNoturno;
    }

    public void ativarDevNoturno() {
        this.modoDevNoturno = true;
        System.out.println(">> Modo Dev Noturno ATIVADO! Você ganha bônus de XP.");
    }

    public void desativarDevNoturno() {
        this.modoDevNoturno = false;
        System.out.println(">> Modo Dev Noturno DESATIVADO.");
    }

    // STATUS COMPLETO
    public void mostrarStatus() {
        System.out.println(Color.YELLOW + "\n======= STATUS COMPLETO DO DESENVOLVEDOR =======" + Color.RESET);

        System.out.println("Nome: " + Color.CYAN + nome + Color.RESET);
        System.out.println("Cargo: " + Color.GREEN + nivel + Color.RESET);
        System.out.println("XP: " + Color.PURPLE + xp + Color.RESET);

        // Energia colorida
        String energiaColorida;
        double perc = (double) energia / getEnergiaMaxima();

        if (perc >= 0.70) energiaColorida = Color.GREEN;       // alta
        else if (perc >= 0.30) energiaColorida = Color.YELLOW; // média
        else energiaColorida = Color.RED;                      // baixa

        System.out.println("Energia: " + energiaColorida + energia + "/" + getEnergiaMaxima() + Color.RESET);

        // Habilidades estudadas
        System.out.println("Habilidades estudadas: " + Color.CYAN + habilidades + Color.RESET);

        // Sistema de skills
        System.out.println("Pontos de Habilidade disponíveis: " + Color.GREEN + pontosHabilidade + Color.RESET);
        System.out.println("Pontos gastos em skills: " + pontosGastos);
        System.out.println("Skills (java/web/mobile/ia/devops): " + Color.PURPLE + skills + Color.RESET);

        if (desafioPromocao != null) {
            System.out.println(Color.PURPLE + "Desafio de promoçao: disponível (use a opçao 7 para tentar)" + Color.RESET);
        } else {
            System.out.println("Desafio de promoçao: nenhum disponível no momento.");
        }

        System.out.println(Color.YELLOW + "============================================" + Color.RESET);
    }

    public int calcularXP(String habilidade) {
        habilidade = habilidade.toLowerCase();
        if (habilidade.contains("java") || habilidade.contains("kotlin"))
            return 12;
        if (habilidade.contains("html") || habilidade.contains("css"))
            return 5;
        if (habilidade.contains("aws") || habilidade.contains("docker") || habilidade.contains("devops"))
            return 15;
        if (habilidade.contains("ia") || habilidade.contains("machine learning"))
            return 20;
        return 5;
    }

    public void consumirEnergia(int valor) {
        energia -= valor;
        if (energia < 0) energia = 0;
    }

    public boolean temEnergia(int qtd) {
        return energia >= qtd;
    }

    public void descansar() {
        energia = Math.min(energia + 20, getEnergiaMaxima());
        System.out.println(nome + " descansou e recuperou energia! Energia atual: " + energia);
    }

    public void tomarCafe() {
        energia = Math.min(energia + 10, getEnergiaMaxima());
        System.out.println(nome + " tomou café ☕ Energia atual: " + energia);
    }

    public void verificarEstado() {
        if (energia <= 5) {
            System.out.println("ALERTA! " + nome + " está EXAUSTO! Produtividade cai drasticamente.");
            perderXP(3);
        }
    }

    // ---- GASTAR PONTO DE SKILL ----
    public boolean gastarPonto(String habilidade) {
        habilidade = habilidade.toLowerCase();

        if (!skills.containsKey(habilidade)) {
            return false;
        }

        if (pontosHabilidade <= 0) {
            return false;
        }

        pontosHabilidade--;
        pontosGastos++;

        skills.put(habilidade, skills.get(habilidade) + 1);

        return true;
    }
}
