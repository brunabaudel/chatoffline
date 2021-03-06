package br.ufpe.cin.if1001.chatoffline.gui.base.friends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.controllers.ChatController;
import br.ufpe.cin.if1001.chatoffline.gui.base.activity.MainActivity;
import br.ufpe.cin.if1001.chatoffline.gui.base.friends.FriendsAdapter;
import br.ufpe.cin.if1001.chatoffline.gui.message.MessageActivity;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Friend;
import br.ufpe.cin.if1001.chatoffline.model.user.UserPreferences;


public class FriendsFragment extends Fragment {

    private RecyclerView recyclerView;
    private FriendsAdapter adapter;
    private static List<Friend> friends = null;
    private ChatController chatController;

    public FriendsFragment() {
    }

    private void setListFriends(){
        friends.addAll(chatController.getAllFriends());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        friends = new ArrayList<Friend>();
        chatController = ChatController.getInstance(UserPreferences.getUser(getActivity()), getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_friends, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.list_friends);

        setListFriends();
        adapter = new FriendsAdapter(getActivity(), this.friends);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(new FriendsAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v , int position) {
                Friend friend = adapter.getItem(position);

                Intent i = new Intent(getActivity(), MessageActivity.class);
                i.putExtra("FRIEND", (Parcelable)friend);
                getActivity().startActivity(i);
            }
        });

        return layout;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
