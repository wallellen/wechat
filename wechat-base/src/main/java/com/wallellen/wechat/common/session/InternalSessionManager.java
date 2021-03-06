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

public interface InternalSessionManager {

    /**
     * Return the active Session, associated with this Manager, with the
     * specified session id (if any); otherwise return <code>null</code>.
     *
     * @param id The session id for the session to be returned
     * @throws IllegalStateException if a new session cannot be
     *                               instantiated for any reason
     * @throws java.io.IOException   if an input/output error occurs while
     *                               processing this request
     */
    InternalSession findSession(String id);

    /**
     * Construct and return a new session object, based on the default
     * settings specified by this Manager's properties.  The session
     * id specified will be used as the session id.
     * If a new session cannot be created for any reason, return
     * <code>null</code>.
     *
     * @param sessionId The session id which should be used to create the
     *                  new session; if <code>null</code>, a new session id will be
     *                  generated
     * @throws IllegalStateException if a new session cannot be
     *                               instantiated for any reason
     */
    public InternalSession createSession(String sessionId);

    /**
     * Remove this Session from the active Sessions for this Manager.
     *
     * @param session Session to be removed
     */
    public void remove(InternalSession session);

    /**
     * Remove this Session from the active Sessions for this Manager.
     *
     * @param session Session to be removed
     * @param update  Should the expiration statistics be updated
     */
    public void remove(InternalSession session, boolean update);

    /**
     * Add this Session to the set of active Sessions for this Manager.
     *
     * @param session Session to be added
     */
    void add(InternalSession session);


    /**
     * Returns the number of active sessions
     *
     * @return number of sessions active
     */
    int getActiveSessions();

    /**
     * Get a session from the recycled ones or create a new empty one.
     * The PersistentManager manager does not need to create session data
     * because it reads it from the Store.
     */
    InternalSession createEmptySession();

    InternalSession[] findSessions();

    /**
     * Implements the Manager interface, direct call to processExpires
     */
    public void backgroundProcess();

    /**
     * Set the default maximum inactive interval (in seconds)
     * for Sessions created by this Manager.
     *
     * @param interval The new default value
     */
    void setMaxInactiveInterval(int interval);

    /**
     * <pre>
     * Set the manager checks frequency.
     * 设置每尝试多少次清理过期session，才真的会执行一次清理动作
     * 要和{@link #setBackgroundProcessorDelay(int)}联合起来看
     * 如果把这个数字设置为6（默认），那么就是说manager要等待 6 * backgroundProcessorDay的时间才会清理过期session
     * </pre>
     *
     * @param processExpiresFrequency the new manager checks frequency
     */
    void setProcessExpiresFrequency(int processExpiresFrequency);

    /**
     * <pre>
     * Set the manager background processor delay
     * 设置manager sleep几秒，尝试执行一次background操作（清理过期session）
     * </pre>
     *
     * @param backgroundProcessorDelay
     */
    void setBackgroundProcessorDelay(int backgroundProcessorDelay);


    /**
     * Set the maximum number of active Sessions allowed, or -1 for
     * no limit.
     * 设置最大活跃session数，默认无限
     *
     * @param max The new maximum number of sessions
     */
    void setMaxActiveSessions(int max);

}
