package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ${YuanZhengHu} on 2017-11-28.
 */

public class MessageAdapter extends  RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{
    private List<Messgae> messageList;

    public MessageAdapter(List<Messgae> m) {
            this.messageList = m ;

    }

    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout,parent,false);
        return new MessageViewHolder(v);

    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
                Messgae e = messageList.get(position);
        holder.messageText.setText(e.getMessage());
    }





    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MessageViewHolder  extends RecyclerView.ViewHolder {

        public   TextView messageText;
        public CircleImageView avatar;
        public MessageViewHolder(View v) {
            super(v);
            avatar = (CircleImageView) v.findViewById(R.id.message_avatar);
            messageText = (TextView) v.findViewById(R.id.message_text);

        }
    }
}

