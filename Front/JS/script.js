const API_URL = 'http://localhost:8080/aluno';

// Função para inserir ou atualizar um aluno
document.getElementById('alunoForm').addEventListener('submit', async function(event) {
    event.preventDefault(); 

    const aluno = {
        matricula: document.getElementById('matricula').value,
        nome: document.getElementById('nome').value,
        idade: document.getElementById('idade').value,
        email: document.getElementById('email').value
    };

    try {
        let response;
        if (document.getElementById('matricula').disabled) {
        
            response = await fetch(`${API_URL}/update/${aluno.matricula}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(aluno)
            });
            mostrarNotificacao('Aluno atualizado com sucesso!');

        } else {
    
            response = await fetch(`${API_URL}/create`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(aluno)
            });
            mostrarNotificacao('Aluno adicionado com sucesso!');
        }

        listarAlunos();
        limparFormulario();
    } catch (error) {
        console.error('Erro ao salvar aluno:', error);
        alert('Erro ao salvar aluno.');
    }
});


// Função para listar todos os alunos
async function listarAlunos() {
    try {
        const response = await fetch(`${API_URL}/list`);
        const alunos = await response.json();

        const tabelaAlunos = document.getElementById('listaAlunos');
        tabelaAlunos.innerHTML = ''; 

        alunos.forEach(aluno => {
            const tr = document.createElement('tr');

    
            const tdMatricula = document.createElement('td');
            tdMatricula.textContent = aluno.matricula;
            tr.appendChild(tdMatricula);

            const tdNome = document.createElement('td');
            tdNome.textContent = aluno.nome;
            tr.appendChild(tdNome);

            const tdIdade = document.createElement('td');
            tdIdade.className = 'coluna-idade';
            tdIdade.textContent = aluno.idade;
            tr.appendChild(tdIdade);

            const tdEmail = document.createElement('td');
            tdEmail.textContent = aluno.email;
            tr.appendChild(tdEmail);


            const tdAcoes = document.createElement('td');
            tdAcoes.className = 'coluna-acoes';

            const editButton = document.createElement('button');
            editButton.className = 'btn btn-warning btn-sm ';
            editButton.textContent = 'Editar';
            editButton.onclick = () => editarAluno(aluno);
            tdAcoes.appendChild(editButton);

            const deleteButton = document.createElement('button');
            deleteButton.className = 'btn btn-danger btn-sm ml-2';
            deleteButton.textContent = 'Excluir';
            deleteButton.onclick = () => excluirAluno(aluno.matricula);
            tdAcoes.appendChild(deleteButton);

            tr.appendChild(tdAcoes);

            tabelaAlunos.appendChild(tr);
        });
    } catch (error) {
        console.error('Erro ao listar alunos:', error);
        alert('Erro ao listar alunos.');
    }
}


// Função para pesquisar um aluno por matrícula
async function pesquisarAluno() {
    const matricula = document.getElementById('matriculaPesquisa').value;

    if (!matricula) {
        alert('Por favor, insira uma matrícula para pesquisa.');
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${matricula}`);

        if (!response.ok) {
            throw new Error('Aluno não encontrado.');
        }

        const aluno = await response.json();

        const tabelaAlunos = document.getElementById('listaAlunos');
        tabelaAlunos.innerHTML = ''; 

        if (aluno) {
            const tr = document.createElement('tr');

        
            const tdMatricula = document.createElement('td');
            tdMatricula.textContent = aluno.matricula;
            tr.appendChild(tdMatricula);

            const tdNome = document.createElement('td');
            tdNome.textContent = aluno.nome;
            tr.appendChild(tdNome);

            const tdIdade = document.createElement('td');
            tdIdade.className = 'coluna-idade';
            tdIdade.textContent = aluno.idade;
            tr.appendChild(tdIdade);

            const tdEmail = document.createElement('td');
            tdEmail.textContent = aluno.email;
            tr.appendChild(tdEmail);

        
            const tdAcoes = document.createElement('td');
            tdAcoes.className = 'coluna-acoes';

            const editButton = document.createElement('button');
            editButton.className = 'btn btn-warning btn-sm';
            editButton.textContent = 'Editar';
            editButton.onclick = () => editarAluno(aluno);
            tdAcoes.appendChild(editButton);

            const deleteButton = document.createElement('button');
            deleteButton.className = 'btn btn-danger btn-sm ml-2';
            deleteButton.textContent = 'Excluir';
            deleteButton.onclick = () => excluirAluno(aluno.matricula);
            tdAcoes.appendChild(deleteButton);

            tr.appendChild(tdAcoes);

            tabelaAlunos.appendChild(tr);

        } else {
            alert('Aluno não encontrado.');
        }
    } catch (error) {
        console.error('Erro ao pesquisar aluno:', error);
        alert('Erro ao pesquisar aluno.');
    }  finally {
        document.getElementById('matriculaPesquisa').value = '';
    }
    
}


// Função para editar um aluno
function editarAluno(aluno) {
    document.getElementById('matricula').value = aluno.matricula;
    document.getElementById('nome').value = aluno.nome;
    document.getElementById('idade').value = aluno.idade;
    document.getElementById('email').value = aluno.email;

    document.getElementById('matricula').disabled = true;

    const submitBtn = document.getElementById('submitBtn');
    submitBtn.textContent = 'Atualizar';
    submitBtn.className = 'btn btn-warning mt-3'; 
}


// Função para excluir um aluno
async function excluirAluno(matricula) {
    if (confirm(`Tem certeza que deseja excluir o aluno de matrícula ${matricula}?`)) {
        try {
            const response = await fetch(`${API_URL}/${matricula}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                mostrarNotificacao('Aluno excluído com sucesso!'); 
                listarAlunos();
            } else {
                alert('Erro ao excluir aluno.');
            }
        } catch (error) {
            console.error('Erro ao excluir aluno:', error);
            alert('Erro ao excluir aluno.');
        }
    }
}


// Função para limpar o formulário
function limparFormulario() {
    document.getElementById('matricula').value = '';
    document.getElementById('nome').value = '';
    document.getElementById('idade').value = '';
    document.getElementById('email').value = '';
    document.getElementById('matricula').disabled = false;

    const submitBtn = document.getElementById('submitBtn');
    submitBtn.textContent = 'Inserir Aluno';
    submitBtn.className = 'btn btn-primary mt-3'; 
}


// Inicializar a lista de alunos quando a página carregar
document.addEventListener('DOMContentLoaded', listarAlunos);

function mostrarNotificacao(mensagem) {
    const notification = document.getElementById('notification');
    notification.textContent = mensagem;
    notification.style.display = 'block'; 
    notification.style.opacity = '1'; 

   
    setTimeout(() => {
        notification.style.opacity = '0'; 
        setTimeout(() => {
            notification.style.display = 'none'; 
        }, 500); 
    }, 3000);
}
