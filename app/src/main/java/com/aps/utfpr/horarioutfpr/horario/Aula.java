package com.aps.utfpr.horarioutfpr.horario;

/**
 * Created by Hudo on 02/12/2014.
 */
public class Aula extends Evento {
    
    private String bloco;
    private String sala;
    private String professor;
    private String disciplina;

    public Aula()
    {
        super();
    }

    public Aula(int horaInicio, int minutoInicio, int horaFim, int minutoFim, int horaNotificacao, int minutoNotificacao, boolean isSilencioso, boolean isNotificar, String descricao, String bloco, String sala, String professor, String disciplina) {
        super(horaInicio, minutoInicio, horaFim, minutoFim, horaNotificacao, minutoNotificacao, isSilencioso, isNotificar, descricao);
        this.bloco = bloco;
        this.sala = sala;
        this.professor = professor;
        this.disciplina = disciplina;
    }

    @Override
    public String toString()
    {
        return bloco + " - " + sala;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
}
