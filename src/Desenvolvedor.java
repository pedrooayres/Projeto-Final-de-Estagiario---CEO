import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class Desenvolvedor {
    private String nome;
    private NivelCargo nivel;
    private int xp;
    private List<String> habilidades;
    private boolean modoDevNoturno;
    private int energia;
    

    public Desenvolvedor(String nome) {
        this.nome = nome;
        this.nivel = NivelCargo.ESTAGIARIO;
        this.xp = 0;
        this.habilidades = new ArrayList<>();
        this.modoDevNoturno = false;
        this.energia = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public NivelCargo getNivel() {
        return nivel;
    }

    public void setNivel(NivelCargo nivel) {
        this.nivel = nivel;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public void adicionarHabilidade(String habilidade) {
       habilidades.add(habilidade);
       System.out.println("Nova habilidade aprendida: " + habilidade + "!");
}

    public void trabalharEmProjeto(Projeto projeto) throws ProjetoInexistenteException {
        if (projeto == null) {
            throw new ProjetoInexistenteException("Projeto informado é nulo ou inexistente.");
        }
        System.out.println("\n" + nome + " está trabalhando no projeto: " + projeto.getNome());
        projeto.concluirProjeto(this);
    }

    public void estudar(String habilidade) {
    habilidade = habilidade.toLowerCase();

    // Verifica primeiro se já existe
    if (habilidades.contains(habilidade)) {
        System.out.println(nome + " já estudou " + habilidade + " antes! Revisão não rende muito XP...");
        ganharXP(1); // XP reduzido por revisão
        return;      // impede continuar
    }

    // Caso seja nova habilidade:
    System.out.println(nome + " está estudando " + habilidade + "...");
    adicionarHabilidade(habilidade);

    int xpGanho = calcularXP(habilidade);
    ganharXP(xpGanho);

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
                // Já tratado no início
                break;
        }

        System.out.println("PARABÉNS! " + nome + " subiu para o cargo: " + this.nivel);
    }

    private int xpNecessarioParaProximoCargo() {
        switch (this.nivel) {
            case ESTAGIARIO:
                return 50;
            case JUNIOR:
                return 120;
            case PLENO:
                return 200;
            case SENIOR:
                return 350;
            case CEO:
                return -1; // Não há próximo cargo
            default:
                return -1;
        }
    }

    public void ganharXP(int qtd) {
        if (modoDevNoturno) {
            qtd += 5; // bônus simples para o modo secreto
        }
        this.xp += qtd;
        System.out.println(nome + " ganhou " + qtd + " XP. Total: " + xp);
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
    public void mostrarStatus() {
    System.out.println(Color.YELLOW + "\n======= STATUS DO DESENVOLVEDOR =======" + Color.RESET);
    System.out.println("Nome: " + Color.CYAN + nome + Color.RESET);
    System.out.println("Nível: " + Color.GREEN + nivel + Color.RESET);
    System.out.println("XP: " + Color.PURPLE + xp + Color.RESET);
    System.out.println("Energia: " + Color.BLUE + energia + Color.RESET);
    System.out.println("Habilidades: " + Color.CYAN + habilidades + Color.RESET);
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
}