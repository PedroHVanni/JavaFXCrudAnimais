package com.template.service;

import com.template.model.dao.AnimaisDAO;
import com.template.model.dto.AnimalDTO;

import java.util.List;

public class AnimalService {

    private final AnimaisDAO animaisDAO;

    public AnimalService() {
        animaisDAO = new AnimaisDAO();
    }

    public List<AnimalDTO> listarAnimais() throws Exception {
        return animaisDAO.listarAnimais();
    }

    public boolean cadastrarAnimal(AnimalDTO animal) throws Exception {
        return animaisDAO.cadastrarAnimal(animal);
    }

    public boolean alterarAnimal(AnimalDTO animal) throws Exception {
        return animaisDAO.alterarAnimal(animal);
    }

    public boolean excluirAnimal(int id) throws Exception {
        return animaisDAO.excluirAnimal(id);
    }
}
