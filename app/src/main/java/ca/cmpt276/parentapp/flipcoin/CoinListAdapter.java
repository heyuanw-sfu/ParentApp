package ca.cmpt276.parentapp.flipcoin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ca.cmpt276.parentapp.R;
import ca.cmpt276.parentapp.model.FlipCoin;

/**
 *An adapter used to show the list of flipCoin games onto a listView
 */
public class CoinListAdapter extends ArrayAdapter<FlipCoin> {

    public CoinListAdapter(Context context, ArrayList<FlipCoin> coinList){
        super(context, R.layout.activity_flip_coin_history, coinList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        FlipCoin currentGame = getItem(getCount() - 1 - position);

        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.history_textview, parent, false);
        }

        if (currentGame.getFlipResult() != null){
            //Picker:
            TextView coinPicker = itemView.findViewById(R.id.flipcoin_history_picker);
            String format_picker = "Picker: " + currentGame.getPicker().getName();
            coinPicker.setText(format_picker);

            //Result:
            TextView coinResult = itemView.findViewById(R.id.flipcoin_history_result);
            String format_result = "Result: " + currentGame.getFlipResult().toString();
            coinResult.setText(format_result);

            Log.i("ss", currentGame.getFlipResult().toString());

            //Player Profile
            ImageView player_profile = itemView.findViewById(R.id.flipCoin_history_profile);
            //TODO: SET CHILD PHOTO PROFILE HERE

            // P1 info
            ImageView coinDetails =  itemView.findViewById(R.id.coinDetails);
            coinDetails.setImageResource(getCoinImageId(currentGame));

            TextView creationTime =  itemView.findViewById(R.id.time);
            creationTime.setText(currentGame.getCreatedTime());

            ImageView result_img = itemView.findViewById(R.id.result_img);
            result_img.setImageResource(getResultIconId(currentGame));
        }

        return itemView;
    }

    private String formatResult(FlipCoin currentGame) {

        return "Result: " + currentGame.getFlipResult().toString()+
                         "\nPicker: " + currentGame.getPicker().getChildName();
    }

    private int getCoinImageId(FlipCoin currentGame){
        if(currentGame.getFlipResult() == FlipCoin.CoinSide.HEADS){
            return R.drawable.loonie_heads;
        }
        else{
            return R.drawable.loonie_tails;
        }
    }

    private int getResultIconId(FlipCoin currentGame){
        if(currentGame.isPickerWinner()){
            return R.drawable.tick_icon;
        }
        else{
            return R.drawable.x_icon;
        }

    }
}
