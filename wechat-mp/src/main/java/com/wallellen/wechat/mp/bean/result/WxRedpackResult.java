/*
 * This file Copyright (c) 2016. Walle.
 * (http://www.wallellen.com). All rights reserved.
 *
 *
 * This file is dual-licensed under both the
 * Walle Agreement (WA) and the GNU General Public License.
 * You may elect to use one or the other of these licenses.
 *
 * This file is distributed in the hope that it will be
 * useful, but AS-IS and WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE, TITLE, or NONINFRINGEMENT.
 * Redistribution, except as permitted by whichever of the GPL
 * or WA you select, is prohibited.
 *
 * 1. For the GPL license (GPL), you can redistribute and/or
 * modify this file under the terms of the GNU General
 * Public License, Version 3, as published by the Free Software
 * Foundation.  You should have received a copy of the GNU
 * General Public License, Version 3 along with this program;
 * if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * 2. For the Walle Agreement (WA), this file
 * and the accompanying materials are made available under the
 * terms of the WA which accompanies this distribution, and
 * is available at http://www.wallellen.com/agreement.html
 *
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */

package com.wallellen.wechat.mp.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 向微信用户个人发现金红包返回结果
 * https://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_5
 *
 * @author kane
 */
@XStreamAlias("xml")
public class WxRedpackResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4837415036337132073L;

    @XStreamAlias("return_code")
    String returnCode;
    @XStreamAlias("return_msg")
    String returnMsg;
    @XStreamAlias("sign")
    String sign;
    @XStreamAlias("result_code")
    String resultCode;

    @XStreamAlias("err_code")
    String errCode;
    @XStreamAlias("err_code_des")
    String errCodeDes;
    @XStreamAlias("mch_billno")
    String mchBillno;
    @XStreamAlias("mch_id")
    String mchId;
    @XStreamAlias("wxappid")
    String wxappid;
    @XStreamAlias("re_openid")
    String reOpenid;
    @XStreamAlias("total_amount")
    int totalAmount;
    @XStreamAlias("send_time")
    String sendTime;
    @XStreamAlias("send_listid")
    String sendListid;

    public String getErrCode() {
        return errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public String getSign() {
        return sign;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getMchBillno() {
        return mchBillno;
    }

    public String getMchId() {
        return mchId;
    }

    public String getWxappid() {
        return wxappid;
    }

    public String getReOpenid() {
        return reOpenid;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getSendTime() {
        return sendTime;
    }

    public String getSendListid() {
        return sendListid;
    }

    @Override
    public String toString() {
        return "WxRedpackResult{" +
                "returnCode=" + returnCode +
                ", returnMsg=" + returnMsg +
                ", sign=" + sign +
                ", errCode=" + errCode +
                ", errCodeDes=" + errCodeDes +
                ", mchBillno=" + mchBillno +
                ", mchId=" + mchId +
                ", wxappid=" + wxappid +
                ", reOpenid=" + reOpenid +
                ", totalAmount=" + totalAmount +
                ", sendTime=" + sendTime +
                ", sendListid=" + sendListid +
                '}';
    }
}
