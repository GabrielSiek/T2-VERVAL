package SGP_Backend.SGP_Backend.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private final WebClient webClient;

    public LoginController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://sgp-auth:8080").build(); // URL do Keycloak
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        return authenticateWithKeycloak(username, password);
    }

    private ResponseEntity<?> authenticateWithKeycloak(String username, String password) {
        // Dados que serão enviados para o Keycloak para autenticação
        Map<String, String> formData = new HashMap<>();
        formData.put("grant_type", "password");
        formData.put("client_id", "SGP-Backend");  // ID do client configurado no Keycloak
        formData.put("client_secret", "development-secret");  // Substitua pelo client secret
        formData.put("username", username);
        formData.put("password", password);

        String encodedFormData = formData.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce((e1, e2) -> e1 + "&" + e2)
                .orElse("");

        // Fazendo a requisição para o endpoint do Keycloak com especificação correta de tipos
        Mono<Map<String, Object>> tokenResponse = webClient.post()
                .uri("/realms/SGP-Realm/protocol/openid-connect/token")
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .bodyValue(encodedFormData)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});  // Usando ParameterizedTypeReference

        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> tokenResponseData = tokenResponse.block();  // Executa a requisição

            if (tokenResponseData != null && tokenResponseData.containsKey("access_token")) {
                String accessToken = (String) tokenResponseData.get("access_token");
                String refreshToken = (String) tokenResponseData.get("refresh_token");

                // Extraindo roles (informações do usuário) do token
                String roles = extractRolesFromAccessToken(accessToken);

                response.put("access_token", accessToken);
                response.put("refresh_token", refreshToken);
                response.put("roles", roles);

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("Credenciais inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao autenticar com Keycloak");
        }
    }

    private String extractRolesFromAccessToken(String accessToken) {
        // Decodifica o token JWT e extrai as roles (claims)
        String[] chunks = accessToken.split("\\.");
        if (chunks.length == 3) {
            return new String(java.util.Base64.getDecoder().decode(chunks[1]));
        }
        return null;
    }
}
