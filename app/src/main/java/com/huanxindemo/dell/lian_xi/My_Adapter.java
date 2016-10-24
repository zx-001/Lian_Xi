package com.huanxindemo.dell.lian_xi;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.huanxindemo.dell.lian_xi.utils.BaseViewHolder;
import com.huanxindemo.dell.lian_xi.utils.TypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/10/24.
 */
public class My_Adapter extends RecyclerView.Adapter<BaseViewHolder> implements TypeUtil {

    private Context context;
    private Data bean;

    /**
     * 类型集合，adapter对应的数据集合
     */
    private List<Pair<Integer,Object>> superData;
    public My_Adapter(Context context,Data bean){
        this.context = context;
        this.bean = bean;
        superData = new ArrayList<Pair<Integer,Object>>();
        //添加问答结伴部分
        superData.add(new Pair<Integer,Object>(COMMUNITY_TOP,bean));
        initOtherData();
    }

    private void initOtherData() {
        for (int i = 0; i < bean.data.forum_list.size(); i++) {

            bean.data.forum_list.get(i).group.get(0).setHasTitle(true,bean.data.forum_list.get(i).name);//设置有标头
            for (int j = 0; j < bean.data.forum_list.get(i).group.size(); j++) {
                j++;
                Pair<Object, Object> objectObjectPair;//两个数据的集合  超过两个可以用list集合
                if (j == bean.data.forum_list.get(i).group.size()) {
                    objectObjectPair = wrapData(bean.data.forum_list.get(i).group.get(j - 1), null);
                } else {
                    objectObjectPair = wrapData(bean.data.forum_list.get(i).group.get(j - 1), bean.data.forum_list.get(i).group.get(j));
                }

                superData.add(new Pair<Integer, Object>(COMMUNITY_OHTER, objectObjectPair));

            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        return superData.get(position).first;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case COMMUNITY_TOP:
                return new ViewHolder_01(View.inflate(context, R.layout.layout_item1, null),context);
            case COMMUNITY_OHTER:
                return new ViewHolder_02(View.inflate(context, R.layout.layout_item2, null),context);
        }

        return new ViewHolder_02(View.inflate(context, R.layout.layout_item2, null),context);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (superData.get(position).first){
            case COMMUNITY_TOP:
                ((ViewHolder_01)holder).initData(bean);
                break;
            case COMMUNITY_OHTER:
                ((ViewHolder_02)holder).initData(superData.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return superData.size();
    }

    /**
     * 给数据打包，两个一块
     *
     * @return
     */
    public Pair<Object, Object> wrapData(Object f, Object s) {
        return new Pair<Object, Object>(f, s);
    }
}
