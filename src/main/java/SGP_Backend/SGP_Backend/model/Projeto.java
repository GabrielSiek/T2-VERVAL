package SGP_Backend.SGP_Backend.model;

import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "projetos")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String versao;
    private String unidade;
    private String objetivo;
    private String dataInicio;
    private String dataFim;
    private String prazo;

    @Enumerated(EnumType.STRING)
    private TipoProjeto tipo;

    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    @OneToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @ManyToMany
    @JoinTable(
            name = "projeto_unidades",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "unidade_id"))
    private Set<Unidade> outrasUnidades;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "projeto_id")
    private List<AlinhamentoEstrategico> alinhamentoEstrategicos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "projeto_id")
    private List<Fase> fases;

    // Construtor personalizado
    public Projeto(String nome, String unidade, String dataInicio, String prazo) {
        this.nome = nome;
        this.unidade = unidade;
        this.dataInicio = dataInicio;
        this.prazo = prazo;
    }

    public Projeto(String nome, String objetivo, String unidade, String dataInicio, String dataFim, String prazo) {
        this.nome = nome;
        this.objetivo = objetivo;
        this.unidade = unidade;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.prazo = prazo;
    }

    // Método para adicionar fases padrão
    @PrePersist
    public void addDefaultFases() {
        if (fases == null) {
            fases = new ArrayList<>();
        }
        if (fases.isEmpty()) {
            fases.add(new Fase("Fase 1"));
            fases.add(new Fase("Fase 2"));
            fases.add(new Fase("Fase 3"));
            fases.add(new Fase("Fase 4"));
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Projeto projeto = (Projeto) object;
        return Objects.equals(id, projeto.id) &&
                Objects.equals(nome, projeto.nome) &&
                Objects.equals(versao, projeto.versao) &&
                Objects.equals(unidade, projeto.unidade) &&
                Objects.equals(objetivo, projeto.objetivo) &&
                Objects.equals(dataInicio, projeto.dataInicio) &&
                Objects.equals(dataFim, projeto.dataFim) &&
                Objects.equals(prazo, projeto.prazo) &&
                tipo == projeto.tipo &&
                status == projeto.status &&
                Objects.equals(equipe, projeto.equipe) &&
                Objects.equals(outrasUnidades, projeto.outrasUnidades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, versao, unidade, objetivo, dataInicio, dataFim, prazo, tipo, status, equipe, outrasUnidades);
    }
}
