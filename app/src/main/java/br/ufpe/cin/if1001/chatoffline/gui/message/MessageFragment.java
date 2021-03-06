package br.ufpe.cin.if1001.chatoffline.gui.message;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Friend;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Message;

public class MessageFragment extends Fragment implements View.OnClickListener {

    private static String TAG = MessageActivity.class.getSimpleName();

    private ListView mListViewMessage;
    private Button mButtonSend;
    private EditText mEditMessage;
    private List<Message> mListMessage;
    private MessageAdapter mChatMessageAdapter;

    public MessageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);

        initViews(rootView);

        mListMessage = new ArrayList<>();

        mChatMessageAdapter = new MessageAdapter(getActivity(), mListMessage);
        mListViewMessage.setAdapter(mChatMessageAdapter);

        return rootView;
    }

    private void initViews(View v) {
        mListViewMessage = (ListView) v.findViewById(R.id.listViewMessage);
        mButtonSend = (Button) v.findViewById(R.id.buttonSend);
        mEditMessage = (EditText) v.findViewById(R.id.editMessage);

        mButtonSend.setOnClickListener(this);
    }

    /*
 * ListView
 */

    private void addItemsToList() {

        Message message = new Message(mEditMessage.getText().toString(), Message.TypeMessage.SENT_MESSAGE);

        mListMessage.add(message);

        if(getActivity() instanceof OnMessageListener) {
            ((OnMessageListener) getActivity()).sendMessage(message);
        }

        mChatMessageAdapter.notifyDataSetChanged();
    }


    public void receiveMessage(Message message){

        mListMessage.add(message);
        mChatMessageAdapter.notifyDataSetChanged();
    }

    public void loadMessages(List<Message> messages){
        mListMessage.addAll(messages);
        mChatMessageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
           case R.id.buttonSend:
               if(!mEditMessage.getText().toString().equals("")) {
                   addItemsToList();
                   mEditMessage.setText("");
               } else {
                   Toast.makeText(getActivity(), "Digite uma mensagem", Toast.LENGTH_SHORT).show();
               }
               break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    interface OnMessageListener {
        void sendMessage(Message message);
    }


}