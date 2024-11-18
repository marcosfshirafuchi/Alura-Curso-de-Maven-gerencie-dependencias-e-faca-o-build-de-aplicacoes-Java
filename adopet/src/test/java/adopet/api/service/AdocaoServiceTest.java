package adopet.api.service;

import adopet.api.dto.SolicitacaoDeAdocaoDTO;
import adopet.api.exception.AdocaoException;
import adopet.api.model.Adocao;
import adopet.api.model.Pet;
import adopet.api.model.StatusAdocao;
import adopet.api.model.Tutor;
import adopet.api.repository.AdocaoRepository;
import adopet.api.repository.PetRepository;
import adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {

    @InjectMocks
    private AdocaoService service;

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private AdocaoRepository adocaoRepository;

    private SolicitacaoDeAdocaoDTO dto;

    @Mock
    private Pet pet;

    @Mock
    private Tutor tutor;

    @Captor
    private ArgumentCaptor<Adocao> adocaoCaptor;

    @Test
    void deveriaLancarExcecaoDePetJaAdotadoQuandoSolicitarAdocao(){

        this.dto = new SolicitacaoDeAdocaoDTO(7l,9l,"Motivo xpto");

        given(petRepository.getReferenceById(this.dto.idPet())).willReturn(pet);
        given(pet.getAdotado()).willReturn(true);


        Assertions.assertThrows(AdocaoException.class, () -> service.solicitar(dto));
    }


    @Test
    void deveriaLancarExcecaoDePetComAdocaoEmAndamentoQuandoSolicitarAdocao(){

        this.dto = new SolicitacaoDeAdocaoDTO(7l,9l,"Motivo xpto");

        given(petRepository.getReferenceById(this.dto.idPet())).willReturn(pet);
        given(adocaoRepository.existsByPetIdAndStatus(this.dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(true);

        Assertions.assertThrows(AdocaoException.class, () -> service.solicitar(dto));
    }

    @Test
    void deveriaLancarExcecaoDeTutorComMaximoDeAdocoesAprovadasQuandoSolicitarAdocao(){

        this.dto = new SolicitacaoDeAdocaoDTO(7l,9l,"Motivo xpto");

        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        given(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO)).willReturn(2);

        Assertions.assertThrows(AdocaoException.class, () -> service.solicitar(dto));
    }


    @Test
    void deveriaPermitirSolicitarAdocao(){

        this.dto = new SolicitacaoDeAdocaoDTO(7l,9l,"Motivo xpto");
        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);

        service.solicitar(dto);

        then(adocaoRepository).should().save(adocaoCaptor.capture());
        Adocao adocaoSalvada = adocaoCaptor.getValue();

        Assertions.assertEquals(pet, adocaoSalvada.getPet());
        Assertions.assertEquals(tutor, adocaoSalvada.getTutor());
        Assertions.assertEquals(dto.motivo(), adocaoSalvada.getMotivo());
    }

}