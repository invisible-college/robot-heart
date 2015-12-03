package college.invisible.robothello;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ppham on 12/2/15.
 */
public class FlashCardAdapter extends RecyclerView.Adapter<FlashCardAdapter.FlashCardViewHolder> {

    /**
     * Created by ppham on 12/2/15.
     */
    public static class FlashCardViewHolder extends RecyclerView.ViewHolder {

        protected TextView vQuestion;
        protected TextView vAnswer;

        public FlashCardViewHolder(View itemView) {
            super(itemView);
            vQuestion = (TextView) itemView.findViewById(R.id.question_view);
            vAnswer = (TextView) itemView.findViewById(R.id.answer_view);
        }

    }

    private Context mContext;
    private LayoutInflater mInflater;
    private List<FlashCard> mList;

    public FlashCardAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = new ArrayList<>();
    }

    public void addFlashCards(List<FlashCard> newFlashCards) {
        this.mList.addAll(newFlashCards);
        notifyDataSetChanged();
    }


    @Override
    public FlashCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.flash_card, parent, false);
        return new FlashCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FlashCardViewHolder holder, int position) {
        FlashCard fc = mList.get(position);
        holder.vQuestion.setText(fc.question);
        if (fc.isAnswerVisible()) {
            holder.vAnswer.setText(fc.correctAnswer);
        } else {
            holder.vAnswer.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }


}
