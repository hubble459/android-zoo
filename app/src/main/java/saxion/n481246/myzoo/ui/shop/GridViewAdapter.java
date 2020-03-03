package saxion.n481246.myzoo.ui.shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import saxion.n481246.myzoo.R;
import saxion.n481246.myzoo.ShopItem;

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShopItem> shopItems;


    GridViewAdapter(@NonNull Context context, @NonNull List<ShopItem> objects) {
        this.mContext = context;
        this.shopItems = objects;
    }

    @Override
    public int getCount() {
        return shopItems.size();
    }

    @Override
    public Object getItem(int position) {
        return shopItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.shop_item_list, parent,false);

        ShopItem currentItem = shopItems.get(position);

        ImageView image = listItem.findViewById(R.id.imageView);
        image.setImageResource(currentItem.getImageId());
        image.setBackgroundColor(currentItem.getBgColor());

        TextView name = listItem.findViewById(R.id.itemName);
        name.setText(currentItem.getItemName());

        TextView price = listItem.findViewById(R.id.itemPrice);
        price.setText(currentItem.getPrice() +  " " + parent.getResources().getString(R.string.coins));

        return listItem;
    }
}
