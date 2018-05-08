/*
 * Copyright (c) 2007, 2017 Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.jemmy.lookup;

import org.jemmy.resources.StringComparePolicy;

/**
 * Default comparison policy is StringComparePolicy.SUBSTRING
 * @param <T>
 * @author shura
 */
public abstract  class ByStringLookup<T> implements LookupCriteria<T> {

    private StringComparePolicy policy;
    private String text;

    /**
     * Default comparison policy is StringComparePolicy.SUBSTRING
     * @param text
     */
    protected ByStringLookup(String text) {
        this(text, StringComparePolicy.SUBSTRING);
    }

    /**
     *
     * @param text
     * @param policy
     */
    protected ByStringLookup(String text, StringComparePolicy policy) {
        this.policy = policy;
        this.text = text;
    }

    /**
     *
     * @param control
     * @return
     */
    public abstract String getText(T control);

    /**
     *
     * @param control
     * @return
     */
    public boolean check(T control) {
        return policy.compare(text, getText(control));
    }

    @Override
    public String toString() {
        return "Text = " + text;
    }

}
