package com.zoogaru.android.jpconnect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.zoogaru.android.jpconnect.GlobalUtil;
import com.zoogaru.android.jpconnect.R;
import com.zoogaru.android.jpconnect.entity.Channel;

import java.util.List;

/**
 * Created by sandeep on 5/15/16.
 */
public class ChannelsAdapter extends ArrayAdapter<Channel> {

    private final Context m_context;
    private final List<Channel> m_channelsArrayList;

    private Switch m_switch;
    private TextView m_timeStamp;

    public ChannelsAdapter(Context context, List<Channel> modelsArrayList) {
        super(context, R.layout.widget_channel, modelsArrayList);
        this.m_context = context;
        this.m_channelsArrayList = modelsArrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Channel channel = m_channelsArrayList.get(position);

        LayoutInflater inflater = (LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.widget_channel, parent, false);

        String tagName = channel.getTitle();
        TextView titleView = (TextView) rowView.findViewById(R.id.id_channel_title);
        titleView.setText(tagName);

        m_switch = (Switch) rowView.findViewById(R.id.id_switch);
        if(channel.isListening())
        {
            m_switch.setChecked(true);
        }

        m_timeStamp = (TextView) rowView.findViewById(R.id.id_time_stamp);

        m_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    channel.setListening(1);
                } else {
                    channel.setListening(0);
                }
                GlobalUtil.m_quikc_store.updateChannel(channel);
            }
        });

        return rowView;
    }

    List<Channel> getChannels()
    {
        return m_channelsArrayList;
    }
}