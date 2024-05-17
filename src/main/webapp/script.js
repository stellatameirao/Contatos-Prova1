// ==============================================================
// 		EVENTOS

// RESET
reset = function() {
	let req = new XMLHttpRequest();
	req.open("POST", "ControllerServlet", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.onreadystatechange = () => {
		if (req.readyState == 4 && req.status == 200) { 
			   atualizaSessao();
			   expoeListaContatos();
			}else {
				console.error("Ocorreu um erro na requisição: ", req.status);
				}
	   }
		req.send("op=RESET");
}

// NOVA AULA
novaAula = function() {
	window.location.href = "nova";
}

// CANCELA NOVA AULA (OU EDIÇÃO)
calcelarNovaAula = function() {
	window.location.href = "/prova1";
}

// EDITA UMA AULA COM ID ESPECIFICADO
editarAula = function(id) {
	window.location.href = "edit?id=" + id;
}

// ENVIA CONTEÚDO DA NOVA AULA
enviarNovaAula = function() {
	// obtém os valores a partir do formulário
	let data = document.getElementById('data-id').value;
	let horario = document.getElementById('hora-id').value;
	let duracao = document.getElementById('dur-id').value;
	let codDisciplina = document.getElementById('disc-id').value;
	let assunto = document.getElementById('ass-id').value;
	// verificando a validação
	if (!validaNovaAula(data, horario, duracao, codDisciplina, assunto)) {
        document.getElementById('msg-id').style.display = 'block';
        return;
    }
	let req = new XMLHttpRequest();
	req.open("POST", "ControllerServlet", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.onreadystatechange = function() {
		if (req.readyState == 4) {
			if (req.status == 200) {  
			   atualizaSessao();
			   window.location.href = "/prova1";
			} else {
				console.error("Erro na requisição: ", req.status);
					}
			}
		}
	let parametros = "op=CREATE&data=" + encodeURIComponent(data) + "&horario=" + encodeURIComponent(horario) + "&duracao=" + encodeURIComponent(duracao) + "&codDisciplina=" + encodeURIComponent(codDisciplina) + "&assunto=" + encodeURIComponent(assunto);
   	req.send(parametros);
}

// ENVIA CONTEÚDO EM EDIÇÃO







// DELETA UMA AULA








const atualizaSessao = function() {
	let req = new XMLHttpRequest();
	req.open("POST", "ControllerServlet", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.onreadystatechange = () => {
		if (req.readyState == 4 && req.status == 200) {
			expoeListaContatos();
			console.log("A sessão foi atualizada com sucesso.");
		} else {
			console.log("Ocorreu um erro ao tentar atualizar a sessão.");
		}
	}
	req.send("op=START_SESSION");
}


// ============================================================
// 			VALIDAÇÕES

validaNovaAula = function(data, horario, duracao, codDisciplina, assunto) {
    // Examine os valores dos parâmetros deste método e decida se estão
    // ou não validados. Este 'return true' provavelmente será alterado, não?
    return true;
}





// ===================================================================================
// 		INICIALIZA O PROCESSAMENTO

atualizaSessao();