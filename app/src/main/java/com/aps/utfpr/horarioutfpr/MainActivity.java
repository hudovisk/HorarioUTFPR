package com.aps.utfpr.horarioutfpr;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aps.utfpr.horarioutfpr.horario.Aula;
import com.aps.utfpr.horarioutfpr.horario.DiasDaSemana;
import com.aps.utfpr.horarioutfpr.horario.Evento;
import com.aps.utfpr.horarioutfpr.horario.HorarioExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    private boolean horarioCustomizadoAtivo = true;

    private HashMap<DiasDaSemana, List<Evento>> eventosCustomizado;
    private HashMap<DiasDaSemana, List<Evento>> eventosOficial;

    private ExpandableListView segundaExpandableListView;
    private ExpandableListView tercaExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton button = (ImageButton) this.findViewById(R.id.atualizar_button);
        button.setVisibility(ImageButton.GONE);

        segundaExpandableListView = (ExpandableListView) findViewById(R.id.segundaExpandableListView);
        tercaExpandableListView = (ExpandableListView) findViewById(R.id.tercaExpandableListView);

        buildHorarios();

        segundaExpandableListView.setAdapter(new HorarioExpandableListAdapter(MainActivity.this, eventosCustomizado.get(DiasDaSemana.SEGUNDA), false));
        tercaExpandableListView.setAdapter(new HorarioExpandableListAdapter(MainActivity.this, eventosCustomizado.get(DiasDaSemana.TERCA), false));
    }

    private void buildHorarios() {

        eventosCustomizado = new HashMap<DiasDaSemana, List<Evento>>();
        eventosOficial = new HashMap<DiasDaSemana, List<Evento>>();

        List<Evento> eventosDaSegundaOficial = new ArrayList<Evento>();

        eventosDaSegundaOficial.add(new Aula(8, 20, 10, 0, 0, 0, false, false, null,"B", "106", "Nádia", "Banco de Dados"));
        eventosDaSegundaOficial.add(new Aula(10, 20, 12, 0, 0, 0, false, false, null,"E", "103", "Jakubiak", "Eletrônica 2"));

        List<Evento> eventosDaTercaOficial = new ArrayList<Evento>();

        eventosDaTercaOficial.add(new Aula(8, 20, 10, 0, 0, 0, false, false, null,"E", "206", "Jakubiak", "Eletrônica 2"));
        eventosDaTercaOficial.add(new Aula(10, 20, 12, 0, 0, 0, false, false, null,"Q", "306", "Jakubiak", "Eletrônica 2"));

        eventosOficial.put(DiasDaSemana.SEGUNDA, eventosDaSegundaOficial);
        eventosOficial.put(DiasDaSemana.TERCA, eventosDaTercaOficial);

        List<Evento> eventosDaSegundaCustom = new ArrayList<Evento>();

        eventosDaSegundaCustom.add(new Aula(8, 20, 10, 0, 0, 0, false, false, null,"B", "106", "Nádia", "Banco de Dados"));
        eventosDaSegundaCustom.add(new Aula(10, 20, 12, 0, 0, 0, false, false, null,"E", "103", "Jakubiak", "Eletrônica 2"));
        eventosDaSegundaCustom.add(new Evento(2, 0, 3, 0, 0, 0, false, false, "Estudar Eletrônica 2"));

        List<Evento> eventosDaTercaCustom = new ArrayList<Evento>();

        eventosDaTercaCustom.add(new Aula(8, 20, 10, 0, 0, 0, false, false, null,"E", "206", "Jakubiak", "Eletrônica 2"));
        eventosDaTercaCustom.add(new Aula(10, 20, 12, 0, 0, 0, false, false, null,"Q", "306", "Jakubiak", "Eletrônica 2"));
        eventosDaTercaCustom.add(new Evento(16, 0, 16, 30, 0, 0, false, false, "Estudar BD"));

        eventosCustomizado.put(DiasDaSemana.SEGUNDA, eventosDaSegundaCustom);
        eventosCustomizado.put(DiasDaSemana.TERCA, eventosDaTercaCustom);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem mudarHorarioItem = menu.findItem(R.id.action_mudar_horario);
        MenuItem novoEventoItem = menu.findItem(R.id.action_novo_evento);
        MenuItem copiarItem = menu.findItem(R.id.action_copiar);

        if(horarioCustomizadoAtivo) {
            mudarHorarioItem.setTitle(R.string.action_mudar_horario_oficial);
            novoEventoItem.setVisible(true);
            copiarItem.setVisible(false);
        }
        else {
            mudarHorarioItem.setTitle(R.string.action_mudar_horario_customizado);
            novoEventoItem.setVisible(false);
            copiarItem.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_configuracoes) {
            return true;
        }
        else if(id == R.id.action_mudar_horario) {
            if(horarioCustomizadoAtivo)
            {
                horarioCustomizadoAtivo = false;
                mostrarHorarioOficial();
            }
            else
            {
                horarioCustomizadoAtivo = true;
                mostrarHorarioCustomizado();
            }

            findViewById(R.id.main_layout).invalidate();
            this.invalidateOptionsMenu();
        }
        else if(id == R.id.action_novo_evento)
        {
            CriarEventoDialogFragment criarEventoDialogFragment = new CriarEventoDialogFragment();
            criarEventoDialogFragment.show(getFragmentManager(), "criar_evento_dialog");
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAtualizarClick(View view)
    {
        AtualizarHorarioDialogFragment atualizarHorarioDialog = new AtualizarHorarioDialogFragment();
        atualizarHorarioDialog.show(getFragmentManager(), "atualizar_horario_dialog");
    }

    private void mostrarHorarioCustomizado() {
        TextView horarioTitulo = (TextView) this.findViewById(R.id.horario_titulo);
        horarioTitulo.setText(R.string.horario_customizado_titulo);

        ImageButton button = (ImageButton) this.findViewById(R.id.atualizar_button);
        button.setVisibility(ImageButton.GONE);

        segundaExpandableListView.setAdapter(new HorarioExpandableListAdapter(MainActivity.this, eventosCustomizado.get(DiasDaSemana.SEGUNDA), false));
        tercaExpandableListView.setAdapter(new HorarioExpandableListAdapter(MainActivity.this, eventosCustomizado.get(DiasDaSemana.TERCA), false));
    }

    private void mostrarHorarioOficial() {
        TextView horarioTitulo = (TextView) this.findViewById(R.id.horario_titulo);
        horarioTitulo.setText(R.string.horario_oficial_titulo);

        ImageButton button = (ImageButton) this.findViewById(R.id.atualizar_button);
        button.setVisibility(ImageButton.VISIBLE);

        segundaExpandableListView.setAdapter(new HorarioExpandableListAdapter(MainActivity.this, eventosOficial.get(DiasDaSemana.SEGUNDA), true));
        tercaExpandableListView.setAdapter(new HorarioExpandableListAdapter(MainActivity.this, eventosOficial.get(DiasDaSemana.TERCA), true));
    }
}