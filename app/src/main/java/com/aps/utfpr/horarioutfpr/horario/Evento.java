package com.aps.utfpr.horarioutfpr.horario;

/**
 * Created by Hudo on 01/12/2014.
 */
public class Evento {

    protected int horaInicio;
    protected int minutoInicio;
    protected int horaFim;
    protected int minutoFim;

    protected int horaNotificacao;
    protected int minutoNotificacao;

    protected boolean isSilencioso;
    protected boolean isNotificar;

    protected String descricao;

    public Evento()
    {
    }

    public Evento(int horaInicio, int minutoInicio, int horaFim, int minutoFim, int horaNotificacao, int minutoNotificacao, boolean isSilencioso, boolean isNotificar, String descricao) {
        this.horaInicio = horaInicio;
        this.minutoInicio = minutoInicio;
        this.horaFim = horaFim;
        this.minutoFim = minutoFim;
        this.horaNotificacao = horaNotificacao;
        this.minutoNotificacao = minutoNotificacao;
        this.isSilencioso = isSilencioso;
        this.isNotificar = isNotificar;
        this.descricao = descricao;
    }

    @Override
    public String toString()
    {
        return descricao;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getMinutoInicio() {
        return minutoInicio;
    }

    public void setMinutoInicio(int minutoInicio) {
        this.minutoInicio = minutoInicio;
    }

    public int getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(int horaFim) {
        this.horaFim = horaFim;
    }

    public int getMinutoFim() {
        return minutoFim;
    }

    public void setMinutoFim(int minutoFim) {
        this.minutoFim = minutoFim;
    }

    public int getHoraNotificacao() {
        return horaNotificacao;
    }

    public void setHoraNotificacao(int horaNotificacao) {
        this.horaNotificacao = horaNotificacao;
    }

    public int getMinutoNotificacao() {
        return minutoNotificacao;
    }

    public void setMinutoNotificacao(int minutoNotificacao) {
        this.minutoNotificacao = minutoNotificacao;
    }

    public boolean isSilencioso() {
        return isSilencioso;
    }

    public void setSilencioso(boolean isSilencioso) {
        this.isSilencioso = isSilencioso;
    }

    public boolean isNotificar() {
        return isNotificar;
    }

    public void setNotificar(boolean isNotificar) {
        this.isNotificar = isNotificar;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
