package com.aps.utfpr.horarioutfpr.horario;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aps.utfpr.horarioutfpr.R;
import com.aps.utfpr.horarioutfpr.horario.Evento;

import java.util.List;

/**
 * Created by Hudo on 01/12/2014.
 */
public class HorarioExpandableListAdapter extends BaseExpandableListAdapter implements View.OnCreateContextMenuListener {

    private List<Evento> eventos;
    private LayoutInflater inflater;
    private boolean isOficial;

    public HorarioExpandableListAdapter(Context context, List<Evento> eventos, boolean isOficial)
    {
        this.eventos = eventos;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.isOficial = isOficial;
    }


    @Override
    public int getGroupCount() {
        return eventos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return eventos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return eventos.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup holder;

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.group_expandable_list_layout, null);

            if(!isOficial)
                convertView.setOnCreateContextMenuListener(this);

            holder = new ViewHolderGroup();
            convertView.setTag(holder);

            holder.groupTextView = (TextView) convertView.findViewById(R.id.groupTextView);
            holder.groupHoraTextView = (TextView) convertView.findViewById(R.id.groupHoraTextView);
        }
        else
        {
            holder = (ViewHolderGroup) convertView.getTag();
        }

        Evento evento = (Evento) getGroup(groupPosition);
        if(evento == null)
        {
            holder.groupTextView.setText("VAZIO");
        }
        else {
            holder.groupTextView.setText(evento.toString());
            holder.groupHoraTextView.setText(String.format("%02d",evento.getHoraInicio()) + ":" + String.format("%02d",evento.getMinutoInicio()) + " - " +
                                            String.format("%02d",evento.getHoraFim()) + ":" + String.format("%02d", evento.getMinutoFim()));
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewItemGroup holder;

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_expandable_list_layout, null);

            holder = new ViewItemGroup();
            convertView.setTag(holder);

           if(isOficial)
               convertView.findViewById(R.id.edit_info_item).setVisibility(LinearLayout.GONE);

            holder.aulaLayout = (LinearLayout) convertView.findViewById(R.id.item_aula_layout);
            holder.naoAulaLayout = (LinearLayout) convertView.findViewById(R.id.item_nao_aula_layout);

            holder.professorTextView = (TextView) convertView.findViewById(R.id.professorTextView);
            holder.disciplinaTextView = (TextView) convertView.findViewById(R.id.disciplinaTextView);

            holder.descricaoTextView = (TextView) convertView.findViewById(R.id.descricao);
        }
        else
        {
            holder = (ViewItemGroup) convertView.getTag();
        }

        Evento evento = (Evento) getGroup(groupPosition);
        if(evento instanceof Aula)
        {
            Aula aula = (Aula) evento;
            holder.naoAulaLayout.setVisibility(LinearLayout.GONE);
            holder.aulaLayout.setVisibility(LinearLayout.VISIBLE);

            holder.professorTextView.setText(aula.getProfessor());
            holder.disciplinaTextView.setText(aula.getDisciplina());
        }
        else
        {
            holder.aulaLayout.setVisibility(LinearLayout.GONE);
            holder.naoAulaLayout.setVisibility(LinearLayout.VISIBLE);

            holder.descricaoTextView.setText(evento.getDescricao());
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        ViewHolderGroup holder = (ViewHolderGroup) v.getTag();

        menu.setHeaderTitle(holder.groupTextView.getText());

        menu.add(0,0,0,R.string.action_editar_expandable_list_view);
        menu.add(0,1,1,R.string.action_excluir_expandable_list_view);
        menu.add(0,2,2,R.string.action_visualizar_expandable_list_view);
    }

    class ViewItemGroup
    {
        public LinearLayout aulaLayout;
        public LinearLayout naoAulaLayout;

        public TextView professorTextView;
        public TextView disciplinaTextView;

        public TextView descricaoTextView;
    }

    class ViewHolderGroup
    {
        public TextView groupTextView;
        public TextView groupHoraTextView;
    }
}
