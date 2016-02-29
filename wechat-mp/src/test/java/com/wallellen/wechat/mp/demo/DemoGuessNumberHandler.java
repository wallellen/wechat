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

package com.wallellen.wechat.mp.demo;

import com.wallellen.wechat.common.exception.WxErrorException;
import com.wallellen.wechat.common.session.WxSession;
import com.wallellen.wechat.common.session.WxSessionManager;
import com.wallellen.wechat.mp.api.WxMpMessageHandler;
import com.wallellen.wechat.mp.api.WxMpMessageMatcher;
import com.wallellen.wechat.mp.api.WxMpService;
import com.wallellen.wechat.mp.bean.WxMpCustomMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutMessage;

import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class DemoGuessNumberHandler implements WxMpMessageHandler, WxMpMessageMatcher {

    private Random random = new Random();

    private Pattern pattern = Pattern.compile("\\d+");

    @Override
    public boolean match(WxMpXmlMessage message) {
        return isUserWantGuess(message) || isUserAnswering(message);
    }

    private boolean isUserWantGuess(WxMpXmlMessage message) {
        return "猜数字".equals(message.getContent());
    }

    private boolean isUserAnswering(WxMpXmlMessage message) {
        return pattern.matcher(message.getContent()).matches();
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        if (isUserWantGuess(wxMessage)) {
            letsGo(wxMessage, wxMpService, sessionManager);
        }

        if (isUserAnswering(wxMessage)) {
            giveHint(wxMessage, wxMpService, sessionManager);
        }

        return null;

    }

    protected void letsGo(WxMpXmlMessage wxMessage, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        WxSession session = sessionManager.getSession(wxMessage.getFromUserName());
        if (session.getAttribute("guessing") == null) {
            WxMpCustomMessage m = WxMpCustomMessage
                    .TEXT()
                    .toUser(wxMessage.getFromUserName())
                    .content("请猜一个100以内的数字")
                    .build();
            wxMpService.customMessageSend(m);
        } else {
            WxMpCustomMessage m = WxMpCustomMessage
                    .TEXT()
                    .toUser(wxMessage.getFromUserName())
                    .content("放弃了吗？那请重新猜一个100以内的数字")
                    .build();
            wxMpService.customMessageSend(m);
        }

        session.setAttribute("guessing", Boolean.TRUE);
        session.setAttribute("number", random.nextInt(100));
    }


    protected void giveHint(WxMpXmlMessage wxMessage, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        WxSession session = sessionManager.getSession(wxMessage.getFromUserName());

        if (session.getAttribute("guessing") == null) {
            return;
        }
        boolean guessing = (Boolean) session.getAttribute("guessing");
        if (!guessing) {
            return;
        }

        int answer = (Integer) session.getAttribute("number");
        int guessNumber = Integer.valueOf(wxMessage.getContent());
        if (guessNumber < answer) {
            WxMpCustomMessage m = WxMpCustomMessage
                    .TEXT()
                    .toUser(wxMessage.getFromUserName())
                    .content("小了")
                    .build();
            wxMpService.customMessageSend(m);

        } else if (guessNumber > answer) {
            WxMpCustomMessage m = WxMpCustomMessage
                    .TEXT()
                    .toUser(wxMessage.getFromUserName())
                    .content("大了")
                    .build();
            wxMpService.customMessageSend(m);
        } else {
            WxMpCustomMessage m = WxMpCustomMessage
                    .TEXT()
                    .toUser(wxMessage.getFromUserName())
                    .content("Bingo!")
                    .build();
            session.removeAttribute("guessing");
            wxMpService.customMessageSend(m);
        }

    }
}
