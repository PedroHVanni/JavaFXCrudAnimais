package com.template;

public class AnimaisDAO {

    // Cadastrar um novo animal
    public void cadastrarAnimal(AnimalDTO animal) {

        // Comando SQL para inserir um registro na tabela animal
        String sql = "INSERT INTO animal (animal, cor, especie, idade, sexo) VALUES (?, ?, ?, ?, ?)";

        // try-with-resources fecha automaticamente conexão e statement
        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            // Preenche os parâmetros da query
            ps.setString(1, animal.getAnimal());
            ps.setString(2, animal.getCor());
            ps.setString(3, animal.getEspecie());
            ps.setString(4, animal.getIdade());
            ps.setString(5, animal.getSexo());

            // Executa o INSERT e retorna a quantidade de linhas afetadas
            int linhas = ps.executeUpdate();

            // Se alguma linha foi inserida, exibe mensagem de sucesso
            if (linhas > 0) {
                System.out.println("\nAnimal cadastrado com sucesso!");
            }

        } catch (SQLException e) {

            // Captura erros de banco de dados
            System.out.println("\nErro ao cadastrar animal: " + e.getMessage());
        }
    }

    // Listar todos os animais cadastrados
    public List<AnimalDTO> listarAnimais() {

        // Consulta SQL para buscar todos os registros
        String sql = "SELECT * FROM animal ORDER BY id";

        // Lista que armazenará os animais retornados do banco
        List<AnimalDTO> lista = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Percorre todas as linhas retornadas pelo SELECT
            while (rs.next()) {

                // Cria um objeto AnimalDTO com os dados da linha atual
                AnimalDTO animal = new AnimalDTO(
                        rs.getInt("id"),
                        rs.getString("animal"),
                        rs.getString("cor"),
                        rs.getString("especie"),
                        rs.getString("idade"),
                        rs.getString("sexo")
                );

                // Adiciona o objeto na lista
                lista.add(animal);
            }

        } catch (SQLException e) {

            // Captura erros de banco de dados
            System.out.println("\nErro ao listar animais: " + e.getMessage());
        }

        // Retorna a lista preenchida
        return lista;
    }

    // Atualizar os dados de um animal existente
    public void alterarAnimal(AnimalDTO animal) {

        // Atualiza os campos do animal de acordo com o ID informado
        String sql = "UPDATE animal SET animal = ?, cor = ?, especie = ?, idade = ?, sexo = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            // Define os valores dos parâmetros da query
            ps.setString(1, animal.getAnimal());
            ps.setString(2, animal.getCor());
            ps.setString(3, animal.getEspecie());
            ps.setString(4, animal.getIdade());
            ps.setString(5, animal.getSexo());

            // ID do registro que será alterado
            ps.setInt(6, animal.getId());

            // Executa o UPDATE
            int linhas = ps.executeUpdate();

            // Verifica se algum registro foi atualizado
            if (linhas > 0) {
                System.out.println("\nAnimal atualizado com sucesso!");
            } else {
                System.out.println("\nID não encontrado.");
            }

        } catch (SQLException e) {

            // Captura erros de banco de dados
            System.out.println("\nErro ao atualizar animal: " + e.getMessage());
        }
    }

    // Excluir um animal pelo ID
    public void excluirAnimal(int id) {

        // Remove o registro correspondente ao ID informado
        String sql = "DELETE FROM animal WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            // Define qual ID será excluído
            ps.setInt(1, id);

            // Executa o DELETE
            int linhas = ps.executeUpdate();

            // Verifica se algum registro foi removido
            if (linhas > 0) {
                System.out.println("\nAnimal excluído com sucesso!");
            } else {
                System.out.println("\nID não encontrado.");
            }

        } catch (SQLException e) {

            // Captura erros de banco de dados
            System.out.println("\nErro ao excluir animal: " + e.getMessage());
        }
    }
}
