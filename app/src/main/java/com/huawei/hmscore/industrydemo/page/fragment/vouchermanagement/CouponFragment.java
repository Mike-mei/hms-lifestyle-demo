/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.huawei.hmscore.industrydemo.page.fragment.vouchermanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huawei.hmscore.industrydemo.R;
import com.huawei.hmscore.industrydemo.entity.Coupon;
import com.huawei.hmscore.industrydemo.entity.User;
import com.huawei.hmscore.industrydemo.page.VoucherMgtActivity;
import com.huawei.hmscore.industrydemo.repository.CouponRepository;
import com.huawei.hmscore.industrydemo.viewadapter.CouponListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Coupon Fragment
 *
 * @version [HMSCore-Demo 3.0.0.300, 2021/10/9]
 * @see com.huawei.hmscore.industrydemo.page.VoucherMgtActivity
 * @since [HMSCore-Demo 3.0.0.300]
 */
public class CouponFragment extends Fragment {

    private static final String TAG = CouponFragment.class.getSimpleName();

    private CouponListAdapter couponListAdapter;

    private List<Coupon> couponList = new ArrayList<>();

    private final User mUser;

    private final VoucherMgtActivity mActivity;

    public CouponFragment(VoucherMgtActivity activity, User user) {
        this.mUser = user;
        this.mActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voucher, container, false);
        initView(view);
        onDataRefresh();
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerOrderList = view.findViewById(R.id.rv_voucher_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerOrderList.setLayoutManager(layoutManager);

        couponListAdapter = new CouponListAdapter(mActivity, mUser);
        couponListAdapter.setCouponList(couponList);
        recyclerOrderList.setAdapter(couponListAdapter);
    }

    public void onDataRefresh() {
        couponList = new ArrayList<>();
        if (null != mUser) {
            couponList = new CouponRepository().queryByUserId(mUser.getOpenId());
        }
        if (couponList.isEmpty()) {
            return;
        }
        couponList.sort((o1, o2) -> {
            int compareRestId = o1.getRestId() - o2.getRestId();
            if (compareRestId != 0) {
                return compareRestId;
            } else {
                return o2.getDiscount() - o1.getDiscount();
            }
        });
        if (null != couponListAdapter) {
            couponListAdapter.setCouponList(couponList);
        }
    }

}