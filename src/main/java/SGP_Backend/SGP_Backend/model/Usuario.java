package SGP_Backend.SGP_Backend.model;

import lombok.*;
import lombok.experimental.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    public enum TipoUsuario {
        ADMIN, GERENTE, FUNCIONARIO, CLIENTE, PATROCINADOR
    }

    //metodo para validar o usuario
    public void validarUsuario() {
        if (this.nome == null || this.nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário não pode ser vazio");
        }
        if (this.tipo == null) {
            throw new IllegalArgumentException("Tipo de usuário não pode ser nulo");
        }
    }
}
