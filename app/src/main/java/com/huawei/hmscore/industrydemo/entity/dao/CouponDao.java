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

package com.huawei.hmscore.industrydemo.entity.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.huawei.hmscore.industrydemo.entity.Coupon;

import java.util.List;

/**
 * Coupon Dao
 * 
 * @version [HMSCore-Demo 3.0.0.300, 2021/10/9]
 * @see [Related Classes/Methods]
 * @since [HMSCore-Demo 3.0.0.300]
 */
@Dao
public interface CouponDao {
    /**
     * Query all Restaurants.
     *
     * @return Restaurant list
     */
    @Query("SELECT * FROM Coupon")
    List<Coupon> queryAll();

    /**
     * deleteAll
     */
    @Query("DELETE FROM Coupon")
    void deleteAll();

    @Query("SELECT * FROM Coupon WHERE (openId=:openId AND restId=:restId)")
    List<Coupon> queryByUserAndRest(String openId, int restId);

    @Query("SELECT * FROM Coupon WHERE (openId=:openId AND restId=:restId AND condition=:condition)")
    List<Coupon> queryByUserAndRestAndCondition(String openId, int restId, int condition);

    @Query("SELECT * FROM Coupon WHERE (openId=:openId AND restId=:restId AND status=:status)")
    List<Coupon> queryByUserAndRestAndStatus(String openId, int restId, boolean status);

    @Update
    void update(Coupon coupon);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Coupon coupon);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Coupon> couponList);

    @Query("SELECT * FROM Coupon WHERE (openId=:userId)")
    List<Coupon> queryByUserId(String userId);
}
