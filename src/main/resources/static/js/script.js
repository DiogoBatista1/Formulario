document.getElementById("contact-form").addEventListener("submit", async function(e) {
    e.preventDefault();

    const nome = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();
    const telefone = document.getElementById("telefone").value.trim();
    const mensagem = document.getElementById("mensagem").value.trim();
    const anexosInput = document.getElementById("anexos");

    let erro = null;

    switch (true) {
        case !nome:
            erro = "O campo nome é obrigatório!";
            break;
        case !email:
            erro = "O campo email é obrigatório!";
            break;
        case !mensagem:
            erro = "O campo mensagem é obrigatório!";
            break;
        case telefone && telefone.replace(/\D/g, '').length < 11:
            erro = "O telefone deve conter ao menos 11 dígitos.";
            break;
    }

    if (erro) {
        alert(erro);
        return;
    }

    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    doc.setFontSize(14);
    doc.text("Formulário de Contato", 10, 10);
    doc.setFontSize(12);
    doc.text(`Nome: ${nome}`, 10, 20);
    doc.text(`Email: ${email}`, 10, 30);
    let y = 40;
    if (telefone) {
        doc.text(`Telefone: ${telefone}`, 10, y);
        y += 10;
    }
    doc.text("Mensagem:", 10, y);
    const textMensagem = doc.splitTextToSize(mensagem, 180);
    doc.text(textMensagem, 10, y + 10);

    const pdfBlob = doc.output("blob");

    const formData = new FormData();
    formData.append("nome", nome);
    formData.append("email", email);
    formData.append("mensagem", mensagem);
    if (telefone) formData.append("telefone", telefone);
	
	
	const safeNome = nome.replace(/[^a-zA-Z0-9]/g, "_")
	
	const pdfFileName = `formulario_${safeNome}_${new Date().getTime()}.pdf`;
	const pdfFile = new File([pdfBlob], pdfFileName, { type: "application/pdf" });
	formData.append("arquivo", pdfFile, pdfFileName);



    if (anexosInput && anexosInput.files.length > 0) {
        for (let i = 0; i < anexosInput.files.length; i++) {
            formData.append("anexos", anexosInput.files[i]);
        }
    }

    fetch("/api/formulario/upload", {
        method: "POST",
        body: formData
    })
    .then(async res => {
        if (res.ok) {
            document.getElementById('popup-overlay').style.display = 'flex';
			document.getElementById('popup-message').innerText = 'Formulário enviado com sucesso!';
        } else if(res.status == 409){
			const msg = await res.text();
			document.getElementById('popup-overlay').style.display = 'flex';
			document.getElementById('popup-message').innerText = msg || 'Usuário já existe!';
		} 
		else {
            alert("Erro ao enviar formulário.");
        }
    })
    .catch(error => {
        console.error("Erro ao enviar formulário:", error);
        alert("Erro ao enviar formulário.");
    });
});


function fecharPopup() {
    document.getElementById("popup-overlay").style.display = 'none';
}
