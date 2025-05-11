document.getElementById("contact-form").addEventListener("submit", function(e) {
    e.preventDefault();

    const nome = document.getElementById("nome").value.trim();
    if (!nome) {
        alert("O campo nome é obrigatório!");
        return;
    }

    const dados = {
        nome: nome,
        email: document.getElementById('email').value,
        telefone: document.getElementById('telefone').value,
        mensagem: document.getElementById('mensagem').value
    };

    fetch('/api/formulario', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dados)
    })
    .then(response => {
        if (response.ok) {
            document.getElementById('popup').classList.remove('popup-ocult');
        } else {
            alert('Erro ao enviar formulário.');
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao enviar formulário.');
    });
});

function fecharPopup() {
    document.getElementById("popup").classList.add("popup-ocult");
}
