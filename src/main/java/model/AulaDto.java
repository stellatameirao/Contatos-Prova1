package model;

import enums.DisciplinaEnum;

public class AulaDto {

	public String id;
	public String disciplina;
	public String codDisciplina;
	public String assunto;
	public String duracao;
	public String data;
	public String horario;

	public AulaDto() {
	}

	public AulaDto(Aula aula) {
		this.id = Long.toString(aula.getId());
		this.disciplina = this.geraNomeDisc(aula.getCodDisciplina());
		this.codDisciplina = Integer.toString(DisciplinaEnum.getDiscByNome(this.disciplina).getCodigo());
		this.assunto = aula.getAssunto();
		this.duracao = Integer.toString(aula.getDuracao());
		this.data = this.formataData(aula.getData());
		this.horario = aula.getHorario();
	}

	private String geraNomeDisc(int cod) {
		return DisciplinaEnum.getDiscByCodigo(cod).getNome();
	}

	private String formataData(String dt) {
		String[] partes = dt.split("-");
		String resposta = partes[2] + "/" + partes[1] + "/" + partes[0];
		return resposta;
	}

	public void reverteFormatoData() {
		String[] partes = this.data.split("/");
		this.data = partes[2] + "-" + partes[1] + "-" + partes[0];
	}

	@Override
	public String toString() {
		return "AulaViewDTO [id=" + id + ", disciplina=" + disciplina + ", codDisciplina=" + codDisciplina
				+ ", assunto=" + assunto + ", duracao=" + duracao + ", data=" + data + ", horario=" + horario + "]";
	}

}