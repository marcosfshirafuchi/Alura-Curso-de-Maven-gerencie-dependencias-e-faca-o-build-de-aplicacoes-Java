package adopet.api.model;

import adopet.api.dto.SolicitacaoDeAdocaoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "adocoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusAdocao status;

    private String justificativa;

    public Adocao(Tutor tutor, Pet pet, String motivo){
        this.tutor = tutor;
        this.pet = pet;
        this.motivo = motivo;
        this.status = StatusAdocao.AGUARDANDO_AVALIACAO;
    }

    public void marcarComoAprovada(){

        this.status = StatusAdocao.APROVADO;
    }

    public void marcarComoReprovada(String justificativa)
    {
        this.status = StatusAdocao.REPROVADO;
        this.justificativa = justificativa;
    }
}
