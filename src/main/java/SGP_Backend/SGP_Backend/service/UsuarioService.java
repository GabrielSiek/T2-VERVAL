package SGP_Backend.SGP_Backend.service;
import SGP_Backend.SGP_Backend.model.Usuario;
import SGP_Backend.SGP_Backend.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Transactional
    public Usuario salvarUsuario(Usuario usuarioEntity) {
        usuarioEntity.validarUsuario();
        return usuarioRepository.save(usuarioEntity);
    }

    @Transactional
    public void deletarUsuario(Usuario usuarioEntity) {
        usuarioRepository.delete(usuarioEntity);
    }


    public Usuario buscarUsuarioPorNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    @Transactional
    public Optional<Usuario> buscarPorId(Long id) {
    return usuarioRepository.findById(id);
    }

    
    public Usuario atualizarUsuario(Usuario usuarioEntity) {
        usuarioEntity.validarUsuario();
        return usuarioRepository.save(usuarioEntity);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

}