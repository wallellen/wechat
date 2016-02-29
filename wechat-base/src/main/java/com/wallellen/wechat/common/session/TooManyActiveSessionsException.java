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
package com.wallellen.wechat.common.session;

/**
 * An exception that indicates the maximum number of active sessions has been
 * reached and the server is refusing to create any new sessions.
 */
public class TooManyActiveSessionsException
        extends IllegalStateException {
    private static final long serialVersionUID = 1L;

    /**
     * The maximum number of active sessions the server will tolerate.
     */
    private final int maxActiveSessions;

    /**
     * Creates a new TooManyActiveSessionsException.
     *
     * @param message   A description for the exception.
     * @param maxActive The maximum number of active sessions allowed by the
     *                  session manager.
     */
    public TooManyActiveSessionsException(String message,
                                          int maxActive) {
        super(message);

        maxActiveSessions = maxActive;
    }

    /**
     * Gets the maximum number of sessions allowed by the session manager.
     *
     * @return The maximum number of sessions allowed by the session manager.
     */
    public int getMaxActiveSessions() {
        return maxActiveSessions;
    }
}
