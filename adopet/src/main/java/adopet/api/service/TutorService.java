package adopet.api.service;

import adopet.api.dto.CadastroTutorDTO;
import adopet.api.dto.TutorDTO;
import adopet.api.model.Tutor;
import adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public List<TutorDTO> listarTodos(){
        return repository.findAll().stream().map(TutorDTO::new).toList();
    }
    public void cadastrar(CadastroTutorDTO dados){
        repository.save(new Tutor(dados));
    }
}
