package com.zoogaru.android.jpconnect.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.zoogaru.android.jpconnect.GlobalUtil;
import com.zoogaru.android.jpconnect.INotifyUs;
import com.zoogaru.android.jpconnect.QKConstants;
import com.zoogaru.android.jpconnect.R;
import com.zoogaru.android.jpconnect.adapter.ChannelsAdapter;
import com.zoogaru.android.jpconnect.entity.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep on 5/15/16.
 */

public class ChannelsFragment extends ListFragment {
    INotifyUs m_fragment_listener ;
    ChannelsAdapter m_channleAdapter ;
    DatabaseReference m_fb_channel_Ref ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channels_list, container, false);
        m_channleAdapter = new ChannelsAdapter(getContext(), getSubscribedChannel());
        setListAdapter(m_channleAdapter);

        /*View footer =  inflater.inflate(R.layout.widget_add_channel, null, false);
        listView.addFooterView(footer);*/
        setHasOptionsMenu(true);
        m_fb_channel_Ref = FirebaseDatabase.getInstance().getReference("channels");
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                String key = dataSnapshot.getKey();
                Log.d(QKConstants.TAG, "onChildAdded:" + key);
                Channel newChannel = dataSnapshot.getValue(Channel.class);
                newChannel.setkey(key);
                if(newChannel.getTitle().contains(" "))
                {
                    return;
                }

                Channel dbChannel = GlobalUtil.m_quikc_store.getChannel(key);
                if(dbChannel == null) {
                    GlobalUtil.m_quikc_store.insertToSqlite(newChannel);
                    m_channleAdapter.add(newChannel);
                }
                else
                {
                    m_channleAdapter.add(dbChannel);
                }
                m_channleAdapter.notifyDataSetChanged();
                FirebaseMessaging.getInstance().subscribeToTopic(newChannel.getTitle());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(QKConstants.TAG, "postComments:onCancelled", databaseError.toException());
                Toast.makeText(getActivity(), "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        m_fb_channel_Ref.addChildEventListener(childEventListener);

        return view;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        m_fragment_listener = (INotifyUs) activity;
    }

    @Override
    public void  onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        m_fragment_listener.notifyus("showFragment", m_channleAdapter.getItem(position).getTitle());
    }

    List<Channel> getSubscribedChannel()
    {
        return new ArrayList<>();
    }
}