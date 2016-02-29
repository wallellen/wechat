package com.wallellen.wechat.mp.api;

import com.google.inject.Inject;
import com.wallellen.wechat.common.exception.WxErrorException;
import com.wallellen.wechat.mp.bean.result.WxMpUserCumulate;
import com.wallellen.wechat.mp.bean.result.WxMpUserSummary;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author chanjarster
 */
@Test(groups = "miscAPI", dependsOnGroups = {"baseAPI"})
@Guice(modules = ApiTestModule.class)
public class WxMpMiscAPITest {

    @Inject
    protected WxMpServiceImpl wxService;

    @Test
    public void testGetCallbackIP() throws WxErrorException {
        String[] ipArray = wxService.getCallbackIP();
        System.out.println(Arrays.toString(ipArray));
        Assert.assertNotNull(ipArray);
        Assert.assertNotEquals(ipArray.length, 0);
    }

    @Test
    public void testGetUserSummary() throws WxErrorException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = simpleDateFormat.parse("2015-01-01");
        Date endDate = simpleDateFormat.parse("2015-01-02");
        List<WxMpUserSummary> summaries = wxService.getUserSummary(beginDate, endDate);
        System.out.println(summaries);
        Assert.assertNotNull(summaries);
    }

    @Test
    public void testGetUserCumulate() throws WxErrorException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = simpleDateFormat.parse("2015-01-01");
        Date endDate = simpleDateFormat.parse("2015-01-02");
        List<WxMpUserCumulate> cumulates = wxService.getUserCumulate(beginDate, endDate);
        System.out.println(cumulates);
        Assert.assertNotNull(cumulates);
    }

}
