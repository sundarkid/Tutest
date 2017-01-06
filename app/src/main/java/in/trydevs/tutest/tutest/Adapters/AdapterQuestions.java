package in.trydevs.tutest.tutest.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import in.trydevs.tutest.tutest.DataClasses.Question;
import in.trydevs.tutest.tutest.R;

/**
 * Created by kid on 06/01/17.
 */

public class AdapterQuestions extends RecyclerView.Adapter<AdapterQuestions.MyViewHolder> {


    List<Question> data = Collections.emptyList();
    Context context;
    private LayoutInflater inflater;

    public AdapterQuestions(Context context, List<Question> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        if (data.size() > 0)
            this.data = data;
        else
            this.data = Collections.emptyList();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_view_questions, parent, false);
        return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Question current = data.get(position);
        holder.question.setText(current.getQuestion());
        holder.rb0.setText(current.getOption1());
        holder.rb1.setText(current.getOption2());
        holder.rb2.setText(current.getOption3());
        holder.rb3.setText(current.getOption4());
        holder.radioGroup.check(holder.radioGroup.getChildAt(current.getAnswerId()).getId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<Question> getAnswers() {
        return data;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView question;
        RadioGroup radioGroup;
        RadioButton rb0, rb1, rb2, rb3;

        public MyViewHolder(View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.fragment_create_question_question);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.fragment_create_question_radio_group);
            rb0 = (RadioButton) itemView.findViewById(R.id.fragment_create_question_radio_0);
            rb1 = (RadioButton) itemView.findViewById(R.id.fragment_create_question_radio_1);
            rb2 = (RadioButton) itemView.findViewById(R.id.fragment_create_question_radio_2);
            rb3 = (RadioButton) itemView.findViewById(R.id.fragment_create_question_radio_3);
            rb0.setOnClickListener(this);
            rb1.setOnClickListener(this);
            rb2.setOnClickListener(this);
            rb3.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.fragment_create_question_radio_0:
                    data.get(getAdapterPosition()).setAnswerId(0);
                    break;
                case R.id.fragment_create_question_radio_1:
                    data.get(getAdapterPosition()).setAnswerId(1);
                    break;
                case R.id.fragment_create_question_radio_2:
                    data.get(getAdapterPosition()).setAnswerId(2);
                    break;
                case R.id.fragment_create_question_radio_3:
                    data.get(getAdapterPosition()).setAnswerId(3);
                    break;
            }
        }
    }

}
