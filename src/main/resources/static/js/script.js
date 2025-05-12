document.getElementById("contact-form").addEventListener("submit", async function(e) {
    e.preventDefault();


    const dados = {
        nome: document.getElementById("nome").value.trim(),
        email: document.getElementById('email').value.trim(),
        telefone: document.getElementById('telefone').value.trim(),
        mensagem: document.getElementById('mensagem').value.trim()
    };
	let erro = null;
	
	switch(true) {
		case !dados.nome:
			erro = "O campo nome é obrigatório!"
			break;
		
		case !dados.email:
			erro = "O campo email é obrigatório!"
			break;
			
		case !dados.mensagem:
			erro = "O campo mensagem é obrigatório!";
			break;
			
		case dados.telefone && !/\(\d{2}\) \d{4,5}-\d{4}/.test(dados.telefone):
			 erro = "Telefone deve seguir o formato (00) 00000-0000";
			 break;
	}
	
	if (erro) {
		alert(erro);
		return;
	}
	
    fetch('/api/formulario', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dados)
    })
    .then(response => {
        if (response.ok) {
			document.getElementById('popup-overlay').style.display = 'flex';
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
    document.getElementById("popup-overlay").style.display = 'none';
}
