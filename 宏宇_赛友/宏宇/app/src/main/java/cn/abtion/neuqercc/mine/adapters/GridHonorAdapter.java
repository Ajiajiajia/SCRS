package cn.abtion.neuqercc.mine.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;

import cn.abtion.neuqercc.mine.models.ShowHonorResponse;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import retrofit2.Call;

/**
 * @author fhyPayaso
 * @since 2017/11/22 14:17
 * email fhyPayaso@qq.com
 */

public class GridHonorAdapter extends BaseAdapter {

    private Context context;
    private List<ShowHonorResponse> honorCertificateList;
    private boolean isShowLast;
    private boolean isShowDelete;


    public GridHonorAdapter(Context context, List<ShowHonorResponse> honorCertificateList, boolean isShowLast) {

        this.isShowLast = isShowLast;
        this.context = context;
        this.honorCertificateList = honorCertificateList;
    }


    @Override
    public int getCount() {

        if (isShowLast) {
            return honorCertificateList.size() + 1;
        } else {
            return honorCertificateList.size();
        }

    }

    @Override
    public Object getItem(int position) {
        return honorCertificateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = new ViewHolder();


        if (position < honorCertificateList.size()) {


            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_honor, null, false);
            ImageView imgHonor = (ImageView) convertView.findViewById(R.id.img_grid_honor);
            Glide.with(context).load(honorCertificateList.get(position).getGloryPicUrl()).into(imgHonor);

            viewHolder.imgDelete = (ImageView) convertView.findViewById(R.id.img_show_delete);
            viewHolder.imgDelete.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);

            if (isShowDelete) {
                viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        deleteHonor(honorCertificateList.get(position).getOrder());
                        honorCertificateList.remove(position);
                        setIsShowDelete(false);
                    }
                });
            }

            return convertView;

        } else if (isShowLast) {


            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_honor, null, false);
            ImageView imgHonor = (ImageView) convertView.findViewById(R.id.img_grid_honor);
            Glide.with(context).load(R.drawable.bg_add_honor).into(imgHonor);
            viewHolder.imgDelete = (ImageView) convertView.findViewById(R.id.img_show_delete);
            viewHolder.imgDelete.setVisibility(View.GONE);

            return convertView;

        } else {

            return convertView;
        }
    }


    class ViewHolder {

        ImageView imgHonor, imgDelete;
    }

    public void setIsShowDelete(boolean isShowDelete) {

        this.isShowDelete = isShowDelete;
        notifyDataSetChanged();

    }


    private void deleteHonor(int order) {


        RestClient.getService().deleteHonor(LoginActivity.phoneNumber, order + 1).enqueue(new DataCallback<APIResponse>() {
            @Override
            public void onDataResponse(Call<APIResponse> call, retrofit2.Response<APIResponse> response) {

            }

            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });

    }
}
