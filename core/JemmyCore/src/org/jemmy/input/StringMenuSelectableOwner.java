/*
 * Copyright (c) 2016, 2017 Oracle and/or its affiliates. All rights reserved.
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
package org.jemmy.input;

import org.jemmy.control.Wrap;
import org.jemmy.interfaces.MenuSelectableOwner;
import org.jemmy.lookup.LookupCriteria;

/**
 *
 * @author erikgreijus
 * @param <T> todo document
 */
public abstract class StringMenuSelectableOwner<T> extends StringMenuOwner<T> implements MenuSelectableOwner<T> {

    private static final String MENU_PATH_LENGTH_ERROR = "Menu path length should be greater than 0";

    public StringMenuSelectableOwner(Wrap<?> menuOwner) {
        super(menuOwner);
    }

    /**
     * Ensures state of a menu item conforming to the criteria. That would mean
     * that all intermediate items get expanded and the menus are shown.
     * Selection depends on if the desired state matches the current state or
     * not. I.e selection of the last criteria happens only if the state differs
     * from desiredSelectionState
     *
     * @param desiredSelectionState The desired selection state of the leaf menu
     * item.
     * @param texts used one for one level. In case of a menu bar, for example,
     * first string is to be used to find a top level menu, second to find a
     * menu underneath, etc.
     */
    public void push(boolean desiredSelectionState, String... texts) {
        if (texts.length == 0) {
            throw new IllegalArgumentException(MENU_PATH_LENGTH_ERROR);
        }
        push(desiredSelectionState, createCriteriaList(texts));
    }

    /**
     * Ensures state of a menu item conforming to the criteria. That would mean
     * that all intermediate items get expanded and the menus are shown.
     * Selection depends on if the desired state matches the current state or
     * not. I.e selection of the last criteria happens only if the state differs
     * from desiredSelectionState
     *
     * @param desiredSelectionState The desired selection state of the leaf menu
     * item.
     * @param criteria used one for one level. In case of a menu bar, for
     * example, first criteria is to be used to find a top level menu, second to
     * find a menu underneath, etc.
     */
    public void push(boolean desiredSelectionState, LookupCriteria<T>... criteria) {
        menu().push(desiredSelectionState, criteria);
    }

    /**
     * Returns the current selection state of the menu item conforming to the
     * criteria. That would mean that all intermediate items get expanded and
     * the menus are shown.
     *
     * @param texts used one for one level. In case of a menu bar, for example,
     * first criteria is to be used to find a top level menu, second to find a
     * menu underneath, etc.
     * @return True if the menu item is selected. Otherwise false.
     */
    public boolean getState(String... texts) {
        if (texts.length == 0) {
            throw new IllegalArgumentException(MENU_PATH_LENGTH_ERROR);
        }
        return getState(createCriteriaList(texts));
    }

    /**
     * Returns the current selection state of the menu item conforming to the
     * criteria. That would mean that all intermediate items get expanded and
     * the menus are shown.
     *
     * @param criteria used one for one level. In case of a menu bar, for
     * example, first criteria is to be used to find a top level menu, second to
     * find a menu underneath, etc.
     * @return True if the menu item is selected. Otherwise false.
     */
    public boolean getState(LookupCriteria<T>... criteria) {
        return menu().getState(criteria);
    }

}
